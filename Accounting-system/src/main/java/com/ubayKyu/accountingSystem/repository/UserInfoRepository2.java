package com.ubayKyu.accountingSystem.repository;


import java.util.List;

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
                 + "    FROM [user_info]"
                 + "    ORDER BY [create_date] DESC"
                 , nativeQuery = true)
     List<UserInfoInterface> GetUserInfoInterface();
    
    @Modifying
    @Query(value = "DELETE FROM [user_info]"
            + "    WHERE [user_info].[id] =:userid"
            + " DELETE FROM [accounting_note]"
            + "    WHERE [accounting_note].[userid] =:userid"
            + "    DELETE FROM [category]"
            + "    WHERE [category].[userid] =:userid"
            , nativeQuery = true)
     void DeleteUserInfoAccountingNoteAndCategoryByUserID(@Param("userid") String userid);
}


