package com.ubayKyu.accountingSystem.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ubayKyu.accountingSystem.repository.AccountingNoteRepository;
import com.ubayKyu.accountingSystem.repository.CategoryRepository;
import com.ubayKyu.accountingSystem.repository.UserInfoRepository2;
import com.ubayKyu.accountingSystem.dto.CategoryModel;
import com.ubayKyu.accountingSystem.entity.Category;
import com.ubayKyu.accountingSystem.entity.UserInfo2;
import com.ubayKyu.accountingSystem.service.LoginService;
import com.ubayKyu.accountingSystem.service.UserInfoService;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.ubayKyu.accountingSystem.dto.CategoryInterFace;
import com.ubayKyu.accountingSystem.service.CategoryService;

@Controller
@RequestMapping("/SystemAdmin/CategoryList")
public class CategoryListController {
	@Autowired
	HttpSession session;
	@Autowired
	private CategoryService CategoryService;

	@RequestMapping(method = RequestMethod.GET)
	public String CategoryList(Model model) {
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if (!loginCheck)// 檢查是否有登入
		{
			String url = "/default";
			LoginService.LoginSessionRemove(session);
			return "redirect:" + url;
		}
		
		UserInfo2 User= (UserInfo2) session.getAttribute("LoginState");
		// 用從UDL取得的ID去DB撈資料，並放進自定義的分類Model
		List<CategoryInterFace> CategoryModelList = CategoryService.getCategoryModelByUserid(User.getId());
		//List<CategoryInterFace> CategoryModelList = CategoryRepository.FindCategoryModelListByUserid(userid);
		model.addAttribute("CategoryModelList", CategoryModelList);// 將自定義的分類Model傳至前台
		return "SystemAdmin/CategoryList.html";
	}

	/*
	 分類頁表頁，做分類刪除
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String CategoryListDel(@RequestParam(value = "chbCategoryDel", required = false) String[] categoryDel, 
			RedirectAttributes redirAttrs,Model model) {		
		//@RequestParam(value = "categoryCount", required = false) String[] categoryCount,
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if (!loginCheck)// 檢查是否有登入
		{
			String url = "/default";
			LoginService.LoginSessionRemove(session);
			return "redirect:" + url;
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

		
		String url = "/SystemAdmin/CategoryList";
		return "redirect:" + url;
	}

}
