package com.ubayKyu.accountingSystem.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.ubayKyu.accountingSystem.entity.UserInfo2;
import com.ubayKyu.accountingSystem.service.LoginService;
import com.ubayKyu.accountingSystem.dto.CategoryInterFace;
import com.ubayKyu.accountingSystem.service.CategoryService;
import com.ubayKyu.accountingSystem.Const.UrlPath;

@Controller
@RequestMapping("/SystemAdmin/CategoryList")
public class CategoryListController {
	@Autowired
	HttpSession session;
	@Autowired
	private CategoryService CategoryService;
	UrlPath UrlPath;

	@RequestMapping(method = RequestMethod.GET)
	public String CategoryList(Model model) {
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if (!loginCheck)// 檢查是否有登入
		{
			LoginService.LoginSessionRemove(session);
			return "redirect:" + UrlPath.URL_DEFAULT;
		}
		
		UserInfo2 User= (UserInfo2) session.getAttribute("LoginState");
		// 用從UDL取得的ID去DB撈資料，並放進自定義的分類Model
		List<CategoryInterFace> CategoryModelList = CategoryService.getCategoryModelByUserid(User.getId());
		//List<CategoryInterFace> CategoryModelList = CategoryRepository.FindCategoryModelListByUserid(userid);
		model.addAttribute("CategoryModelList", CategoryModelList);// 將自定義的分類Model傳至前台
		return UrlPath.URL_CATEGORYLIST;
	}

	/*
	 分類頁表頁，做分類刪除
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String CategoryListDel(@RequestParam(value = "chbCategoryDel", required = false) String[] categoryDel, 
			RedirectAttributes redirAttrs,Model model) {		
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if (!loginCheck)// 檢查是否有登入
		{
			LoginService.LoginSessionRemove(session);
			return "redirect:" + UrlPath.URL_DEFAULT;
		}
		if (categoryDel != null ) {//假如checkbox有被勾選
			for (String categoryid : categoryDel) {
				int count = CategoryService.getCategoryCountByCategoryid(categoryid);
				
				if(count == 0) { //沒有流水帳套用該分類才能刪除
					CategoryService.deleteCategoryByCategoryid(categoryid);
				}

			}
			redirAttrs.addFlashAttribute("message", "刪除成功");
		}
		else
			redirAttrs.addFlashAttribute("message", "並未勾選");

		
		return "redirect:" + UrlPath.URL_CATEGORYLIST;
	}

}
