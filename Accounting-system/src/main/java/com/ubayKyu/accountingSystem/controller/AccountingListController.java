package com.ubayKyu.accountingSystem.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ubayKyu.accountingSystem.service.AccountingNoteService;
import com.ubayKyu.accountingSystem.service.CategoryService;
import com.ubayKyu.accountingSystem.service.LoginService;
import com.ubayKyu.accountingSystem.dto.AccountingNoteInterFace;
import com.ubayKyu.accountingSystem.dto.User;
import com.ubayKyu.accountingSystem.entity.UserInfo2;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AccountingListController {
	@Autowired
	HttpSession session;
	@Autowired
	private AccountingNoteService AccountingNoteService;
	
	@GetMapping("/SystemAdmin/AccountingList")
	public String AccountList(Model model) {
		//, @ModelAttribute("message") String message
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck)
		{
            String url = "/default";
            LoginService.LoginSessionRemove(session);
            return "redirect:" + url;
		}
		UserInfo2 User= (UserInfo2) session.getAttribute("LoginState");
        List<AccountingNoteInterFace> AccountingNoteList = AccountingNoteService.getAccountingNoteInterfaceListByUserID(User.getId());
        Integer AmountSum = (AccountingNoteService.getAccountingNoteAmountSumOfMonth(User.getId(), 1,false) - AccountingNoteService.getAccountingNoteAmountSumOfMonth(User.getId(), 0,false));
        Integer AmountSumOfMonth = (AccountingNoteService.getAccountingNoteAmountSumOfMonth(User.getId(), 1,true) - AccountingNoteService.getAccountingNoteAmountSumOfMonth(User.getId(), 0, true));
        model.addAttribute("AccountingNoteList", AccountingNoteList);
		model.addAttribute("subtotal", AmountSum);
		model.addAttribute("subtotalofmonth", AmountSumOfMonth);
		

		return "SystemAdmin/AccountingList.html";
	}
	
	@PostMapping("/SystemAdmin/AccountingList")
	public String AccountListDel(@RequestParam(value = "ckbDelete", required = false) Integer[] AccountNoteDel, 
			RedirectAttributes redirAttrs,Model model) {
		//, @ModelAttribute("message") String message
		boolean loginCheck = LoginService.LoginSessionCheck(session);
		if(!loginCheck)
		{
            String url = "/default";
            LoginService.LoginSessionRemove(session);
            return "redirect:" + url;
		}
		
		if (AccountNoteDel != null ) {//假如checkbox有被勾選
			for (int AccountNoteid : AccountNoteDel) {
				AccountingNoteService.deleteAccountingNoteByAccountingNoteID(AccountNoteid);
				}
			redirAttrs.addFlashAttribute("message", "刪除成功");
		}
		else
			redirAttrs.addFlashAttribute("message", "並未勾選");
		
		return "redirect:AccountingList";
	}

}



