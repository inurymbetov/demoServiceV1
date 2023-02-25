package com.examp.demoServiceV2.entity.users;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    private long            id;
    private String          uid;
    private String          firstName;
    private String          lastName;
    private String          email;
    private String          password;
    private boolean         active;

    public static final String TABLE =              "users";
    public static final String ID =                 "id";
    public static final String UID =                "uid";
    public static final String FIRST_NAME =         "firstName";
    public static final String LAST_NAME =          "lastName";
    public static final String EMAIL =              "email";
    public static final String PASSWORD =           "password";
    public static final String ACTIVE =             "active";

}
