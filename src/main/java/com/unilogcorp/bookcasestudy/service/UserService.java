package com.unilogcorp.bookcasestudy.service;

import java.sql.Timestamp;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.unilogcorp.bookcasestudy.dto.DBOperationsStatus;
import com.unilogcorp.bookcasestudy.dto.UserApiRequest;
import com.unilogcorp.bookcasestudy.dto.UserStatus;
import com.unilogcorp.bookcasestudy.model.User;
import com.unilogcorp.bookcasestudy.security.MyUserDetails;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserPersistenceService userPersistenceService;

    public UserStatus createUser(UserApiRequest userApiRequest) throws UsernameNotFoundException {
        UserStatus userStatus = new UserStatus();
        try {
            User user = prepareUser(userApiRequest);
            DBOperationsStatus dbOperationsStatus = userPersistenceService.createUser(user);
            if (dbOperationsStatus.getStatus().equals(DBOperationsStatus.dbOperationsStatus.USER_CREATED)) {
                userStatus.setStatus(UserStatus.userStatus.USER_CREATED);
            } else {
                userStatus.setStatus(UserStatus.userStatus.USER_NOT_CREATED);
            }
        } catch (Exception e) {
            userStatus.setStatus(UserStatus.userStatus.USER_NOT_CREATED);
        }
        return userStatus;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userPersistenceService.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User is not Authenticated!");
        }
        return new MyUserDetails(user);
    }

    private User prepareUser(UserApiRequest userApiRequest) {
        return new User().builder().username(userApiRequest.getUsername()).password(userApiRequest.getPassword()).role(userApiRequest.getRole())
                         .enabled(Boolean.TRUE).created_at(new Timestamp(new Date().getTime())).updated_at(new Timestamp(new Date().getTime()))
                         .build();
    }

    public String getLoggedInUsername() {
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
