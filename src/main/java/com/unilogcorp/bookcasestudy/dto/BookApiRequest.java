package com.unilogcorp.bookcasestudy.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookApiRequest {
    private String bookName;

    private String authors;
}
