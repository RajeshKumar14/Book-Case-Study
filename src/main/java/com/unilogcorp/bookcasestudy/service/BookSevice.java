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

    public BookStatus updateBook(BookApiRequest bookApiRequest) throws UsernameNotFoundException {
        BookStatus bookStatus = new BookStatus();
        try {
            String username = userService.getLoggedInUsername();
            if (username!=null){
                bookPersistenceService.updateBookByUsername(bookApiRequest,username);
                bookStatus.setStatus(BookStatus.bookStatus.BOOK_UPDATED);
            }else {
                bookStatus.setStatus(BookStatus.bookStatus.INVALID_USER);
            }
        } catch (Exception e) {
            bookStatus.setStatus(BookStatus.bookStatus.BOOK_NOT_UPDATED);
        }
        return bookStatus;
    }

    public BookStatus getAllBook() throws UsernameNotFoundException {
        BookStatus bookStatus = new BookStatus();
        try {
            String username = userService.getLoggedInUsername();
            if (username!=null){
                bookStatus.setData(bookPersistenceService.getAllBook());
                bookStatus.setStatus(BookStatus.bookStatus.GET_LIST_OF_BOOK_SUCCESS);
            }else {
                bookStatus.setStatus(BookStatus.bookStatus.INVALID_USER);
            }
        } catch (Exception e) {
            bookStatus.setStatus(BookStatus.bookStatus.GET_LIST_OF_BOOK_FAIL);
        }
        return bookStatus;
    }

    public BookStatus deleteBookById(long id) throws UsernameNotFoundException {
        BookStatus bookStatus = new BookStatus();
        try {
            String username = userService.getLoggedInUsername();
            if (username!=null){
                bookPersistenceService.deleteBookById(id);
                bookStatus.setStatus(BookStatus.bookStatus.BOOK_DELETED);
            }else {
                bookStatus.setStatus(BookStatus.bookStatus.INVALID_USER);
            }
        } catch (Exception e) {
            bookStatus.setStatus(BookStatus.bookStatus.BOOK_NOT_DELETED);
        }
        return bookStatus;
    }

    private Book prepareBook(BookApiRequest bookApiRequest, String username) {
        return new Book().builder().bookName(bookApiRequest.getBookName()).authors(bookApiRequest.getAuthors()).belongsTo(username)
                         .created_at(new Timestamp(new Date().getTime())).updated_at(new Timestamp(new Date().getTime())).build();
    }
}
