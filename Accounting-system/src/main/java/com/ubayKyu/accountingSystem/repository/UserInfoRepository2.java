package com.ubayKyu.accountingSystem.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ubayKyu.accountingSystem.dto.UserInfoInterface;
import com.ubayKyu.accountingSystem.entity.UserInfo2;

@Repository
public interface UserInfoRepository2 extends JpaRepository<UserInfo2,String>{

    @Query(value = "SELECT [id]"
            + "      ,[account]"
            + "      ,[create_date]"
            + "      ,[email]"
            + "      ,[name]"
            + "      ,[pwd]"
            + "      ,[user_level]"
            + "      ,[edit_date]"
            + "  FROM [user_info]"
            +"  WHERE [account] =:account AND [pwd]=:pwd"
            , nativeQuery = true)
    UserInfo2 GetUserLogin(@Param("account") String account , @Param("pwd") String pwd);
    
    //取得會員資訊(Interface)
    @Query(value = "SELECT [id]"
                + "     ,[account]"
                 + "        ,FORMAT([create_date], 'yyyy/MM/dd hh:mm') AS [create_date]"
                 + "        ,[email]"
                 + "        ,[name]"
                 + "        ,[user_level]"
                 + "        ,FORMAT([edit_date], '	') AS [edit_date]"
                 + "    FROM [user_info]"
                 + "    ORDER BY [create_date] DESC"
                 , nativeQuery = true)
     List<UserInfoInterface> GetUserInfoInterface();
    
    @Query(value = "SELECT [id]"
            + "     ,[account]"
             + "        ,FORMAT([create_date], 'yyyy/MM/dd hh:mm:ss') AS [create_date]"
             + "        ,[email]"
             + "        ,[name]"
             + "        ,[user_level]"
             + "        ,FORMAT([edit_date], 'yyyy/MM/dd hh:mm:ss') AS [edit_date]"
             + "    FROM [user_info]"
             + "    WHERE [user_info].[id] =:userid"
             , nativeQuery = true)
    Optional<UserInfoInterface> FindUserInfoInterfaceByID(@Param("userid") String userid);
    
    //檢查帳號重複
	@Query(value = "  SELECT COUNT(*)"
			+ "  FROM  user_info"
			+ "  WHERE account =:account", nativeQuery = true)
	int FindUserAccountByAccount(@Param("account") String account);
    
	//刪除、修改沒有回傳值得場合要加@Modifying
	//刪除使用者，同時刪除該使用者的流水帳跟分類
    @Modifying
    @Query(value = "DELETE FROM [user_info]"
            + "    WHERE [user_info].[id] =:userid"
            + " DELETE FROM [accounting_note]"
            + "    WHERE [accounting_note].[userid] =:userid"
            + "    DELETE FROM [category]"
            + "    WHERE [category].[userid] =:userid"
            , nativeQuery = true)
     void DeleteUserInfoAccountingNoteAndCategoryByUserID(@Param("userid") String userid);
    
    //找出現在的管理員數
	@Query(value = "  SELECT COUNT(*)"
			+ "  FROM  user_info"
			+ "  WHERE user_level > '0'", nativeQuery = true)
	int FindAdminUserCount();
    
	
    
}


