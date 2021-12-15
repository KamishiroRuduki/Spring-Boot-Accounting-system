package com.ubayKyu.accountingSystem.controller;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.hibernate.hql.spi.id.cte.CteValuesListUpdateHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ubayKyu.accountingSystem.dto.User;
import com.ubayKyu.accountingSystem.entity.Category;
import com.ubayKyu.accountingSystem.entity.UserInfo;
import com.ubayKyu.accountingSystem.entity.UserInfo2;
import com.ubayKyu.accountingSystem.service.LoginService;
import com.ubayKyu.accountingSystem.service.UserInfoService;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.ubayKyu.accountingSystem.service.CategoryService;

@Controller
public class CategoryDetailController {
	@Autowired
	HttpSession session;
	@Autowired
	private CategoryService CategoryService;
	
	@GetMapping("/SystemAdmin/CategoryDetail")
	public String CategoryDetail(@RequestParam(value = "CategoryID", required = false) String categoryid, Model model) {
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck)
		{
			String url = "/default";
			LoginService.LoginSessionRemove(session);
			return "redirect:" + url;
		}
		if(categoryid != null) {
			Optional<Category> category = CategoryService.getCategoryByCategoryid(categoryid);
			model.addAttribute("CategoryCaption", category.get().getCaption());
			model.addAttribute("CategoryDatetime", category.get().getCreateDate());
			
			if(category.get().getBody() != null)
			model.addAttribute("CategoryBody", category.get().getBody());
		}

		return "SystemAdmin/CategoryDetail";
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
		if(!loginCheck)
		{
			String url = "/default";
			LoginService.LoginSessionRemove(session);
			return "redirect:" + url;
		}
		
		UserInfo2 User= (UserInfo2) session.getAttribute("LoginState");		
		if(!CategoryService.IsNotCategoryCaptionCreated(User.getId(), txtCaption,categoryid))//檢查標題重複
		{
			String url;
			//判斷是新增還是編輯，決定回傳地址
			if(categoryid != null)
			   url = "/SystemAdmin/CategoryDetail?CategoryID=" + categoryid;
			else
			   url = "/SystemAdmin/CategoryDetail";
			redirAttrs.addFlashAttribute("message", "此分類標題已經存在");			
			return "redirect:" + url;
		}
		

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
		String url = "/SystemAdmin/CategoryDetail?CategoryID=" + categoryid;
		return "redirect:" + url;
		

	}

}




