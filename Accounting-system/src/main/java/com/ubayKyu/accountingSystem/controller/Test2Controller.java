package com.ubayKyu.accountingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ubayKyu.accountingSystem.dto.User;
import com.ubayKyu.accountingSystem.service.UserInfoService;
import com.ubayKyu.accountingSystem.entity.UserInfo2;
import com.ubayKyu.accountingSystem.repository.UserInfoRepository2;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/default")
public class Test2Controller {

    @Autowired
    private UserInfoRepository2 UserInfoRepository2;

    @GetMapping("/default")
    public List<UserInfo2> getAll() {
        return UserInfoRepository2.findAll();
        
    }
}

