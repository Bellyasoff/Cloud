package com.car.cloud.service;

import com.car.cloud.dto.RegistrationDto;
import com.car.cloud.model.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
