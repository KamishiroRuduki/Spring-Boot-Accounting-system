package com.ubayKyu.accountingSystem.controller;


import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ubayKyu.accountingSystem.dto.UserInfoInterface;
import com.ubayKyu.accountingSystem.entity.UserInfo2;
import com.ubayKyu.accountingSystem.service.LoginService;
import com.ubayKyu.accountingSystem.service.UserInfoService;
import com.ubayKyu.accountingSystem.service.common.LoggerService;
import com.ubayKyu.accountingSystem.Const.UrlPath;

@Controller
public class UserListController {
	
	@Autowired
	HttpSession session;
	@Autowired
	UserInfoService UserInfoService;
	@Autowired
	LoggerService LoggerService;
	UrlPath UrlPath;
	
	@GetMapping("/SystemAdmin/UserList")
	public String UserList(RedirectAttributes redirectAttrs,Model model) {
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck)
		{
			LoginService.LoginSessionRemove(session);
			return "redirect:" + UrlPath.URL_DEFAULT;
		}
		UserInfo2 user =  (UserInfo2) session.getAttribute("LoginState");
		if(user.getUserLevel() < 1 )//檢查使用者權限
		{
			redirectAttrs.addFlashAttribute("message", "你的權限不足，無法訪問該頁面");
			return "redirect:" + UrlPath.URL_USERPROFILE;
		}
		
		List<UserInfoInterface> userInfoList = UserInfoService.getUserInfoInterface();
		model.addAttribute("userInfoListTable", userInfoList);        
		return UrlPath.URL_USERLIST;
	}
	
	@PostMapping("/SystemAdmin/UserList")
	public String userListDel(Model model,
			@RequestParam(value ="ckbDelete", required = false) String[] userIDsForDel,
			RedirectAttributes redirectAttrs) {

		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck)
		{
			LoginService.LoginSessionRemove(session);
			return "redirect:" + UrlPath.URL_DEFAULT;
		}

		//取得登入者資訊
		UserInfo2 user = (UserInfo2)session.getAttribute("LoginState");
		String currentUserID = user.getId();
		String currentAccount = user.getAccount();
		String delThisUser = null;
		String messageLog = "";
		if(userIDsForDel != null) {
			for(String eachUserID : userIDsForDel) {
				Optional<UserInfo2> userInfoToDel = UserInfoService.findByUserID(eachUserID);
				String account = userInfoToDel.get().getAccount();
				if(currentUserID.equals(eachUserID)) //若刪除自己，先記錄起自己的userid，迴圈結束後再做刪除
				{ 
					delThisUser = eachUserID;
				}
				else 
				{
					try {
						LoggerService.WriteLoggerFile("管理者 " + currentAccount + " 於 " + LocalDate.now() + " 刪除使用者 " + account);//將刪除訊息寫進LOG
					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}              
					UserInfoService.deleteUserInfoAccountingNoteAndCategoryByUserID(eachUserID);//刪除使用者
				}
				
			}
			
			if( delThisUser != null)//刪除自己的會員資料
			{
				//將刪除訊息寫進LOG
				try {
					LoggerService.WriteLoggerFile("管理者 " + currentAccount + " 於 " + LocalDate.now() + " 刪除使用者 " + currentAccount);
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				UserInfoService.deleteUserInfoAccountingNoteAndCategoryByUserID(delThisUser);//刪除使用者
				redirectAttrs.addFlashAttribute("message","本會員已刪除，回到預設頁");
				LoginService.LoginSessionRemove(session);
				return "redirect:" + UrlPath.URL_DEFAULT;
			}
			redirectAttrs.addFlashAttribute("message","已將選取之會員及其流水帳、分類刪除");
		}else
			redirectAttrs.addFlashAttribute("message","未選取任何項目");
		return "redirect:" + UrlPath.URL_USERLIST; 
	}

}






