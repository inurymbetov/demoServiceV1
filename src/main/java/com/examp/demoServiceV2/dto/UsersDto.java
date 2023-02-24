package com.examp.demoServiceV2.dto;

import com.examp.demoServiceV2.entity.users.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {

    private long id;
    private String uid;
    private String fullName;
    private String email;
    private String password;

    public static UsersDto toDto(Users users) {
        return UsersDto.builder()
                .id(users.getId())
                .uid(users.getUid())
                .fullName(users.getLastName() + " " + users.getFirstName())
                .email(users.getEmail())
                .password(users.getPassword())
                .build();
    }

    public static Users toEntity(UsersDto dto) {
        return Users.builder()
                .id(dto.getId())
                .uid(dto.getUid())
                .firstName(dto.getFullName().split("\\s")[1])
                .lastName(dto.getFullName().split("\\s")[0])
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

}
