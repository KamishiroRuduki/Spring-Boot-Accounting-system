package com.ubayKyu.accountingSystem.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ubayKyu.accountingSystem.entity.Category;
import com.ubayKyu.accountingSystem.entity.UserInfo2;
import com.ubayKyu.accountingSystem.service.LoginService;
import com.ubayKyu.accountingSystem.service.CategoryService;
import com.ubayKyu.accountingSystem.Const.UrlPath;

@Controller
public class CategoryDetailController {
	@Autowired
	HttpSession session;
	@Autowired
	private CategoryService CategoryService;
	private UrlPath UrlPath;
	@GetMapping("/SystemAdmin/CategoryDetail")
	public String CategoryDetail(@RequestParam(value = "CategoryID", required = false) String categoryid, Model model) {
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck)//驗證登入
		{
			LoginService.LoginSessionRemove(session);
			return "redirect:" + UrlPath.URL_DEFAULT;
		}
		if(categoryid != null) {//編輯模式作資料回填
			Optional<Category> category = CategoryService.getCategoryByCategoryid(categoryid);
			model.addAttribute("CategoryCaption", category.get().getCaption());
			model.addAttribute("CategoryDatetime", category.get().getCreateDate());
			
			if(category.get().getBody() != null)
			model.addAttribute("CategoryBody", category.get().getBody());
		}

		return UrlPath.URL_CATEGORYDETAIL;
	}
	
	@PostMapping("/SystemAdmin/CategoryDetail")
	public String CategoryCreateOrUpdate( @RequestParam(value = "CategoryID", required = false) String categoryid		 
			, @RequestParam(value = "txtCaption", required = false) String txtCaption
			, @RequestParam(value = "txtBody", required = false) String txtBody
			, @RequestParam(value = "hiddenCategory", required = false) String CategoryDatetime
			,RedirectAttributes redirAttrs, Model model) {
		/*

		 */
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck)//驗證登入
		{
			LoginService.LoginSessionRemove(session);
			return "redirect:" + UrlPath.URL_DEFAULT;
		}
		
		UserInfo2 User= (UserInfo2) session.getAttribute("LoginState");		
		if(!CategoryService.IsNotCategoryCaptionCreated(User.getId(), txtCaption,categoryid))//檢查標題重複
		{
			String url;
			//判斷是新增還是編輯，決定回傳地址
			if(categoryid != null)
			   url = UrlPath.URL_CATEGORYDETAIL +"?CategoryID=" + categoryid;
			else
			   url =  UrlPath.URL_CATEGORYDETAIL;
			redirAttrs.addFlashAttribute("message", "此分類標題已經存在");			
			return "redirect:" + url;
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime date = LocalDateTime.now();
		String message = "編輯成功";
		if(categoryid == null)
		{
			categoryid = UUID.randomUUID().toString();//新增模式，新增一個GUID
			message = "新增成功";
		}
		else
			date = LocalDateTime.parse(CategoryDatetime);//編輯模式，使用該分類的建立日期
		
		CategoryService.CategorySave(User.getId(), txtCaption, txtBody, categoryid,  date);
		redirAttrs.addFlashAttribute("message", message);
		return "redirect:" + UrlPath.URL_CATEGORYDETAIL +"?CategoryID=" + categoryid;
		

	}

}




