package com.aalvarezg.ms_user.infrastructure.ouput.jpa.mapper;

import com.aalvarezg.ms_user.domain.model.User;
import com.aalvarezg.ms_user.infrastructure.ouput.jpa.entity.UserEntity;

public class UserEntityMapper {

    private UserEntityMapper(){
        throw new IllegalStateException("Mapper para usuarios");
    }
    public static UserEntity toEntity(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setFullName(user.getFullName());
        userEntity.setProfilePicture(user.getProfilePicture());
        userEntity.setUsername(userEntity.getUsername());
        return userEntity;
    }

    public static User toUsuario(UserEntity userEntity){
        User user = new User();
        user.setId(userEntity.getId());
        user.setFullName(userEntity.getFullName());
        user.setUsername(userEntity.getUsername());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());
        user.setProfilePicture(userEntity.getProfilePicture());
        return user;
    }
}
