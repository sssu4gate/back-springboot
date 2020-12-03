package com.gate.planner.gate.controller;

import com.gate.planner.gate.model.dto.comment.response.CommentResponseDto;
import com.gate.planner.gate.model.dto.course.request.CourseRequestDto;
import com.gate.planner.gate.model.dto.course.response.CourseResponseDetailDto;
import com.gate.planner.gate.model.dto.course.response.CourseResponseDto;
import com.gate.planner.gate.model.entity.course.CourseRequestType;
import com.gate.planner.gate.model.entity.course.report.CourseReportType;
import com.gate.planner.gate.service.comment.CommentService;
import com.gate.planner.gate.service.course.CourseService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    @ApiOperation("코스 저장")
    @PostMapping(value = "/save", consumes = (MediaType.ALL_VALUE))
    public CourseResponseDetailDto saveCourse(@RequestPart(required = false) MultipartFile img, @RequestPart CourseRequestDto courseRequestDto) throws ParseException, IOException {
        return courseService.saveCourse(img, courseRequestDto);
    }

    @ApiOperation("코스 좋아요 기능")
    @GetMapping("/like/{id}")
    public int likeCourse(@PathVariable Long id) {
        /**
         * 행알이의 추가 코드
         */
        return courseService.likeCourse(id);
    }

    /**
     * 행알이가 추가한 코드
     */
    @ApiOperation("코스 신고 기능")
    @GetMapping("/report/{id}")
    public void reportCourse(@PathVariable Long id, @RequestParam CourseReportType type) {
        courseService.reportCourse(id, type);
    }

    @ApiOperation("코스 상세보기")
    @GetMapping("/{id}")
    public CourseResponseDetailDto responseCourseDetail(@PathVariable Long id) {
        return courseService.courseDetail(id);
    }

    @ApiOperation("기본 코스 리스트 리턴(최신순,좋아요순)")
    @GetMapping("/list")
    public List<CourseResponseDto> responseBasicCourseList(@RequestParam CourseRequestType type, @RequestParam int page, @RequestParam int offset) {
        return courseService.basicCourseList(type, page, offset);
    }

    @ApiOperation("코스 수정")
    @PutMapping(value = "/{id}", consumes = (MediaType.ALL_VALUE))
    public CourseResponseDetailDto updateCourse(@PathVariable Long id, @RequestPart(required = false) MultipartFile img, @RequestPart CourseRequestDto courseRequestDto) throws ParseException, IOException {
        return courseService.updateCourse(id, img, courseRequestDto);
    }

    private final CommentService commentService;

    @ApiOperation("코스랑 관련된 댓글 전부 가져오기")
    @GetMapping("/{courseId}/comment")
    public List<CommentResponseDto> getComment(@PathVariable Long courseId) {
        return commentService.getComment(courseId);
    }

    @ApiOperation("댓글 입력하기")
    @PostMapping("/{courseId}/comment/write")
    public void writeComment(@PathVariable Long courseId, @RequestBody String content) {
        commentService.saveComment(courseId, content);
    }

    @ApiOperation("댓글 삭제")
    @DeleteMapping("/{courseId}/comment/{commentId}")
    public void deleteComment(@PathVariable Long courseId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }

    @ApiOperation("댓글 수정")
    @PutMapping("/{courseId}/comment/{commentId}")
    public void updateComment(@PathVariable Long courseId, @PathVariable Long commentId, @RequestBody String content) {
        commentService.modifyComment(commentId, content);
    }
}
