package com.ubayKyu.accountingSystem.repository;



import org.apache.ibatis.annotations.*; 
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ubayKyu.accountingSystem.entity.AccountingNote;
import com.ubayKyu.accountingSystem.entity.Category;
import com.ubayKyu.accountingSystem.dto.CategoryModel;
import com.ubayKyu.accountingSystem.dto.CategoryInterFace;


@Repository
public interface CategoryRepository extends JpaRepository<Category,String>{

	
	@Query(value = "  SELECT category.categoryid"
			+ "      ,category.body"
			+ "      ,category.caption"
			+ "      ,FORMAT(category.create_date, 'yyyy-MM-dd') create_date"
			+ "      ,category.userid"
			+ "	     ,COUNT(accounting_note.categoryid) count"
			+ "  FROM category category"
			+ "  LEFT JOIN accounting_note accounting_note   ON accounting_note.categoryid = category.categoryid"
			+ "  WHERE category.userid =:userid"
			+ "  GROUP   BY category.categoryid ,category.body ,category.caption ,category.create_date,category.userid", nativeQuery = true)
	List<CategoryInterFace> FindCategoryModelListByUserid(@Param("userid") String userid);
	
	@Query(value = "  SELECT COUNT(accounting_note.categoryid) count"
			+ "  FROM category category"
			+ "  LEFT JOIN accounting_note accounting_note   ON accounting_note.categoryid = category.categoryid"
			+ "  WHERE category.categoryid =:categoryid"
			+ "  GROUP   BY category.categoryid", nativeQuery = true)
	int FindCountByCategoryid(@Param("categoryid") String categoryid);

	
	

}






