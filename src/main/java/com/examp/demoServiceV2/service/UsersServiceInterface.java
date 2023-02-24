package com.examp.demoServiceV2.service;

import com.examp.demoServiceV2.dao.user.UsersImpl;
import com.examp.demoServiceV2.dto.UsersDto;
import com.examp.demoServiceV2.entity.users.Users;
import com.examp.demoServiceV2.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceInterface implements ServiceInterface<UsersDto> {

    private final UsersImpl usersData;

    @Override
    public List<UsersDto> getAll() {
        return usersData.getAll().stream()
                .filter(Users::isActive)
                .map(UsersDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UsersDto getById(long id) throws ApiException {
        try {
            return UsersDto.toDto(usersData.getById(id));
        } catch (DataAccessException accessException) {
            throw new ApiException("Not found user by id = " + id, accessException);
        }
    }

    @Override
    public UsersDto getByEmail(String email) throws ApiException {
        return usersData.getByEmail(email, true).map(UsersDto::toDto).orElseThrow(ApiException.getSupplier("Not found user by email = " + email));
    }

    @Override
    public UsersDto create(UsersDto object) throws ApiException {
        if (Objects.isNull(object))
            throw new ApiException("User null");
        if (usersData.userExistByEmail(object.getEmail(), false))
            throw new ApiException("Already exist user by email = " + object.getEmail());
        Users users = UsersDto.toEntity(object);
        users.setUid(UUID.randomUUID().toString());
        users.setActive(false);
        usersData.create(users);
        return usersData.getByEmail(object.getEmail(), false).map(UsersDto::toDto).orElse(null);
    }

    @Override
    public UsersDto update(UsersDto object) throws ApiException {
        if (Objects.isNull(object))
            throw new ApiException("User null");
        if (!usersData.userExistByUid(object.getUid()))
            throw new ApiException("Not found user by uid = " + object.getUid());
        Users users = UsersDto.toEntity(object);
        usersData.update(users);
        return usersData.getByUid(object.getUid()).map(UsersDto::toDto).orElse(null);
    }

    @Override
    public void delete(UsersDto object) throws ApiException {

    }

}

