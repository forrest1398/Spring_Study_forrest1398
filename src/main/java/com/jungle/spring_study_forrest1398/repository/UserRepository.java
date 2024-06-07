package com.jungle.spring_study_forrest1398.repository;

import com.jungle.spring_study_forrest1398.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByUserName(String userName);
}
