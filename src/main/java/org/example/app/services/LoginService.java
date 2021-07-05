package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.LoginForm;
import org.example.web.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private Logger logger = Logger.getLogger(LoginService.class);

    private final RegisterService registerService;

    @Autowired
    public LoginService(RegisterService registerService) {
        this.registerService = registerService;
    }

    public boolean authenticate(LoginForm loginFrom) {
        logger.info("try auth with user-form: " + loginFrom);
        boolean resultAuthAdmin = loginFrom.getUsername().equals("root") && loginFrom.getPassword().equals("123");
        boolean resultAuthUser = false;
        for (User user : registerService.getAllUsers()) {
            if (user.getUser().equals(loginFrom.getUsername())
                    && user.getPassword().equals(loginFrom.getPassword())) {
                resultAuthUser = true;
                break;
            }
        }
        return resultAuthAdmin || resultAuthUser;
    }
}
