package com.car.cloud.controller;

import com.car.cloud.dto.DetailDto;
import com.car.cloud.model.UserEntity;
import com.car.cloud.security.SecurityUtil;
import com.car.cloud.service.DetailService;
import com.car.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AccountController {

    private UserService userService;
    private DetailService detailService;

    @Autowired
    public AccountController(UserService userService, DetailService detailService) {
        this.userService = userService;
        this.detailService = detailService;
    }

    @GetMapping("/account")
    public String accountOverall(Model model) {
        UserEntity user;
        List<DetailDto> details = detailService.findDetailsByUser();
        String username = SecurityUtil.getSessionUser();
        user = userService.findByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("detail", details);
        return "account";
    }
}
