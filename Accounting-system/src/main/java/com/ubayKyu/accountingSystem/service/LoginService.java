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
	static
    String username;
	@Autowired
	HttpSession session;
	

public static void LoginSessionRemove(HttpSession session) {
	 session.removeAttribute("LoginState");

	 }

public static boolean LoginSessionCheck(HttpSession session) {
	Object wkLoginID= session.getAttribute("LoginState");
//	for( int i = 0; i < 5; i++) {
//    try {
//		LoggerService.WriteLoggerFile("test"+i);
//	} catch (Exception e) {
//		// TODO 自動生成された catch ブロック
//		e.printStackTrace();
//	}
//	}
	if( wkLoginID != null)
		return true;
	else
		return false;

	 }
}
