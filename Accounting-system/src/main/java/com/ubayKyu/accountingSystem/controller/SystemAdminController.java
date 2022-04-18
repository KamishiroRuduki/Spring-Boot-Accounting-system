package com.ubayKyu.accountingSystem.controller;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ubayKyu.accountingSystem.service.LoginService;
import com.ubayKyu.accountingSystem.repository.UserInfoRepository2;
import com.ubayKyu.accountingSystem.repository.AccountingNoteRepository;
import com.ubayKyu.accountingSystem.Const.UrlPath;
@Controller
public class SystemAdminController {
	@Autowired
	HttpSession session;
	
	@Autowired
	private UserInfoRepository2 UserInfoRepository2;
	
	@Autowired
	private AccountingNoteRepository AccountingNoteRepository;
	UrlPath UrlPath;
	//登出按鈕
	@RequestMapping("/logout-button")
	public String Logout(Model model) {
		Object wkLoginID= session.getAttribute("LoginState");
		if( wkLoginID != null)
		LoginService.LoginSessionRemove(session);
		return "redirect:" + UrlPath.URL_DEFAULT;
	}

}


