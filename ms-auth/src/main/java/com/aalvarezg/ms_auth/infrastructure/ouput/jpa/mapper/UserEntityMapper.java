package com.aalvarezg.ms_auth.infrastructure.ouput.jpa.mapper;

import com.aalvarezg.ms_auth.domain.model.User;
import com.aalvarezg.ms_auth.infrastructure.ouput.jpa.entity.UserEntity;

public class UserEntityMapper {

    private UserEntityMapper(){
        throw new IllegalStateException("Mapper para usuarios");
    }
    public static UserEntity toEntity(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setPassword(user.getPassword());
        userEntity.setPassword(user.getPassword());
        return userEntity;
    }

    public static User toUser(UserEntity userEntity){
        User user = new User();
        user.setId(userEntity.getId());
        user.setPassword(userEntity.getPassword());
        user.setUsername(userEntity.getUsername());
        return user;
    }
}
