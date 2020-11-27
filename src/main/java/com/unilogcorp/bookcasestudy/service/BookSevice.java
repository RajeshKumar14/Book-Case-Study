package com.unilogcorp.bookcasestudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookSevice {
    @Autowired
    private BookPersistenceService bookPersistenceService;
}
