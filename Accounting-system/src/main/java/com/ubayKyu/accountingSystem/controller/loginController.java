package com.ubayKyu.accountingSystem.controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ubayKyu.accountingSystem.entity.UserInfo2;
import com.ubayKyu.accountingSystem.repository.UserInfoRepository2;
import com.ubayKyu.accountingSystem.service.LoginService;
import com.ubayKyu.accountingSystem.Const.UrlPath;

@Controller
public class loginController {
	@Autowired
	HttpSession session;
	@Autowired
	private UserInfoRepository2 UserInfoRepository2;
	@Autowired  //與service層進行互動
	private LoginService LoginService;
	UrlPath UrlPath;
	
	@GetMapping("/login")
	public String getLogin(Model model) {
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck)
		{
			return UrlPath.URL_LOGIN;
		}
		else {
			return "redirect:" + UrlPath.URL_USERPROFILE;
		}
	}
	
	
	
	// 登入(帳號、密碼)
	@PostMapping("/login")
	public String getLoginCl(@RequestParam("txtAccount") String account, @RequestParam("txtPWD") String password,
			RedirectAttributes redirAttrs,Model model) {
		boolean result = false;
		UserInfo2 userInfo = UserInfoRepository2.GetUserLogin(account, password);

		if (userInfo != null) {
			int userLevel = userInfo.getUserLevel();
			result = true;
			// 寫入session
			session.setAttribute("UserLevel", userLevel);
			session.setAttribute("LoginState", userInfo);
		}

		if (result == true) {// 如果成功
			redirAttrs.addFlashAttribute("message", "登入成功");
			return "redirect:" + UrlPath.URL_USERPROFILE; // 重新導向到指定的url
		} else {// 如果失敗
			redirAttrs.addFlashAttribute("message", "登入失敗，請檢查帳號或密碼是否正確");
			return "redirect:"+ UrlPath.URL_LOGIN; // 重新導向到指定的url
		}

	}
}