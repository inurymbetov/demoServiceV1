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

    private long id;
    private String uid;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean active;

}
