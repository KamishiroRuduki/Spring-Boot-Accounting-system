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
	
	@GetMapping("/login")
	public String getLogin(Model model) {
		//, @ModelAttribute("message") String message
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck)
		{
		/*	if(message != null)
			model.addAttribute("test", message);*/
			return "login.html";
		}
		else {
            String url = "/SystemAdmin/AccountList";
            return "redirect:" + url;
		}
	}
	
	@Autowired  //與service層進行互動
	private LoginService LoginService;
	
	
/*	@RequestMapping("/login")
	public String getLoginCl(@RequestParam("txtAccount") String name,@RequestParam("txtPWD") String password){
		
		boolean b;
		b=LoginService.Find(name, password);//呼叫service層的方法
		
		
		if(b){
			session.setAttribute("Name", name);
			session.setAttribute("Password", password);
			String url ="/SystemAdmin/AccountList";
            return "redirect:" + url; // 重新導向到指定的url
		}
		else {	
		String url ="/default";
        return "redirect:" + url; // 重新導向到指定的url
			
		}
}*/
	
	//@RequestMapping("/login")
    // 登入(帳號、密碼)
	@PostMapping("/login")
    public String getLoginCl(@RequestParam("txtAccount") String account, @RequestParam("txtPWD") String password,
    		RedirectAttributes redirAttrs,Model model) {
//RedirectAttributes redirAttrs,
        boolean result = false;
        UserInfo2 userInfo = UserInfoRepository2.GetUserLogin(account, password);

        if (userInfo != null) {
            result = true;
            // 寫入session
            String state = "Suscess";
            session.setAttribute("LoginState", userInfo);
        }

        if (result == true) {// 如果成功
        	redirAttrs.addFlashAttribute("message", "登入成功");
        	//model.addAttribute("message", "登入成功");
            String url = "/SystemAdmin/AccountList"; // 重新導向到指定的url
            return "redirect:" + url; // 重新導向到指定的url
        } else {// 如果失敗
        	
        	redirAttrs.addFlashAttribute("message", "バカなの、死ぬの?");
			//model.addAttribute("message", "バカなの、死ぬの?");
            String url = "/default";
            return "redirect:/login"; // 重新導向到指定的url
        }

    }
}