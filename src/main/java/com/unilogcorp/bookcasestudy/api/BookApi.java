package com.unilogcorp.bookcasestudy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.unilogcorp.bookcasestudy.dto.ApiResponse;
import com.unilogcorp.bookcasestudy.dto.BookApiRequest;
import com.unilogcorp.bookcasestudy.dto.BookStatus;
import com.unilogcorp.bookcasestudy.service.BookSevice;
import com.unilogcorp.bookcasestudy.utils.BindingResultHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
public class BookApi {
    private static final String BOOK_URL = "/api/v1/bookcasestudy/book";

    @Autowired
    private BookSevice bookSevice;

    @RequestMapping (value = BOOK_URL, method = RequestMethod.POST)
    public ResponseEntity<?> createBook(@RequestBody BookApiRequest bookApiRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new BindingResultHelper().getErrorMessage(BOOK_URL, bindingResult), HttpStatus.BAD_REQUEST);
        }
        try {
            ApiResponse apiResponse=new ApiResponse();
            BookStatus bookStatus = bookSevice.createBook(bookApiRequest);
            if (bookStatus.getStatus().equals(BookStatus.bookStatus.BOOK_CREATED)){
                apiResponse.setStatus(ApiResponse.ApiStatus.BOOK_CREATED);
            }else {
                apiResponse.setStatus(ApiResponse.ApiStatus.BOOK_NOT_CREATED);
            }
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping (value = BOOK_URL, method = RequestMethod.PUT)
    public ResponseEntity<?> updateBook(@RequestBody BookApiRequest bookApiRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new BindingResultHelper().getErrorMessage(BOOK_URL, bindingResult), HttpStatus.BAD_REQUEST);
        }
        try {
            ApiResponse apiResponse=new ApiResponse();
            BookStatus bookStatus = bookSevice.updateBook(bookApiRequest);
            if (bookStatus.getStatus().equals(BookStatus.bookStatus.BOOK_UPDATED)){
                apiResponse.setStatus(ApiResponse.ApiStatus.BOOK_UPDATED);
            }else {
                apiResponse.setStatus(ApiResponse.ApiStatus.BOOK_NOT_UPDATED);
            }
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping (value = BOOK_URL, method = RequestMethod.GET)
    public ResponseEntity<?> getAllBook() {
        try {
            ApiResponse apiResponse=new ApiResponse();
            BookStatus bookStatus = bookSevice.getAllBook();
            if (bookStatus.getStatus().equals(BookStatus.bookStatus.GET_LIST_OF_BOOK_SUCCESS)){
                apiResponse.setStatus(ApiResponse.ApiStatus.GET_LIST_OF_BOOK_SUCCESS);
                apiResponse.setData(bookStatus.getData());
            }else {
                apiResponse.setStatus(ApiResponse.ApiStatus.GET_LIST_OF_BOOK_FAIL);
            }
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping (value = BOOK_URL, method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBookById() {
        try {
            ApiResponse apiResponse=new ApiResponse();
            BookStatus bookStatus = bookSevice.getAllBook();
            if (bookStatus.getStatus().equals(BookStatus.bookStatus.GET_LIST_OF_BOOK_SUCCESS)){
                apiResponse.setStatus(ApiResponse.ApiStatus.GET_LIST_OF_BOOK_SUCCESS);
                apiResponse.setData(bookStatus.getData());
            }else {
                apiResponse.setStatus(ApiResponse.ApiStatus.GET_LIST_OF_BOOK_FAIL);
            }
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
