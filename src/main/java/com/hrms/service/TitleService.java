package com.hrms.service;

import java.util.Date;
import java.util.List;

import com.hrms.beans.Titles;

public interface TitleService {
	
	public List<Titles> getAllTitles();
	public List<Titles> updateTitleByFromDate(String date,String title);
	public List<Titles> updateTitleByTitle(String title,String upTitle);
	public String addNewTitle(Titles title);
    public List<Titles> getTitlesByTitle(String title);
    public List<Titles> getTitlesByEmpNoAndTitle(String empNo, String title);
    public List<Titles> deleteTitlesByEmpNo(String empNo);
    public List<Titles> deleteTitlesByTitle(String title);
	String deleteTitleByEmpNoFromDateAndTitle(String empNo, Date fromDate, String title);
	List<Titles> getTitlesByEmpNoFromDateAndTitle(String empNo, Date fromDate, String title);
	List<Titles> getTitlesByFromDate(String fromDate);
	String updateTitlesForEmployee(String empNo, List<Titles> titles);
	public String updateTitleByEmpNoFromDateAndTitle(String empNo, String fromDate, String title);
	
}
