package com.ubayKyu.accountingSystem.controller;

import java.util.List;

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

import com.ubayKyu.accountingSystem.repository.AccountingNoteRepository;
import com.ubayKyu.accountingSystem.repository.CategoryRepository;
import com.ubayKyu.accountingSystem.repository.UserInfoRepository2;
import com.ubayKyu.accountingSystem.dto.CategoryModel;
import com.ubayKyu.accountingSystem.entity.Category;
import com.ubayKyu.accountingSystem.entity.UserInfo;
import com.ubayKyu.accountingSystem.service.LoginService;
import com.ubayKyu.accountingSystem.service.UserInfoService;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.ubayKyu.accountingSystem.dto.CategoryInterFace;

@Controller
@RequestMapping("/SystemAdmin/CategoryList")
public class CategoryListController {
	@Autowired
	HttpSession session;
    @Autowired
    private CategoryRepository CategoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    
    @RequestMapping(method = RequestMethod.GET)
	public String CategoryList( @RequestParam(value="id") String userid,Model model) {
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck)//檢查是否有登入
		{
            String url = "/default";
            LoginService.LoginSessionRemove(session);
            return "redirect:" + url;
		}
		
		
		//用從UDL取得的ID去DB撈資料，並放進自定義的分類Model
		List<CategoryInterFace> CategoryModelList = CategoryRepository.FindCategoryModelListByUserid(userid); 
		 model.addAttribute("CategoryModelList", CategoryModelList);//將自定義的分類Model傳至前台
		return "SystemAdmin/CategoryList.html";
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String CategoryListDel(@RequestParam(value="id") String userid,@RequestParam(value ="chbCategoryDel", required = false) String[] categoryDel,Model model) {
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck)//檢查是否有登入
		{
            String url = "/default";
            LoginService.LoginSessionRemove(session);
            return "redirect:" + url;
		}
		if(categoryDel != null ) {
		for( String categoryid : categoryDel) {
			CategoryRepository.deleteById(categoryid);
		}
		}
		

		String url = "/SystemAdmin/CategoryList?id="+ userid;
		return "redirect:" + url;
	}
    

}






