package com.examp.demoServiceV2.dto;

import com.examp.demoServiceV2.entity.users.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersRolesDto {

    private long id;
    private UsersDto usersDto;
    private Role role;

}
