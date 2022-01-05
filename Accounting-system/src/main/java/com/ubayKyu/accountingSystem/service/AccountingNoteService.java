package com.ubayKyu.accountingSystem.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.ubayKyu.accountingSystem.entity.AccountingNote;
import com.ubayKyu.accountingSystem.entity.Category;
import com.ubayKyu.accountingSystem.repository.AccountingNoteRepository;
import com.ubayKyu.accountingSystem.dto.AccountingNoteInterFace;

@Service
public class AccountingNoteService {
	@Autowired
	private AccountingNoteRepository AccountingNoteRepository;
	
	public List<AccountingNote> getAccountingNoteAll(){
		return AccountingNoteRepository.findAll();
	}
	
	public Optional<AccountingNote> getAccountingNoteByID(Integer accID){
		return AccountingNoteRepository.findById(accID);
	}
	
	public List<AccountingNoteInterFace> getAccountingNoteInterfaceListByUserID(String userid){
		return AccountingNoteRepository.FindAccountingNoteModelListByUserID(userid);
	}
	
	public Integer saveAccountingNote(AccountingNote accountingNote) {
		AccountingNoteRepository.save(accountingNote);
		if(accountingNote.getAccID() == null) {
			//找到剛剛新增的那筆並回傳該筆的accid
			List<AccountingNote> accountingNotes = AccountingNoteRepository.FindAccountingNoteByUserID(accountingNote.getUserID()); 
			return accountingNotes.get(accountingNotes.size()).getAccID();
		}
		return accountingNote.getAccID();	
	}
	
	//取得小計
	public Integer getAccountingNoteAmountSumOfMonth(String userid, int actType, boolean thismonth){
		Integer answer = null;
		if(!thismonth) {
			LocalDateTime minDateTime = LocalDateTime.of(1900, 1, 1, 0, 0);
			//LocalDateTime maxDateTime = LocalDateTime.now();
			LocalDateTime maxDateTime = LocalDateTime.of(2500, 12, 31, 23, 59,59,999999);
	  	    answer = AccountingNoteRepository.FindAccountingNoteAmount(userid, actType, minDateTime, maxDateTime);
		}
		else {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);//設定為當月第一天
		Date first = c.getTime();////獲取當前月第一天	
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));//設定為當月最後一天
		Date last = ca.getTime();//獲取當前月最後一天
		LocalDate firstdate = LocalDate.ofInstant(first.toInstant(), ZoneId.systemDefault());//轉換成LocalDate
		LocalDate lastdate = LocalDate.ofInstant(last.toInstant(), ZoneId.systemDefault());//轉換成LocalDate
		LocalDateTime firstdatetime = firstdate.atTime(LocalTime.MIN);//賦予時分秒(00:00:00)
		LocalDateTime lastdatetime = lastdate.atTime(LocalTime.MAX);//賦予時分秒(23:59:59)
         answer = AccountingNoteRepository.FindAccountingNoteAmount(userid, actType, firstdatetime, lastdatetime);
		}
		
        if(answer == null) {
            answer=0;
        }
        
        return answer; 
	}
	
	public void deleteAccountingNoteByAccountingNoteID(int accountingNoteid){
		AccountingNoteRepository.deleteById(accountingNoteid);
	}

}



