package com.unilogcorp.bookcasestudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unilogcorp.bookcasestudy.repository.BookRepository;

@Service
public class BookPersistenceService {
    @Autowired
    private BookRepository bookRepository;

}
