package com.jungle.spring_study_forrest1398.service;

import com.jungle.spring_study_forrest1398.domain.User;
import com.jungle.spring_study_forrest1398.dto.UserDto;
import com.jungle.spring_study_forrest1398.exception.DuplicateUsernameException;
import com.jungle.spring_study_forrest1398.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto createUser(UserDto userDto) {
        // 중복시 예외처리
        if (userRepository.existsUserByUserName(userDto.getUserName()))
            throw new DuplicateUsernameException();
        User newUser = new User(
                userDto.getUserName(),
                userDto.getPassword()
        );
        User createdUser = userRepository.save(newUser);
        return new UserDto(
                createdUser.getUserName(),
                createdUser.getPassword()
        );
    }
}
