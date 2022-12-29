package com.mralifr.crud.models.mappers;

import com.mralifr.crud.models.entities.UserEntity;
import com.mralifr.crud.models.requests.UserRequest;
import com.mralifr.crud.models.responses.UserResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface UserMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserEntityFromRequest(UserRequest userRequest, @MappingTarget UserEntity userEntity);
    UserEntity toUserEntity(UserRequest userRequest);
    UserResponse toUserResponse(UserEntity user);
}
