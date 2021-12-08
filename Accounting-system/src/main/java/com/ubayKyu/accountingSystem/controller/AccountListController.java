package com.ubayKyu.accountingSystem.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ubayKyu.accountingSystem.service.LoginService;
import com.ubayKyu.accountingSystem.dto.User;
import com.ubayKyu.accountingSystem.entity.UserInfo;
import com.ubayKyu.accountingSystem.service.UserInfoService;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AccountListController {
	@Autowired
	HttpSession session;
	
	@GetMapping("/SystemAdmin/AccountList")
	public String AccountList(Model model) {
		//, @ModelAttribute("message") String message
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck)
		{
            String url = "/default";
            LoginService.LoginSessionRemove(session);
            return "redirect:" + url;
		}
	/*		if(message != null)
		model.addAttribute("message", message);*/
		model.addAttribute("money", "4220");
		
		Object wkLoginID= session.getAttribute("LoginState");
		if(wkLoginID != null) 
		{
			model.addAttribute("Name", "Suscess");
			model.addAttribute("PWD", "Suscess");
		}
		return "SystemAdmin/AccountList.html";
	}

}



