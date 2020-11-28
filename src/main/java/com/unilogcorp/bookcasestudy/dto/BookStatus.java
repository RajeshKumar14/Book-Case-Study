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
public class BookStatus {
    public enum bookStatus {
        BOOK_CREATED,
        BOOK_NOT_CREATED,
        INVALID_USER,
        BOOK_UPDATED,
        BOOK_NOT_UPDATED,
    }

    @JsonProperty ("status")
    private bookStatus status;
}
