package com.gate.planner.gate.factory;

import com.gate.planner.gate.dao.course.CourseRepository;
import com.gate.planner.gate.model.dto.course.request.CourseMemoRequestDto;
import com.gate.planner.gate.model.dto.course.request.CourseRequestDto;
import com.gate.planner.gate.model.dto.place.PlaceWrapperRequestDto;
import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.course.ShareType;
import com.gate.planner.gate.model.entity.course.memo.CourseMemoType;
import com.gate.planner.gate.model.entity.user.User;
import com.gate.planner.gate.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CourseFactory {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    PlaceFactory placeFactory;

    @Autowired
    UserFactory userFactory;

    private String title = "jUnitTestCourse";
    private String content = "jUnitTestContent";
    private User user = null;
    private Date dateDay = null;
    private ShareType courseShareType = ShareType.PUBLIC;

    private String updateTitle = "jUnitUpdateTestCourse";
    private String updateContent = "jUnitUpdateTestContent";
    private ShareType updateCourseShareType = ShareType.PRIVATE;

    public CourseRequestDto returnUpdateCourseRequestDto() {
        List<PlaceWrapperRequestDto> updatePlaceWrapperList = placeFactory.returnSecondPlaceWrapperDtoList();
        return new CourseRequestDto(updateTitle, updateContent, "2020-12-01", updatePlaceWrapperList, returnCourseMemoRequestListDto(), updateCourseShareType);
    }

    public CourseRequestDto returnCourseRequestDto() {
        List<PlaceWrapperRequestDto> savePlaceWrapperList = placeFactory.returnFirstPlaceWrapperDtoList();
        return new CourseRequestDto(title, content, DateUtil.parseString(dateDay), savePlaceWrapperList, returnCourseMemoRequestListDto(), courseShareType);
    }

    public Course returnSaveCourse() throws ParseException {
        return courseRepository.save(Course.builder()
                .user(userFactory.returnSignUpUser1())
                .content(content)
                .title(title)
                .dateDay(dateDay)
                .shareType(courseShareType)
                .build());
    }

    public List<CourseMemoRequestDto> returnCourseMemoRequestListDto() {
        ArrayList<CourseMemoRequestDto> courseMemos = new ArrayList<>();

        CourseMemoRequestDto courseMemoRequestDto1 = new CourseMemoRequestDto("memo1", CourseMemoType.CHECKON);
        CourseMemoRequestDto courseMemoRequestDto2 = new CourseMemoRequestDto("memo2", CourseMemoType.CHECKOFF);
        CourseMemoRequestDto courseMemoRequestDto3 = new CourseMemoRequestDto("memo3", CourseMemoType.MEMO);

        courseMemos.add(courseMemoRequestDto1);
        courseMemos.add(courseMemoRequestDto2);
        courseMemos.add(courseMemoRequestDto3);

        return courseMemos;
    }

    public List<Course> findCourseAtDB() {
        return courseRepository.findAll();
    }
}
