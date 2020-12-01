package com.gate.planner.gate.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonFactory {

    @Autowired
    public UserFactory userFactory;

    @Autowired
    public PlaceFactory placeFactory;

    @Autowired
    public CourseFactory courseFactory;

    @Autowired
    public CommentFactory commentFactory;

}
