package com.ubayKyu.accountingSystem.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.ubayKyu.accountingSystem.dto.User;
import com.ubayKyu.accountingSystem.entity.UserInfo;
import com.ubayKyu.accountingSystem.service.LoginService;
import com.ubayKyu.accountingSystem.service.UserInfoService;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserDetailController {
	@Autowired
	HttpSession session;
	
	@GetMapping("/SystemAdmin/UserDetail")
	public String hello(Model model) {
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck)
		{
            String url = "/default";
            LoginService.LoginSessionRemove(session);
            return "redirect:" + url;
		}
		model.addAttribute("Account", "MizukiYukikaze");
		model.addAttribute("name", "大人ゆきかぜ");
		model.addAttribute("email", "Lilith@test.com");
		model.addAttribute("CreatTime", "2021/8/2 下午 09:24:16");
		model.addAttribute("UpateTime", "2021/8/5 下午 11:52:15");
		return "SystemAdmin/UserDetail";
	}

}







