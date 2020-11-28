package com.unilogcorp.bookcasestudy.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.unilogcorp.bookcasestudy.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude (JsonInclude.Include.NON_NULL)
public class DBOperationsStatus {

    public enum dbOperationsStatus {
        USER_CREATED,
        USER_NOT_CREATED,
        BOOK_CREATED,
        BOOK_NOT_CREATED,
        BOOK_UPDATED,
        BOOK_NOT_UPDATED,
        GET_LIST_OF_BOOK_SUCCESS,
        GET_LIST_OF_BOOK_FAIL,
        BOOK_DELETED,
        BOOK_NOT_DELETED,
    }

    @JsonProperty ("status")
    private dbOperationsStatus status;

    @JsonProperty ("bookList")
    private List<Book> bookList;

}
