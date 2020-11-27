package com.unilogcorp.bookcasestudy.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserApiRequest {
    private String username;

    private String password;

    private String role;
}
