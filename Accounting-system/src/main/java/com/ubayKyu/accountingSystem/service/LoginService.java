package com.ubayKyu.accountingSystem.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.example.smalldemo.dao.*;
//import com.example.smalldemo.model.*;

@Service
public class LoginService {
	@Autowired
	HttpSession session;

 public boolean Find(String name, String password) {
  boolean b = false;
  
  if (name.equals("test") && password.equals("test")) {// 兩種渠道的得到的資料進行比較
   b = true;
  }

  //List<UserBean> All = userdao.findAll();// 呼叫dao層方法，讀取資料庫資料
  //System.out.println("================" + All);
//  for (int i = 0; i <= All.size(); i++) {// 將資料庫中的資料全部拿出，一個一個比較
//
//   UserBean one = All.get(i);
//   if (name.equals(one.getName()) && password.equals(one.getpassword())) {// 兩種渠道的得到的資料進行比較
//
//    b = true;
//    break;
//   } else {
//
//    b = false;
//    break;
//   }
//
//  }

  return b;
 }

public static void LoginSessionRemove(HttpSession session) {
	 session.removeAttribute("LoginState");

	 }

public static boolean LoginSessionCheck(HttpSession session) {
	Object wkLoginID= session.getAttribute("LoginState");
	if( wkLoginID != null)
		return true;
	else
		return false;

	 }
}
