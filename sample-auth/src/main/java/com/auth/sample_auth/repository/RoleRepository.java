package com.auth.sample_auth.repository;

import com.auth.sample_auth.dto.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
