package com.examp.demoServiceV2.entity.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private long        id;
    private String      name;

    public static final String TABLE =      "roles";
    public static final String ID =         "id";
    public static final String NAME =       "name";

}
