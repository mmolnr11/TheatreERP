package com.project.dao;

import com.project.model.Comment;
import com.project.model.Employee;
import com.project.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentDao {
    @Autowired
    CommentRepository commentRepository;

    public void saveComment(Comment comment){
        commentRepository.save(comment);
    }
}
