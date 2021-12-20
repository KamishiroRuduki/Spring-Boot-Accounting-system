package com.ubayKyu.accountingSystem.repository;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ubayKyu.accountingSystem.dto.AccountingNoteInterFace;
import com.ubayKyu.accountingSystem.dto.CategoryInterFace;
import com.ubayKyu.accountingSystem.entity.AccountingNote;



@Repository
public interface AccountingNoteRepository extends JpaRepository<AccountingNote,Integer>{
	//List<AccountingNote> findAll();
	
    @Query(value = "SELECT Min(accounting_note.create_date)"
    		+ "FROM accounting_note"
            , nativeQuery = true)
      LocalDateTime GetFirstDate();
    
    @Query(value = "SELECT Max(accounting_note.create_date)"
    		+ "FROM accounting_note"
            , nativeQuery = true)
     LocalDateTime GetLastDate();
    
    @Query(value = "SELECT Count(*)"
    		+ "FROM accounting_note"
            , nativeQuery = true)
     Integer GetAccountCount();
    
	@Query(value = "  SELECT  accounting_note.[accid]"
    		+ "         ,accounting_note.[act_type]"
    		+ "         ,accounting_note.[amount]"
    		+ "         ,accounting_note.[body]"
    		+ "         ,accounting_note.[caption]"
    		+ "         ,accounting_note.[categoryid]"
    		+ "         ,FORMAT(accounting_note.[create_date], 'yyyy/MM/dd') AS [create_date]"
    		+ "         ,accounting_note.[userid]"
    		+ "         ,category.[caption] AS [category_caption]"
    		+ "  FROM [accounting_note] AS accounting_note"
    		+ "  LEFT JOIN [category] category ON category.[categoryid] = accounting_note.[categoryid]"
    		+ "  WHERE accounting_note.[userid] = :userid"
    		+ "  GROUP BY accounting_note.[accid], accounting_note.[act_type], accounting_note.[amount], accounting_note.[body], accounting_note.[caption], accounting_note.[categoryid], accounting_note.[create_date], accounting_note.[userid], category.[caption]"
    		+ "  ORDER BY [create_date] DESC, [amount] DESC", nativeQuery = true)
	List<AccountingNoteInterFace> FindAccountingNoteModelListByUserID(@Param("userid") String userid);
	
    
    @Query(value = "	SELECT SUM(amount) AS Amount"
    		+ "    FROM accounting_note"
    		+ "    WHERE userid=:userid AND act_type=:actType AND create_date BETWEEN  :FirstDate AND :LastDate"
            , nativeQuery = true)
    Integer FindAccountingNoteAmount(@Param("userid") String userid, @Param("actType") int actType, @Param("FirstDate") LocalDateTime FirstDate,@Param("LastDate") LocalDateTime LastDate); 
    
    @Query(value = "	SELECT *"
    		+ "    FROM accounting_note"
    		+ "    WHERE userid=:userid "
            , nativeQuery = true)
    List<AccountingNote> FindAccountingNoteByUserID(@Param("userid") String userid);
    

}




