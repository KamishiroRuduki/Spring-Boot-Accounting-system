package com.ubayKyu.accountingSystem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
	
	public List<Category> getCategoryByUserID(String userid){
		return CategoryRepository.FindCategoryByUserID(userid);
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
	

	
	//檢查標題重複
	public boolean IsNotCategoryCaptionCreated(String userid, String caption, String categoryid) {
		Optional<Category> category = null;
		if (categoryid != null)// 編輯模式
			category = CategoryRepository.findById(categoryid);
		
		if ( category != null && category.get().getCaption().equals(caption) )//標題可以是原本的標題
			return true;
		else 
			return (CategoryRepository.FindCategoryCaptionByCaptionAndUserID(userid, caption) == 0); 
//		else if (CategoryRepository.FindCategoryCaptionByCaptionAndUserID(userid, caption) == 0) 
//			return true;
//		else
//			return false;
		 

	}
	
    
    public void CategorySave(String userID,String txtCaption, String txtBody, String categoryID, LocalDateTime dateTime ) {//新增/修改分類
		Category category = new Category();
		category.setCategoryID(categoryID);
		category.setBody(txtBody);
		category.setCaption(txtCaption);
		category.setUserID(userID);
		category.setCreateDate(dateTime);
		CategoryRepository.save(category);
    }

}

