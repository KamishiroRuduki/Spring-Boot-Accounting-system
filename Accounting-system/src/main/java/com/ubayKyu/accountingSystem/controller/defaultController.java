package com.ubayKyu.accountingSystem.controller;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ubayKyu.accountingSystem.service.FormatService;
import com.ubayKyu.accountingSystem.entity.UserInfo2;
import com.ubayKyu.accountingSystem.repository.UserInfoRepository2;
import com.ubayKyu.accountingSystem.entity.AccountingNote;
import com.ubayKyu.accountingSystem.repository.AccountingNoteRepository;
import com.ubayKyu.accountingSystem.service.UserInfoService;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class defaultController {
	
    @Autowired
    private UserInfoRepository2 UserInfoRepository2;
    
    @Autowired
    private AccountingNoteRepository AccountingNoteRepository;
    
	@GetMapping("/default")
	public String hello(Model model) {
        String oldestAccDate = "無紀錄";
        String latestAccDate = "無紀錄";
        // 驗證時間是否為空
        if (AccountingNoteRepository.GetFirstDate() != null) {
            oldestAccDate = FormatService.FormatDateTime(AccountingNoteRepository.GetFirstDate()); // string=FormatService.時間格式化(Get時間.())
        }
        if (AccountingNoteRepository.GetLastDate() != null) {
            latestAccDate = FormatService.FormatDateTime(AccountingNoteRepository.GetLastDate()); // string=FormatService.時間格式化(Get時間.())
        }
		
		LocalDateTime FirstDate = AccountingNoteRepository.GetFirstDate();
		LocalDateTime LastDate = AccountingNoteRepository.GetLastDate();
		Integer AccountCount = AccountingNoteRepository.GetAccountCount();
		List<UserInfo2> user = UserInfoRepository2.findAll();
	//	LocalDateTime firsttime = user.get(0).getCreateDate();
	//	LocalDateTime lasttime = user.get(user.size()-1).getCreateDate();
		model.addAttribute("firstAccRecordTime", oldestAccDate);
        model.addAttribute("lastAccRecordTime", latestAccDate);
        model.addAttribute("recordNumber", AccountCount);
        model.addAttribute("menberNumber", user.size());
		return "default.html";
	}

}
