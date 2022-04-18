package com.ubayKyu.accountingSystem.controller;


import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ubayKyu.accountingSystem.service.AccountingNoteService;
import com.ubayKyu.accountingSystem.service.LoginService;
import com.ubayKyu.accountingSystem.dto.AccountingNoteInterFace;
import com.ubayKyu.accountingSystem.entity.UserInfo2;
import com.ubayKyu.accountingSystem.Const.UrlPath;

@Controller
public class AccountingListController {
	@Autowired
	HttpSession session;
	@Autowired
	private AccountingNoteService AccountingNoteService;
	private UrlPath UrlPath;
	@GetMapping("/SystemAdmin/AccountingList")
	public String AccountList(Model model) {
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck)//驗證登入
		{
			LoginService.LoginSessionRemove(session);
			return "redirect:" + UrlPath.URL_DEFAULT;
		}
		UserInfo2 User= (UserInfo2) session.getAttribute("LoginState");
		//傳至前台用的流水帳列表自訂MODEL
		List<AccountingNoteInterFace> AccountingNoteList = AccountingNoteService.getAccountingNoteInterfaceListByUserID(User.getId());
		//小計
		Integer AmountSum = (AccountingNoteService.getAccountingNoteAmountSumOfMonth(User.getId(), 1,false) - AccountingNoteService.getAccountingNoteAmountSumOfMonth(User.getId(), 0,false));
		//當月小計
		Integer AmountSumOfMonth = (AccountingNoteService.getAccountingNoteAmountSumOfMonth(User.getId(), 1,true) - AccountingNoteService.getAccountingNoteAmountSumOfMonth(User.getId(), 0, true));
		model.addAttribute("AccountingNoteList", AccountingNoteList);
		model.addAttribute("subtotal", AmountSum);
		model.addAttribute("subtotalofmonth", AmountSumOfMonth);
		

		return UrlPath.URL_ACCOUNTINGLIST;
	}
	
	@PostMapping("/SystemAdmin/AccountingList")
	public String AccountListDel(@RequestParam(value = "ckbDelete", required = false) Integer[] AccountNoteDel, 
			RedirectAttributes redirAttrs,Model model) {
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck)//驗證登入
		{
			LoginService.LoginSessionRemove(session);
			return "redirect:" + UrlPath.URL_DEFAULT;
		}
		
		if (AccountNoteDel != null ) {//假如checkbox有被勾選
			for (int AccountNoteid : AccountNoteDel) {
				AccountingNoteService.deleteAccountingNoteByAccountingNoteID(AccountNoteid);//刪除流水帳
				}
			redirAttrs.addFlashAttribute("message", "刪除成功");
		}
		else
			redirAttrs.addFlashAttribute("message", "並未勾選");
		
		return "redirect:" + UrlPath.URL_ACCOUNTINGLIST;
	}

}



