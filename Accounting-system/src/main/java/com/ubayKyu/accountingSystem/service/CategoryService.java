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
	
	public boolean IsCategoryCaptionCreated(String userid, String caption, String categoryid)
	{		
		int captionDB =  CategoryRepository.FindCategoryCaptionByCaptionAndUserID(userid, caption);	
		if(categoryid != null)		//編輯模式	
		{	
			Optional<Category> category = CategoryRepository.findById(categoryid);
			if( captionDB == 0 )
				return true;
			else if ( category != null && category.get().getCaption().equals(caption) ) //標題可以是原本的標題
				return true;
			else
			    return false;
		}
		else //新增模式
		{			
			if(captionDB == 0 )//判斷是否有重複的標題
				return true;
			else
			    return false;
		}

	}
	
	public boolean IsNotCategoryCaptionCreated(String userid, String caption, String categoryid) {
		//int captionDB = CategoryRepository.FindCategoryCaptionByCaptionAndUserID(userid, caption);
		if (categoryid != null) // 編輯模式
		{
			Optional<Category> category = CategoryRepository.findById(categoryid);
			if ( category != null && category.get().getCaption().equals(caption) )//標題可以是原本的標題
				return true;
			else if (CategoryRepository.FindCategoryCaptionByCaptionAndUserID(userid, caption) == 0) 
				return true;
			else
				return false;
		} 
		else if (CategoryRepository.FindCategoryCaptionByCaptionAndUserID(userid, caption) == 0) 
			return true;
		else
			return false;

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

