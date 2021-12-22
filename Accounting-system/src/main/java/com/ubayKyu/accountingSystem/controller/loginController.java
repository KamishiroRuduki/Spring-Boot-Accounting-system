package com.ubayKyu.accountingSystem.controller;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ubayKyu.accountingSystem.dto.User;
import com.ubayKyu.accountingSystem.entity.UserInfo;
import com.ubayKyu.accountingSystem.entity.UserInfo2;
import com.ubayKyu.accountingSystem.repository.UserInfoRepository2;
import com.ubayKyu.accountingSystem.service.LoginService;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class loginController {
	@Autowired
	HttpSession session;
    @Autowired
    private UserInfoRepository2 UserInfoRepository2;
	@Autowired  //與service層進行互動
	private LoginService LoginService;
	
	@GetMapping("/login")
	public String getLogin(Model model) {
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck)
		{
			return "login.html";
		}
		else {
            String url = "/SystemAdmin/UserProfile";
            return "redirect:" + url;
		}
	}
	
	
	
	//@RequestMapping("/login")
    // 登入(帳號、密碼)
	@PostMapping("/login")
    public String getLoginCl(@RequestParam("txtAccount") String account, @RequestParam("txtPWD") String password,
    		RedirectAttributes redirAttrs,Model model) {
//RedirectAttributes redirAttrs,
        boolean result = false;
        UserInfo2 userInfo = UserInfoRepository2.GetUserLogin(account, password);

        if (userInfo != null) {
        	int userLevel = userInfo.getUserLevel();
            result = true;
            // 寫入session
            String state = "Suscess";
        	session.setAttribute("UserLevel", userLevel);
            session.setAttribute("LoginState", userInfo);
        }

        if (result == true) {// 如果成功
        	redirAttrs.addFlashAttribute("message", "登入成功");
            String url = "/SystemAdmin/UserProfile"; // 重新導向到指定的url
            return "redirect:" + url; // 重新導向到指定的url
        } else {// 如果失敗
        	
        	redirAttrs.addFlashAttribute("message", "バカなの、死ぬの?");
            String url = "/default";
            return "redirect:/login"; // 重新導向到指定的url
        }

    }
}