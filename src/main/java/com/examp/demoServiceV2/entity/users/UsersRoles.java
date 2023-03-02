package com.examp.demoServiceV2.entity.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersRoles {

    private long        id;
    private Integer     userId;
    private Integer     roles;

    public static final String TABLE =      "users_roles";
    public static final String ID =         "id";
    public static final String USERS_ID =   "users_id";
    public static final String ROLES_ID =   "roles_id";

}
