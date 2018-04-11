package com.project.repository;

import com.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    User findOne(Long aLong);
    User getUserByEmailAddress(@Param("email") String email);
    User getUserByPosition(@Param("position")String position);


}
