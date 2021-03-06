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
import com.unilogcorp.bookcasestudy.dto.UserApiRequest;
import com.unilogcorp.bookcasestudy.dto.UserStatus;
import com.unilogcorp.bookcasestudy.service.UserService;
import com.unilogcorp.bookcasestudy.utils.BindingResultHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
public class UserApi {
    private static final String CREATE_USER_URL = "/api/v1/bookcasestudy/user/create";

    @Autowired
    private UserService userService;

    @RequestMapping (value = CREATE_USER_URL, method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserApiRequest userApiRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new BindingResultHelper().getErrorMessage(CREATE_USER_URL, bindingResult), HttpStatus.BAD_REQUEST);
        }
        try {
            ApiResponse apiResponse=new ApiResponse();
            UserStatus userStatus = userService.createUser(userApiRequest);
            if (userStatus.getStatus().equals(UserStatus.userStatus.USER_CREATED)){
                apiResponse.setStatus(ApiResponse.ApiStatus.USER_CREATED);
            }else {
                apiResponse.setStatus(ApiResponse.ApiStatus.USER_NOT_CREATED);
            }
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
