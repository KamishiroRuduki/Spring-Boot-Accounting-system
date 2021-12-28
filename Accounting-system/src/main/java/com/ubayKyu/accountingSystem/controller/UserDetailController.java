package com.ubayKyu.accountingSystem.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
import com.ubayKyu.accountingSystem.dto.UserInfoInterface;
import com.ubayKyu.accountingSystem.entity.AccountingNote;
import com.ubayKyu.accountingSystem.entity.UserInfo;
import com.ubayKyu.accountingSystem.entity.UserInfo2;
import com.ubayKyu.accountingSystem.repository.UserInfoRepository2;
import com.ubayKyu.accountingSystem.service.FormatService;
import com.ubayKyu.accountingSystem.service.LoginService;
import com.ubayKyu.accountingSystem.service.UserInfoService;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserDetailController {
	@Autowired
	HttpSession session;
	@Autowired
	UserInfoService UserInfoService;
	@GetMapping("/SystemAdmin/UserDetail")
	public String UserDetail(@RequestParam(value = "userID", required = false) String userID, RedirectAttributes redirectAttrs,Model model) {
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck)//檢查是否有登入
		{
            String url = "/default";
            LoginService.LoginSessionRemove(session);
            return "redirect:" + url;
		}
		UserInfo2 user =  (UserInfo2) session.getAttribute("LoginState");
		if(user.getUserLevel() < 1 )//檢查權限
		{
			redirectAttrs.addFlashAttribute("message", "你的權限不足，無法訪問該頁面");
			return "redirect:/SystemAdmin/UserProfile";
		}
		if( userID != null)//編輯模式下把該使用者的資訊做回填
		{
			Optional<UserInfoInterface> userInfo=  UserInfoService.GetUserInfoInterfaceByUserID(userID);
			model.addAttribute("Account", userInfo.get().getaccount());
			model.addAttribute("Name", userInfo.get().getname());
			model.addAttribute("Email", userInfo.get().getemail());
			model.addAttribute("CreateTime", userInfo.get().getcreate_date());
			model.addAttribute("EditTime", userInfo.get().getedit_date());
			model.addAttribute("UserLevel", userInfo.get().getuser_level());
		}
		return "SystemAdmin/UserDetail";
	}

	@PostMapping("/SystemAdmin/UserDetail")
	public String AccountingNoteCreateOrUpdate( @RequestParam(value = "userID", required = false) String userID		 
			, @RequestParam(value = "txtAccount", required = false) String txtAccount
			, @RequestParam(value = "txtName", required = false) String txtName
			, @RequestParam(value = "txtEmail", required = false) String txtEmail
			, @RequestParam(value = "ddlUserLevel", required = false) Integer ddlUserLevel
			, @RequestParam(value = "hiddenCreateDate", required = false) String CreateDate
			,RedirectAttributes redirAttrs, Model model) {
		/*

		 */
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck)//檢查是否有登入
		{
			String url = "/default";
			LoginService.LoginSessionRemove(session);
			return "redirect:" + url;
		}


		//前台資料驗證
		String message = "";
		if(txtAccount == null || txtAccount.isEmpty())
			message += "帳號不能為空\n";
		if(userID == null && !UserInfoService.IsNotAccountCreated(txtAccount))//檢查帳號重複
			message += "此帳號已被使用\n";					
		if(txtName == null || txtName.isEmpty() || txtName.length() > 20)
			message += "姓名不能為空或超過20個字\n";
		if(txtEmail == null || txtEmail.isEmpty())
			message += "Email不能為空\n";
		
		
		if(!message.isEmpty())//檢查錯誤訊息是否為空，不為空return並跳出錯誤訊息
		{
			redirAttrs.addFlashAttribute("message", message);
			//判斷是新增還是編輯，決定回傳地址
			if(userID != null)
				return "redirect:/SystemAdmin/UserDetail?userID=" + userID;
			else
		   	    return "redirect:/SystemAdmin/UserDetail";
		}

		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");//定義LocalDateTime格式，不定義的話有些日期字串轉換會失敗
		UserInfo2 User= (UserInfo2) session.getAttribute("LoginState");	
		UserInfo2 UserInfo = new UserInfo2();//新增、編輯用的UserInfo
		//新增跟編輯不同的部分在controller先set，共同的部分到service再set
		if(userID != null) {
			UserInfo.setCreateDate(LocalDateTime.parse(CreateDate, formatter)) ;
			UserInfo.setEditDate(LocalDateTime.now());
			message = "編輯成功";
		}
		else {
			userID =UUID.randomUUID().toString();
			UserInfo.setCreateDate(LocalDateTime.now());
			message = "新增成功";
		}
		
		UserInfoService.SaveUserInfo(UserInfo,userID,txtAccount,txtName,txtEmail,ddlUserLevel);	   		
		if(User.getId().equals(userID) )//編輯自己的資料後，更新session
		{
			UserInfo2 NewSessionUserInfo = UserInfoService.findByUserID(userID).get();
			session.setAttribute("LoginState", NewSessionUserInfo);
		}

		redirAttrs.addFlashAttribute("message", message);
		return "redirect:/SystemAdmin/UserDetail?userID=" + userID;
		

	}
}







