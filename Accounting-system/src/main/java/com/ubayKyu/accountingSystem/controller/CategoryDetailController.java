package com.ubayKyu.accountingSystem.controller;


import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ubayKyu.accountingSystem.dto.User;
import com.ubayKyu.accountingSystem.entity.Category;
import com.ubayKyu.accountingSystem.entity.UserInfo;
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
	public String CategoryDetail(@RequestParam(value = "id") String userid
			, @RequestParam(value = "CategoryID", required = false) String categoryid, Model model) {
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
			if(category.get().getBody() != null)
			model.addAttribute("CategoryBody", category.get().getBody());
		}

		return "SystemAdmin/CategoryDetail";
	}
	
	@PostMapping("/SystemAdmin/CategoryDetail")
	public String CategoryCreateOrUpdate(@RequestParam(value = "id") String userid
			, @RequestParam(value = "CategoryID", required = false) String categoryid		 
			, @RequestParam(value = "txtCaption", required = false) String txtCaption
			, @RequestParam(value = "txtBody", required = false) String txtBody
			, Model model) {
		/*

		 */
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck)
		{
            String url = "/default";
            LoginService.LoginSessionRemove(session);
            return "redirect:" + url;
		}
		if(categoryid != null) {		//更新分類	
			Optional<Category> category = CategoryService.getCategoryByCategoryid(categoryid);
			category.get().setCaption(txtCaption);
			category.get().setBody(txtBody);
			CategoryService.saveCategory(category.get());
			model.addAttribute("CategoryCaption", category.get().getCaption());
			if(category.get().getBody() != null)
			model.addAttribute("CategoryBody", category.get().getBody());
			String url = "/SystemAdmin/CategoryDetail?id=" + userid +"&CategoryID=" + categoryid;
			return "redirect:" + url;
		}
		else //新增分類
		{
			Category category = new Category();
			UUID uuid = UUID.randomUUID();
			category.setCategoryID(uuid.toString());
			category.setBody(txtBody);
			category.setCaption(txtCaption);
			category.setUserID(userid);
			category.setCreateDate(LocalDateTime.now());
			CategoryService.saveCategory(category);
			String url = "/SystemAdmin/CategoryDetail?id=" + userid+"&CategoryID=" + uuid;
			return "redirect:" + url;
		}


	}

}




