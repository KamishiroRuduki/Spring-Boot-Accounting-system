package com.ubayKyu.accountingSystem.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ubayKyu.accountingSystem.entity.UserInfo2;

@Repository
public interface UserInfoRepository2 extends JpaRepository<UserInfo2,String>{
	List<UserInfo2> findAll();

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
}


