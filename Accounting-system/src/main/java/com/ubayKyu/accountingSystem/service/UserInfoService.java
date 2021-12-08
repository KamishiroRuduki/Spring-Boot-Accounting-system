package com.ubayKyu.accountingSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubayKyu.accountingSystem.entity.UserInfo2;
import com.ubayKyu.accountingSystem.repository.UserInfoRepository2;

@Service
public class UserInfoService {
	@Autowired
	private UserInfoRepository2 repository;
	
	public List<UserInfo2> getUserInfos(){
		return repository.findAll();
	}
	
	//test
	public UserInfo2 saveUserInfo(UserInfo2 UserInfo) {
		return repository.save(UserInfo);
	}
	
	public List<UserInfo2> getUserInfoById(List<String> ids){
		return repository.findAllById(ids);
	}
	
	public String deleteUserInfo(String id) {
		repository.deleteById(id);
		return "Deleted!";
	}
}
