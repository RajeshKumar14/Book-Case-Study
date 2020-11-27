package com.unilogcorp.bookcasestudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.unilogcorp.bookcasestudy.dto.DBOperationsStatus;
import com.unilogcorp.bookcasestudy.model.User;
import com.unilogcorp.bookcasestudy.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserPersistenceService {
    @Autowired
    private UserRepository userRepository;

    public DBOperationsStatus createUser(User user) throws UsernameNotFoundException {
        DBOperationsStatus dbOperationsStatus = new DBOperationsStatus();
        try {
            userRepository.save(user);
            dbOperationsStatus.setStatus(DBOperationsStatus.dbOperationsStatus.USER_CREATED);
        } catch (Exception e) {
            String message = "Error in save user Data in DB:";
            log.error(message, e);
            dbOperationsStatus.setStatus(DBOperationsStatus.dbOperationsStatus.USER_NOT_CREATED);
        }
        return dbOperationsStatus;
    }

    public User getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
    }
}
