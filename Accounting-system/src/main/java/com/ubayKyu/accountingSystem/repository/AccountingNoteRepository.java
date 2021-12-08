package com.ubayKyu.accountingSystem.repository;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ubayKyu.accountingSystem.entity.AccountingNote;



@Repository
public interface AccountingNoteRepository extends JpaRepository<AccountingNote,Integer>{
	List<AccountingNote> findAll();
	
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

}




