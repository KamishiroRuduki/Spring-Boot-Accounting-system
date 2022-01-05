package com.ubayKyu.accountingSystem.service;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//import org.springframework.web.bind.annotation.ModelAttribute;



@Service
public class LoginService {
	@Autowired
	static
	LoggerService LoggerService;
	@Autowired
	HttpSession session;
	

public static void LoginSessionRemove(HttpSession session) {//刪除session內的登入資訊
	 session.removeAttribute("LoginState");

	 }

public static boolean LoginSessionCheck(HttpSession session) {//登入驗證
	Object wkLoginID= session.getAttribute("LoginState");
	if( wkLoginID != null)
		return true;
	else
		return false;

	 }
}
