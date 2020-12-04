package com.gate.planner.gate.service.course;

import com.gate.planner.gate.dao.common.CommonPage;
import com.gate.planner.gate.dao.course.CourseLikeRepository;
import com.gate.planner.gate.dao.course.CourseMemoRepository;
import com.gate.planner.gate.dao.course.CourseReportRepository;
import com.gate.planner.gate.dao.course.CourseRepository;
import com.gate.planner.gate.dao.course.projection.CourseOnly;
import com.gate.planner.gate.dao.place.PlaceRepository;
import com.gate.planner.gate.dao.place.PlaceWrapperRepository;
import com.gate.planner.gate.dao.user.UserRepository;
import com.gate.planner.gate.exception.course.*;
import com.gate.planner.gate.exception.user.UserNotExistException;
import com.gate.planner.gate.model.dto.course.request.CourseMemoRequestDto;
import com.gate.planner.gate.model.dto.course.request.CourseRequestDto;
import com.gate.planner.gate.model.dto.course.response.CourseMemoResponseDto;
import com.gate.planner.gate.model.dto.course.response.CourseResponseDetailDto;
import com.gate.planner.gate.model.dto.course.response.CourseResponseDto;
import com.gate.planner.gate.model.dto.place.PlaceWrapperRequestDto;
import com.gate.planner.gate.model.dto.place.PlaceWrapperResponseDto;
import com.gate.planner.gate.model.entity.course.*;
import com.gate.planner.gate.model.entity.course.like.CourseLike;
import com.gate.planner.gate.model.entity.course.memo.CourseMemo;
import com.gate.planner.gate.model.entity.course.report.CourseReport;
import com.gate.planner.gate.model.entity.course.report.CourseReportType;
import com.gate.planner.gate.model.entity.place.PlaceWrapper;
import com.gate.planner.gate.model.entity.user.User;
import com.gate.planner.gate.service.course.function.ImgUploadFunction;
import com.gate.planner.gate.service.place.PlaceService;
import com.gate.planner.gate.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseMemoRepository courseMemoRepository;
    private final PlaceRepository placeRepository;
    private final CourseRepository courseRepository;
    private final PlaceService placeService;
    private final UserRepository userRepository;
    private final CourseLikeRepository courseLikeRepository;
    private final PlaceWrapperRepository placeWrapperRepository;
    /**
     * 행알이가 추가한 코드>3<
     */
    private final CourseReportRepository courseReportRepository;
    private final ImgUploadFunction imgUploadFunction;


    /**
     * 코스 저장
     */
    @Transactional
    public CourseResponseDetailDto saveCourse(MultipartFile image, CourseRequestDto courseRequestDto) throws ParseException, IOException {
        User user = userRepository.findById(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName())).orElseThrow(UserNotExistException::new);
        List<PlaceWrapperResponseDto> places = new ArrayList<>();
        List<CourseMemoResponseDto> memos = null;
        String courseImgUrl = null;
        int totalCost = 0;

        if (image != null)
            courseImgUrl = imgUploadFunction.StoreImgToS3(image);


        Course course = courseRepository.save(
                Course.builder()
                        .title(courseRequestDto.getCourseName())
                        .content(courseRequestDto.getContent())
                        .shareType(courseRequestDto.getShareType())
                        .dateDay(DateUtil.parseDateFormat(courseRequestDto.getDateDay()))
                        .imgUrl(courseImgUrl)
                        .user(user).build());

        if (courseRequestDto.getPlaces() != null) {
            for (PlaceWrapperRequestDto placeWrapperRequestDto : courseRequestDto.getPlaces()) {
                PlaceWrapper placeWrapper = placeService.savePlaceWrapper(placeWrapperRequestDto, course);
                totalCost += placeWrapper.getCost();
                places.add(new PlaceWrapperResponseDto(placeWrapper));
            }
            course.setTotalCost(totalCost);
        }

        if (courseRequestDto.getMemos() != null) {
            memos = new ArrayList<>();
            CourseMemo courseMemo = null;
            for (CourseMemoRequestDto memo : courseRequestDto.getMemos()) {
                courseMemo = courseMemoRepository.save(CourseMemo.builder()
                        .course(course)
                        .type(memo.getType())
                        .content(memo.getContent()).build());
                memos.add(new CourseMemoResponseDto(courseMemo));
            }
        }


        return CourseResponseDetailDto.builder()
                .id(course.getId())
                .courseImgUrl(courseImgUrl)
                .userImgUrl(user.getImgUrl())
                .likeNum(course.getLikeNum())
                .commentNum(course.getCommentNum())
                .content(course.getContent())
                .createdAt(course.getCreatedAt())
                .dateDay(course.getDateDay())
                .nickName(user.getNickName())
                .title(course.getTitle())
                .type(course.getShareType())
                .totalCost(course.getTotalCost())
                .memos(memos)
                .places(places).build();
    }

    @Transactional
    public CourseResponseDetailDto updateCourse(Long id, MultipartFile image, CourseRequestDto courseRequestDto) throws ParseException, IOException {
        int totalCost = 0;
        User user = userRepository.findById(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName())).orElseThrow(UserNotExistException::new);
        Course updateCourse = courseRepository.findById(id).orElseThrow(CourseNotExistException::new);

        if (image != null) {
            updateCourse.setImgUrl(imgUploadFunction.StoreImgToS3(image));
            ;
        }

        updateCourse.setTitle(courseRequestDto.getCourseName());
        updateCourse.setContent(courseRequestDto.getContent());
        updateCourse.setDateDay(DateUtil.parseDateFormat(courseRequestDto.getDateDay()));
        updateCourse.setShareType(courseRequestDto.getShareType());

        if (user.equals(updateCourse.getUser())) {
            deletePlaceWrapperAndMemo(updateCourse);
            if (updateCourse.getPlaces() != null) {
                for (PlaceWrapperRequestDto placeWrapperRequestDto : courseRequestDto.getPlaces()) {
                    PlaceWrapper placeWrapper = placeService.savePlaceWrapper(placeWrapperRequestDto, updateCourse);
                    totalCost += placeWrapper.getCost();
                }
                updateCourse.setTotalCost(totalCost);
            }

            if (courseRequestDto.getMemos() != null) {
                for (CourseMemoRequestDto memo : courseRequestDto.getMemos()) {
                    courseMemoRepository.save(CourseMemo.builder()
                            .course(updateCourse)
                            .type(memo.getType())
                            .content(memo.getContent()).build());
                }
            }
            return new CourseResponseDetailDto(updateCourse);
        } else
            throw new CourseUpdateDenyException();

    }

    /**
     * 코스 좋아요
     */
    @Transactional
    public int likeCourse(Long id) {
        User user = userRepository.findById(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName())).orElseThrow(UserNotExistException::new);
        Course course = courseRepository.findById(id).orElseThrow(CourseNotExistException::new);

        if (courseLikeRepository.existsByCourseAndUser(course, user)) {
            course.setLikeNum(course.getLikeNum() - 1);
            courseLikeRepository.deleteByCourseAndUser(course, user);
            course.getUser().setLikeNum(course.getUser().getLikeNum() - 1);
        } else {
            courseLikeRepository.save(CourseLike.builder()
                    .course(course)
                    .user(user).build());

            course.setLikeNum(course.getLikeNum() + 1);
            course.getUser().setLikeNum(course.getUser().getLikeNum() + 1);
        }
        return course.getLikeNum();
    }

    /**
     * 코스 신고
     */
    public void reportCourse(Long id, CourseReportType type) {
        User user = userRepository.findById(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName())).orElseThrow(UserNotExistException::new);
        Course course = courseRepository.findById(id).orElseThrow(CourseNotExistException::new);

        if (courseReportRepository.existsByCourseAndUser(course, user)) {
            AlreadyReportedException alreadyReportedException = new AlreadyReportedException();
            throw alreadyReportedException;
        } else {
            courseReportRepository.save(CourseReport.builder()
                    .course(course)
                    .type(type)
                    .user(user).build());

            course.setReportNum(course.getReportNum() + 1);
        }

        checkReportNum(course, course.getReportNum());
    }

    /**
     * 코스 신고 횟수 체크
     */
    private void checkReportNum(Course course, int reportNum) {
        if (reportNum == 5)
            course.setReportFlag(true);
    }

    /**
     * 나와 연관된 코스정보
     */
    @Transactional
    public List<CourseResponseDto> findUserRelatedCourse(UserRelatedCourseSearchType type, int page, int offset, String startDate, String endDate) throws ParseException {
        User user = userRepository.findById(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName())).orElseThrow(UserNotExistException::new);
        List<CourseResponseDto> returnCourseList = new ArrayList<>();
        if (type.equals(UserRelatedCourseSearchType.LIKE)) {
            List<CourseOnly> courseList = courseLikeRepository.findAllByUser(user, new CommonPage(page, offset));
            for (CourseOnly course : courseList)
                returnCourseList.add(new CourseResponseDto(course.getCourse()));

        } else if (type.equals(UserRelatedCourseSearchType.WRITE)) {
            List<Course> courseList = courseRepository.findAllByUser(user, new CommonPage(page, offset));
            for (Course course : courseList)
                returnCourseList.add(new CourseResponseDto(course));
        } else if (type.equals(UserRelatedCourseSearchType.DATE) && startDate != null && endDate != null) {
            Date sDate = DateUtil.parseDateFormat(startDate);
            Date eDate = DateUtil.parseDateFormat(endDate);

            return courseRepository.findAllByDateDayBetweenAndUser(sDate, eDate, user, new CommonPage(page, offset)).stream().map(CourseResponseDto::new).collect(Collectors.toList());
        } else
            throw new CourseSearchTypeWrongException();
        return returnCourseList;
    }

    /**
     * 코스 검색
     */
    @Transactional
    public List<CourseResponseDto> searchCourse(String keyWord, CourseSearchType type, int page, int offset) {
        if (type.equals(CourseSearchType.WRITE)) {
            return courseRepository.findAllByUser_NickNameAndShareTypeAndReportFlagIsFalse(keyWord, new CommonPage(page, offset), ShareType.PUBLIC).stream().map(CourseResponseDto::new).collect(Collectors.toList());
        } else if (type.equals(CourseSearchType.MONEY)) {
            return courseRepository.findAllByTotalCostIsLessThanEqualAndShareTypeAndReportFlagIsFalse(Integer.parseInt(keyWord), new CommonPage(page, offset), ShareType.PUBLIC).stream().map(CourseResponseDto::new).collect(Collectors.toList());
        } else if (type.equals(CourseSearchType.TAG)) {
            return null;
        } else {
            return courseRepository.findDistinctByTitleContainingOrContentContainingAndShareTypeAndReportFlagIsFalse(keyWord, keyWord, new CommonPage(page, offset), ShareType.PUBLIC).stream().map(CourseResponseDto::new).collect(Collectors.toList());
        }
    }

    /**
     * 코스 상세보기
     */
    @Transactional
    public CourseResponseDetailDto courseDetail(long id) {
        User user = userRepository.findById(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName())).orElseThrow(UserNotExistException::new);
        Course course = courseRepository.findById(id).orElseThrow(CourseNotExistException::new);
        return new CourseResponseDetailDto(course);
    }

    /**
     * 코스 리스트 리턴(최신순,좋아요 순)
     */
    @Transactional
    public List<CourseResponseDto> basicCourseList(CourseRequestType type, int page, int offset) {
        if (type == CourseRequestType.LATEST) {
            return courseRepository.findAllByShareTypeAndReportFlagIsFalse(ShareType.PUBLIC, new CommonPage(page, offset))
                    .stream().map(CourseResponseDto::new).collect(Collectors.toList());
        } else if (type == CourseRequestType.LIKE) {
            return courseRepository.findAllByShareTypeAndReportFlagIsFalseOrderByLikeNumDesc(ShareType.PUBLIC, new CommonPage(page, offset))
                    .stream().map(CourseResponseDto::new).collect(Collectors.toList());
        } else {
            throw new CourseRequestTypeInvalidException();
        }
    }

    private void deletePlaceWrapperAndMemo(Course course) {
        if (course.getPlaces() != null)
            for (PlaceWrapper placeWrapper : course.getPlaces())
                placeWrapperRepository.delete(placeWrapper);
        if (course.getMemos() != null)
            for (CourseMemo courseMemo : course.getMemos())
                courseMemoRepository.delete(courseMemo);
    }
}

