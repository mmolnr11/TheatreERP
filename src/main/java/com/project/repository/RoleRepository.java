package com.project.repository;

import com.project.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Override
    Role findOne(Long aLong);
}
