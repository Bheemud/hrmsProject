package com.hrms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.hrms.beans.Titles;

public interface TitleDao extends JpaRepository<Titles, String>{
	

		public List<Titles> findByTitle(String title);

	    List<Titles> findByEmpNoAndTitle(String empNo, String title);
	    List<Titles> findByEmpNo(String empNo);
	    
	    void deleteByEmpNo(String empNo);
	    List<Titles> deleteByTitle(String title);
	    
	    List<Titles> findByFromDate(Date fromDate);
	 
	    
	    @Query("SELECT t FROM Titles t WHERE t.empNo = ?1 AND t.fromDate = ?2 AND t.title = ?3")
	    List<Titles> findByEmpNoFromDateAndTitle(String empNo, Date fromDate, String title);
	    
	    
	    @Modifying
	    @Query("DELETE FROM Titles t WHERE t.empNo = ?1 AND t.fromDate = ?2 AND t.title = ?3")
	    void deleteByEmpNoFromDateAndTitle(String empNo, Date formattedFromDate, String title);

}
