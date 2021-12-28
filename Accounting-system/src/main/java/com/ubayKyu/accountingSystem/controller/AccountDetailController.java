package com.ubayKyu.accountingSystem.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.Convert;
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
import com.ubayKyu.accountingSystem.entity.AccountingNote;
import com.ubayKyu.accountingSystem.entity.Category;
import com.ubayKyu.accountingSystem.entity.UserInfo;
import com.ubayKyu.accountingSystem.entity.UserInfo2;
import com.ubayKyu.accountingSystem.service.AccountingNoteService;
import com.ubayKyu.accountingSystem.service.CategoryService;
import com.ubayKyu.accountingSystem.service.FormatService;
import com.ubayKyu.accountingSystem.service.LoginService;
import com.ubayKyu.accountingSystem.service.UserInfoService;

import groovyjarjarantlr4.v4.runtime.ParserInterpreter;

import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AccountDetailController {
	@Autowired
	HttpSession session;
	@Autowired
	private CategoryService CategoryService;
	@Autowired
	private UserInfoService UserInfoService;
	@Autowired
	private AccountingNoteService AccountingNoteService;
	
	@GetMapping("/SystemAdmin/AccountingDetail")
	public String AccountDetail(@RequestParam(value = "accID", required = false) String accID,Model model) {
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck)
		{
            String url = "/default";
            LoginService.LoginSessionRemove(session);
            return "redirect:" + url;
		}
		Integer accountingid = FormatService.parseIntOrNull(accID);
		if( accountingid != null)
		{
			Optional<AccountingNote> accountingNote = AccountingNoteService.getAccountingNoteByID(accountingid);
			model.addAttribute("AccAmount", accountingNote.get().getAmount());
			model.addAttribute("AccCaption", accountingNote.get().getCaption());
			model.addAttribute("AccBody", accountingNote.get().getBody());
			model.addAttribute("AccCategory", accountingNote.get().getCategoryID());
			model.addAttribute("AccDatetime", accountingNote.get().getCreateDate());
			model.addAttribute("AccActType", accountingNote.get().getActType());
		}
		UserInfo2 user =  (UserInfo2) session.getAttribute("LoginState");
		List<Category> categoryList = CategoryService.getCategoryByUserID(user.getId());
        model.addAttribute("CategoryList", categoryList);
		return "SystemAdmin/AccountingDetail";
	}

	@PostMapping("/SystemAdmin/AccountingDetail")
	public String AccountingNoteCreateOrUpdate( @RequestParam(value = "accID", required = false) String accID	//用字串讀避免讀近來是數字以外的情況	 
			, @RequestParam(value = "txtCaption", required = false) String txtCaption
			, @RequestParam(value = "txtBody", required = false) String txtBody
			, @RequestParam(value = "txtAmount", required = false) String txtAmount
			, @RequestParam(value = "PrivateType", required = false) String categoryid
			, @RequestParam(value = "ActType", required = false) Integer actType
			, @RequestParam(value = "hiddenAccDate", required = false) String accDatetime
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
		//-------------------------驗證輸入資料，不正確的話直接return並跳出錯誤訊息-------------------------------
		Integer amount = Integer.parseInt(txtAmount);
		String message = "";
        if(amount == null)
            message += "金額不可為空\n";

        if(txtCaption.isEmpty() || txtCaption == null)
            message += "標題不可為空\n";
        
        if( amount > 10000000 || amount < 0)
        	message += "輸入金額不能超過1000萬或為負數\n";
        
        if(!message.isEmpty())
        {
            redirAttrs.addFlashAttribute("message", message);
            if(accID != null)
                return "redirect:/SystemAdmin/AccountingDetail?accID=" + accID;
            else 
                return "redirect:/SystemAdmin/AccountingDetail";
        }
		//-------------------------驗證輸入資料，不正確的話直接return並跳出錯誤訊息-------------------------------
		UserInfo2 User= (UserInfo2) session.getAttribute("LoginState");	
		Integer accountingid = FormatService.parseIntOrNull(accID); //檢查accID是否為數字，不是的話取得NULL
		AccountingNote accountingNote = new AccountingNote();//新增一個流水帳，並且放入資料
		if(!categoryid.isEmpty()) //如果不是未分類，才把值帶入
			accountingNote.setCategoryID(categoryid);
		accountingNote.setActType(actType);
		accountingNote.setAmount(amount);
		accountingNote.setCaption(txtCaption);
		accountingNote.setBody(txtBody);		
		accountingNote.setUserID(User.getId());
		
	    message = "編輯成功";
		if(accountingid == null)//判斷是新增還是編輯
		{
			accountingNote.setCreateDate(LocalDateTime.now());
			message = "新增成功";			
		}
		else {
			accountingNote.setAccID(accountingid);
			accountingNote.setCreateDate(LocalDateTime.parse(accDatetime));		
		}
		accID = (AccountingNoteService.saveAccountingNote(accountingNote)).toString();
		redirAttrs.addFlashAttribute("message", message);
		String url = "/SystemAdmin/AccountingDetail?accID=" + accID;
		return "redirect:" + url;
		

	}
}




