package com.unilogcorp.bookcasestudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.unilogcorp.bookcasestudy.dto.BookApiRequest;
import com.unilogcorp.bookcasestudy.dto.DBOperationsStatus;
import com.unilogcorp.bookcasestudy.model.Book;
import com.unilogcorp.bookcasestudy.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookPersistenceService {
    @Autowired
    private BookRepository bookRepository;

    public DBOperationsStatus createBook(Book book) throws UsernameNotFoundException {
        DBOperationsStatus dbOperationsStatus = new DBOperationsStatus();
        try {
            bookRepository.save(book);
            dbOperationsStatus.setStatus(DBOperationsStatus.dbOperationsStatus.BOOK_CREATED);
        } catch (Exception e) {
            String message = "Error in save Book Data in DB:";
            log.error(message, e);
            dbOperationsStatus.setStatus(DBOperationsStatus.dbOperationsStatus.BOOK_NOT_CREATED);
        }
        return dbOperationsStatus;
    }

    public DBOperationsStatus updateBookByUsername(BookApiRequest bookApiRequest, String username) throws UsernameNotFoundException {
        DBOperationsStatus dbOperationsStatus = new DBOperationsStatus();
        try {
            bookRepository.updateBookByUsername(bookApiRequest.getBookName(), bookApiRequest.getAuthors(), username);
            dbOperationsStatus.setStatus(DBOperationsStatus.dbOperationsStatus.BOOK_UPDATED);
        } catch (Exception e) {
            String message = "Error in updating Book Data from DB:";
            log.error(message, e);
            dbOperationsStatus.setStatus(DBOperationsStatus.dbOperationsStatus.BOOK_NOT_UPDATED);
        }
        return dbOperationsStatus;
    }

    public DBOperationsStatus getAllBook() throws UsernameNotFoundException {
        DBOperationsStatus dbOperationsStatus = new DBOperationsStatus();
        try {
            dbOperationsStatus.setBookList(bookRepository.findAll());
            dbOperationsStatus.setStatus(DBOperationsStatus.dbOperationsStatus.GET_LIST_OF_BOOK_SUCCESS);
        } catch (Exception e) {
            String message = "Error in getting Book Data from DB:";
            log.error(message, e);
            dbOperationsStatus.setStatus(DBOperationsStatus.dbOperationsStatus.GET_LIST_OF_BOOK_FAIL);
        }
        return dbOperationsStatus;
    }

    public DBOperationsStatus deleteBookById(long id) throws UsernameNotFoundException {
        DBOperationsStatus dbOperationsStatus = new DBOperationsStatus();
        try {
            bookRepository.deleteById(id);
            dbOperationsStatus.setStatus(DBOperationsStatus.dbOperationsStatus.BOOK_DELETED);
        } catch (Exception e) {
            String message = "Error in delete Book by id from DB:";
            log.error(message, e);
            dbOperationsStatus.setStatus(DBOperationsStatus.dbOperationsStatus.BOOK_NOT_DELETED);
        }
        return dbOperationsStatus;
    }

}
