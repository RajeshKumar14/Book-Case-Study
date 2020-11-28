package com.unilogcorp.bookcasestudy;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.google.gson.Gson;
import com.unilogcorp.bookcasestudy.dto.BookApiRequest;
import com.unilogcorp.bookcasestudy.dto.BookStatus;
import com.unilogcorp.bookcasestudy.service.BookSevice;
import com.unilogcorp.bookcasestudy.utils.FileParser;
import lombok.extern.slf4j.Slf4j;

@Ignore
@Slf4j
@RunWith (SpringRunner.class)
@SpringBootTest
public class BookServiceTest {
    @Autowired
    private BookSevice bookSevice;

    @Test
    public void testCreateBook() {
        String resourceName = "Book.json";
        JSONObject bookJsonObject = new FileParser().JsonFileToJSONObject(resourceName);
        log.info("book json :{}", new Gson().toJson(prepareBook(bookJsonObject)));
        BookStatus bookStatus = bookSevice.createBook(prepareBook(bookJsonObject));
        Assert.assertTrue(bookStatus.getStatus().equals(BookStatus.bookStatus.BOOK_CREATED));
    }

    public BookApiRequest prepareBook(JSONObject bookJsonObject) {
        return new BookApiRequest().builder().bookName(bookJsonObject.get("bookName").toString()).authors(bookJsonObject.get("author").toString())
                                   .build();
    }
}
