package com.project.repository;

import com.project.model.Comment;
import com.project.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Override
    Comment findOne(Long aLong);
}
