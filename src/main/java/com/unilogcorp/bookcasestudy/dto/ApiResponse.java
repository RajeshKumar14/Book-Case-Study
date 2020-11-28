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
public class ApiResponse {
    public enum ApiStatus {
        USER_CREATED,
        USER_NOT_CREATED,
        BOOK_CREATED,
        BOOK_NOT_CREATED,
        BOOK_UPDATED,
        BOOK_NOT_UPDATED,
    }

    @JsonProperty ("status")
    private ApiStatus status;

    @JsonProperty ("errorMsg")
    private Object errorMsg;

    @JsonProperty ("data")
    private Object data;
}
