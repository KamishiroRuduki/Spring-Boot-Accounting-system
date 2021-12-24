package com.ubayKyu.accountingSystem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubayKyu.accountingSystem.dto.UserInfoInterface;
import com.ubayKyu.accountingSystem.entity.Category;
import com.ubayKyu.accountingSystem.entity.UserInfo2;
import com.ubayKyu.accountingSystem.repository.UserInfoRepository2;

@Service
public class UserInfoService {
	@Autowired
	private UserInfoRepository2 repository;
	
	public List<UserInfo2> getUserInfos(){
		return repository.findAll();
	}
	
    public Optional<UserInfo2> findByUserID(String userID) {
        return repository.findById(userID);
    }

    public Optional<UserInfoInterface> GetUserInfoInterfaceByUserID(String userID) {
        return repository.FindUserInfoInterfaceByID(userID);
    }
    
    public void SaveUserInfo(UserInfo2 userInfo,String userID, String txtAccount, String txtName, String txtEmail,Integer ddlUserLevel ) {//新增、編輯使用者
    	userInfo.setId(userID);
    	userInfo.setPWD("12345678");
		userInfo.setAccount(txtAccount);
		userInfo.setName(txtName);
		userInfo.setEmail(txtEmail);
		userInfo.setUserLevel(ddlUserLevel);
		repository.save(userInfo);
    }
	
	public boolean IsNotAccountCreated(String account) {
	    if (repository.FindUserAccountByAccount(account) == 0) 
			return true;
		else
			return false;

	}
	public List<UserInfo2> getUserInfoById(List<String> ids){
		return repository.findAllById(ids);
	}
	
	public String deleteUserInfo(String id) {
		repository.deleteById(id);
		return "Deleted!";
	}
	
	@Transactional
    public void deleteUserInfoAccountingNoteAndCategoryByUserID(String userID) {
        repository.DeleteUserInfoAccountingNoteAndCategoryByUserID(userID);
    }
    
    public List<UserInfoInterface> getUserInfoInterface() {
        return repository.GetUserInfoInterface();
    }
    
    public void UpdateUserProfile(String userID, String txtName, String txtEmail) {
        Optional<UserInfo2> userInfoForEdit = repository.findById(userID);
        userInfoForEdit.get().setName(txtName); // 更新Name
        userInfoForEdit.get().setEmail(txtEmail); // 更新Email
        repository.save(userInfoForEdit.get()); // 內建儲存語法
    }
}
