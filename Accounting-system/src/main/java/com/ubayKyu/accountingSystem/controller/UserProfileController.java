package com.ubayKyu.accountingSystem.controller;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ubayKyu.accountingSystem.dto.User;
import com.ubayKyu.accountingSystem.entity.UserInfo;
import com.ubayKyu.accountingSystem.entity.UserInfo2;
import com.ubayKyu.accountingSystem.service.LoginService;
import com.ubayKyu.accountingSystem.service.UserInfoService;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserProfileController {
	@Autowired
	HttpSession session;
    @Autowired
    UserInfoService UserInfoService;
    
	@GetMapping("/SystemAdmin/UserProfile")
	public String UserProfile(Model model) {
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck)//檢查登入
		{
            String url = "/default";
            LoginService.LoginSessionRemove(session);
            return "redirect:" + url;
		}
		UserInfo2 user =  (UserInfo2) session.getAttribute("LoginState");//從session取得使用者資料
		Optional<UserInfo2> userInfo = UserInfoService.findByUserID(user.getId());//用ID去DB取得使用者資料
		
		//把資料傳至前台
		model.addAttribute("Account", userInfo.get().getAccount());
		model.addAttribute("name", userInfo.get().getName());
		model.addAttribute("email", userInfo.get().getEmail());
		return "SystemAdmin/UserProfile";
	}

    @PostMapping("/SystemAdmin/UserProfile")
    public String UserProfilePageUpdate(Model model,
                                        @RequestParam(value="txtName", required = false) String txtName, 
                                        @RequestParam(value="txtEmail", required = false) String txtEmail, 
                                        RedirectAttributes redirectAttrs) {

		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck) //檢查登入
		{
            String url = "/default";
            LoginService.LoginSessionRemove(session);
            return "redirect:" + url;
		}
		//驗證傳進來的資料
		else if( txtName == null || txtName.isEmpty() || txtName.length() > 20) //檢查名字長度
		{
			redirectAttrs.addFlashAttribute("message", "名字不能為空、名字長度不能超過20文字");
			return "redirect:/SystemAdmin/UserProfile";
		}
		else if( txtEmail == null || txtEmail.isEmpty() ||txtEmail.length() > 100) //檢查email長度
		{
			redirectAttrs.addFlashAttribute("message", "Email不能為空、Email長度不能超過100文字");
			return "redirect:/SystemAdmin/UserProfile";
		}

        //取得登入者資訊
		UserInfo2 user =  (UserInfo2) session.getAttribute("LoginState");
		
        //把資料傳至DB做更新
        UserInfoService.UpdateUserProfile(user.getId(), txtName, txtEmail);
        redirectAttrs.addFlashAttribute("message", "個人資訊修改成功");

        
        return "redirect:/SystemAdmin/UserProfile";
    }

}






