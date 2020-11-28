package com.unilogcorp.bookcasestudy.service;

import java.sql.Timestamp;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.unilogcorp.bookcasestudy.dto.BookApiRequest;
import com.unilogcorp.bookcasestudy.dto.BookStatus;
import com.unilogcorp.bookcasestudy.dto.DBOperationsStatus;
import com.unilogcorp.bookcasestudy.model.Book;

@Service
public class BookSevice {
    @Autowired
    private BookPersistenceService bookPersistenceService;

    @Autowired
    private UserService userService;

    public BookStatus createBook(BookApiRequest bookApiRequest) throws UsernameNotFoundException {
        BookStatus bookStatus = new BookStatus();
        try {
            String username = userService.getLoggedInUsername();
            if (username!=null){
                Book book = prepareBook(bookApiRequest, username);
                DBOperationsStatus dbOperationsStatus = bookPersistenceService.createBook(book);
                if (dbOperationsStatus.getStatus().equals(DBOperationsStatus.dbOperationsStatus.BOOK_CREATED)) {
                    bookStatus.setStatus(BookStatus.bookStatus.BOOK_CREATED);
                } else {
                    bookStatus.setStatus(BookStatus.bookStatus.BOOK_NOT_CREATED);
                }
            }else {
                bookStatus.setStatus(BookStatus.bookStatus.INVALID_USER);
            }
        } catch (Exception e) {
            bookStatus.setStatus(BookStatus.bookStatus.BOOK_NOT_CREATED);
        }
        return bookStatus;
    }

    private Book prepareBook(BookApiRequest bookApiRequest, String userId) {
        return new Book().builder().bookName(bookApiRequest.getBookName()).authors(bookApiRequest.getAuthors()).belongsTo(userId)
                         .created_at(new Timestamp(new Date().getTime())).updated_at(new Timestamp(new Date().getTime())).build();
    }
}
