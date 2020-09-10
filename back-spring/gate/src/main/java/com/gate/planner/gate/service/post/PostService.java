package com.gate.planner.gate.service.post;

import com.gate.planner.gate.dao.post.ImageRepository;
import com.gate.planner.gate.dao.post.PostRepository;
import com.gate.planner.gate.dao.user.UserRepository;
import com.gate.planner.gate.exception.user.UserNotExistException;
import com.gate.planner.gate.model.dto.request.place.PlacePostDto;
import com.gate.planner.gate.model.dto.request.place.WriteCourseRequestDto;
import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.place.Place;
import com.gate.planner.gate.model.entity.post.Image;
import com.gate.planner.gate.model.entity.post.Post;
import com.gate.planner.gate.model.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final S3Function s3Function;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final PostRepository postRepository;

    public void savePost(Course course, List<Place> places, WriteCourseRequestDto writeCourseRequestDto, MultipartFile[] images) throws IOException {
        User user = userRepository.findByUserName(writeCourseRequestDto.getUserName()).orElseThrow(UserNotExistException::new);
        List<String> imageStrings = null;
        Iterator it = null;
        int size = places.size();


        if (images != null) {
            imageStrings = s3Function.StoreImgToS3(images);
            it = imageStrings.iterator();
        }


        for (int i = 0; i < size; i++) {
            PlacePostDto temp = writeCourseRequestDto.getPlaces()[i];
            Post post = Post.builder()
                    .course(course)
                    .place(places.get(i))
                    .content(temp.getContent())
                    .user(user).build();

            for (int j = 0; j < temp.getImgCount(); j++)
                imageRepository.save(Image.builder()
                        .imgUrl((String) it.next())
                        .post(post).build());

            postRepository.save(post);
        }
    }


}
