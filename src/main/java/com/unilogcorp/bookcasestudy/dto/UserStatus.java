package com.unilogcorp.bookcasestudy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude (JsonInclude.Include.NON_NULL)
public class UserStatus {
    public enum userStatus {
        USER_CREATED,
        USER_NOT_CREATED
    }

    @JsonProperty ("status")
    private userStatus status;
}
