package com.ubayKyu.accountingSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.ubayKyu.accountingSystem.entity.Category;
import com.ubayKyu.accountingSystem.entity.UserInfo2;
import com.ubayKyu.accountingSystem.repository.CategoryRepository;
import com.ubayKyu.accountingSystem.dto.CategoryInterFace;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository CategoryRepository;
	
	public List<Category> getCategoryAll(){
		return CategoryRepository.findAll();
	}
	
	public List<CategoryInterFace> getCategoryModelByUserid(String userid){
		return CategoryRepository.FindCategoryModelListByUserid(userid);
	}
	
	public int getCategoryCountByCategoryid(String categoryid){
		return CategoryRepository.FindCountByCategoryid(categoryid);
	}
	
	public void deleteCategoryByCategoryid(String categoryid){
		CategoryRepository.deleteById(categoryid);
	}
	
	public Optional<Category> getCategoryByCategoryid(String categoryid){
		return CategoryRepository.findById(categoryid);
		
		
	}
	
	public void saveCategory(Category category) {
		 CategoryRepository.save(category);
	}
	//test
//	public UserInfo2 saveUserInfo(UserInfo2 UserInfo) {
//		return repository.save(UserInfo);
//	}
//	
//	public List<UserInfo2> getUserInfoById(List<String> ids){
//		return repository.findAllById(ids);
//	}
//	
//	public String deleteUserInfo(String id) {
//		repository.deleteById(id);
//		return "Deleted!";
//	}
}

