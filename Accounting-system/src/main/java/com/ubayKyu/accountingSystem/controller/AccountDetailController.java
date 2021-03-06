package com.ubayKyu.accountingSystem.controller;

import java.time.LocalDateTime;
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

import com.ubayKyu.accountingSystem.entity.AccountingNote;
import com.ubayKyu.accountingSystem.entity.Category;
import com.ubayKyu.accountingSystem.entity.UserInfo2;
import com.ubayKyu.accountingSystem.service.AccountingNoteService;
import com.ubayKyu.accountingSystem.service.CategoryService;
import com.ubayKyu.accountingSystem.service.LoginService;
import com.ubayKyu.accountingSystem.service.common.FormatService;
import com.ubayKyu.accountingSystem.Const.UrlPath;


@Controller
public class AccountDetailController {
	@Autowired
	HttpSession session;
	@Autowired
	private CategoryService CategoryService;
	@Autowired
	private AccountingNoteService AccountingNoteService;
	private UrlPath UrlPath;
	
	@GetMapping("/SystemAdmin/AccountingDetail")
	public String AccountDetail(@RequestParam(value = "accID", required = false) String accID,Model model) {
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck)
		{
			LoginService.LoginSessionRemove(session);
			return "redirect:" + UrlPath.URL_DEFAULT;
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
		return UrlPath.URL_ACCOUNTINGDETAIL;
	}

	@PostMapping("/SystemAdmin/AccountingDetail")
	public String AccountingNoteCreateOrUpdate( @RequestParam(value = "accID", required = false) String accID	//???????????????????????????????????????????????????	 
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
			LoginService.LoginSessionRemove(session);
			return "redirect:" + UrlPath.URL_DEFAULT;
		}
		//-------------------------??????????????????????????????????????????return?????????????????????-------------------------------
		Integer amount = FormatService.parseIntOrNull(txtAmount);
		String message = "";
		if(amount == null)
			message += "??????????????????\n";

		if( txtCaption == null || txtCaption.isEmpty())
			message += "??????????????????\n";

		if(amount != null && (amount > 10000000 || amount < 0))
			message += "????????????????????????1000???????????????\n";

		if(!message.isEmpty())
		{
			redirAttrs.addFlashAttribute("message", message);
			if(accID != null)
				return "redirect:" + UrlPath.URL_ACCOUNTINGDETAIL+ "?accID=" + accID;
			else 
				return "redirect:"+ UrlPath.URL_ACCOUNTINGDETAIL;
		}
		//-------------------------??????????????????????????????????????????return?????????????????????-------------------------------
		UserInfo2 User= (UserInfo2) session.getAttribute("LoginState");	
		Integer accountingid = FormatService.parseIntOrNull(accID); //??????accID????????????????????????????????????NULL
		AccountingNote accountingNote = new AccountingNote();//??????????????????????????????????????????
		if(!categoryid.isEmpty()) //???????????????????????????????????????
			accountingNote.setCategoryID(categoryid);
		accountingNote.setActType(actType);
		accountingNote.setAmount(amount);
		accountingNote.setCaption(txtCaption);
		accountingNote.setBody(txtBody);		
		accountingNote.setUserID(User.getId());
		
	    message = "????????????";
		if(accountingid == null)//???????????????????????????
		{
			accountingNote.setCreateDate(LocalDateTime.now());
			message = "????????????";			
		}
		else {
			accountingNote.setAccID(accountingid);
			accountingNote.setCreateDate(LocalDateTime.parse(accDatetime));		
		}
		accID = (AccountingNoteService.saveAccountingNote(accountingNote)).toString();
		redirAttrs.addFlashAttribute("message", message);
		return "redirect:" + UrlPath.URL_ACCOUNTINGDETAIL+ "?accID=" + accID;
		

	}
}




