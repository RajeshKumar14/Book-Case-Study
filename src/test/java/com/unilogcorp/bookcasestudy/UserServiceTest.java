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
import com.unilogcorp.bookcasestudy.dto.UserApiRequest;
import com.unilogcorp.bookcasestudy.dto.UserStatus;
import com.unilogcorp.bookcasestudy.service.UserService;
import com.unilogcorp.bookcasestudy.utils.FileParser;
import lombok.extern.slf4j.Slf4j;

@Ignore
@Slf4j
@RunWith (SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser() {
        String resourceName = "User.json";
        JSONObject userJsonObject = new FileParser().JsonFileToJSONObject(resourceName);
        log.info("user json :{}", new Gson().toJson(prepareUser(userJsonObject)));
        UserStatus userStatus=userService.createUser(prepareUser(userJsonObject));
        Assert.assertTrue(userStatus.getStatus().equals(UserStatus.userStatus.USER_CREATED));
    }

    public UserApiRequest prepareUser(JSONObject userJsonObject) {
        return new UserApiRequest().builder().username(userJsonObject.get("username").toString()).password(userJsonObject.get("password").toString())
                         .role(userJsonObject.get("role").toString()).build();
    }
}
