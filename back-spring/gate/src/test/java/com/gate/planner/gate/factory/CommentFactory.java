package com.gate.planner.gate.factory;

import com.gate.planner.gate.dao.coment.CommentRepository;
import com.gate.planner.gate.model.entity.comment.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentFactory {

    @Autowired
    CommentRepository commentRepository;

    private String content = "Test Content";
    private String updateContent = "Test Update Content";

    public String getUpdateContent() {
        return updateContent;
    }

    public String getContent() {
        return content;
    }

    public List<Comment> findCommentAtDB() {
        return commentRepository.findAll();
    }
}
