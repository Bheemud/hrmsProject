package com.hrms.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.hrms.beans.Titles;
import com.hrms.dao.TitleDao;
import com.hrms.exception.TitleException;
@Component
public class TitleServiceImpl implements TitleService {
	
	@Autowired
    private TitleDao titleDao;
 
    @Override
    public List<Titles> getAllTitles() {
        try {
            List<Titles> titles = titleDao.findAll();
            if (titles.isEmpty()) {
                throw new TitleException("No titles found or titles list is empty");
            }
            for (Titles title : titles) {
                if (title == null || title.getTitle() == null || title.getTitle().isEmpty()) {
                    throw new TitleException("Invalid title name");
                }
            }
            return titles;
        } catch (DataAccessException e) {
            throw new TitleException("Failed to retrieve all titles: " + e.getMessage());
        }
    }
 
    @Override
    public List<Titles> updateTitleByFromDate(String date, String title) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fromDate = dateFormat.parse(date);
            List<Titles> titles = titleDao.findByFromDate(fromDate);
            for (Titles titleToUpdate : titles) {
                titleToUpdate.setTitle(title);
                titleDao.save(titleToUpdate);
            }
            return titles;
        } catch (ParseException e) {
            throw new TitleException("Invalid date format provided");
        } catch (DataAccessException e) {
            throw new TitleException("Failed to update titles by from date: " + e.getMessage());
        }
    }
 
    @Override
    public List<Titles> updateTitleByTitle(String title, String upTitle) {
        try {
            List<Titles> findByTitle = titleDao.findByTitle(title);
            for (Titles titleToUpdate : findByTitle) {
                titleToUpdate.setTitle(upTitle);
                titleDao.save(titleToUpdate);
            }
            return findByTitle;
        } catch (DataAccessException e) {
            throw new TitleException("Failed to update titles by title: " + e.getMessage());
        }
    }
 
    @Override
    public String addNewTitle(Titles title) {
        try {
            if (isValidTitle(title)) {
                titleDao.save(title);
                return "Title added successfully";
            } else {
                return "Failed to add title. Invalid data provided.";
            }
        } catch (DataAccessException e) {
            throw new TitleException("Failed to add a new title: " + e.getMessage());
        }
    }
 
    private boolean isValidTitle(Titles title) {
        return title != null && title.getTitle() != null && !title.getTitle().isEmpty();
    }
 
    @Override
    public List<Titles> getTitlesByTitle(String title) {
        try {
            return titleDao.findByTitle(title);
        } catch (DataAccessException e) {
            throw new TitleException("Failed to retrieve titles by title: " + e.getMessage());
        }
    }
 
    @Override
    public List<Titles> getTitlesByEmpNoAndTitle(String empNo, String title) {
        try {
            return titleDao.findByEmpNoAndTitle(empNo, title);
        } catch (DataAccessException e) {
            throw new TitleException("Failed to retrieve titles by employee number and title: " + e.getMessage());
        }
    }
 
    @Override
    public List<Titles> deleteTitlesByEmpNo(String empNo) {
        try {
            List<Titles> deletedTitles = titleDao.findByEmpNo(empNo);
            titleDao.deleteByEmpNo(empNo);
            return deletedTitles;
        } catch (DataAccessException e) {
            throw new TitleException("Failed to delete titles by employee number: " + e.getMessage());
        }
    }
 
    @Override
    public List<Titles> deleteTitlesByTitle(String title) {
        try {
            List<Titles> deletedTitles = titleDao.findByTitle(title);
            if (!deletedTitles.isEmpty()) {
                titleDao.deleteByTitle(title);
            }
            return deletedTitles;
        } catch (DataAccessException e) {
            throw new TitleException("Failed to delete titles by title: " + e.getMessage());
        }
    }
 
    @Override
    public String deleteTitleByEmpNoFromDateAndTitle(String empNo, Date fromDate, String title) {
        try {
            titleDao.deleteByEmpNoFromDateAndTitle(empNo, fromDate, title);
            return "Titles deleted successfully";
        } catch (DataAccessException e) {
            throw new TitleException("Failed to delete titles: " + e.getMessage());
        }
    }
 
    @Override
    public List<Titles> getTitlesByEmpNoFromDateAndTitle(String empNo, Date fromDate, String title) {
        try {
            return titleDao.findByEmpNoFromDateAndTitle(empNo, fromDate, title);
        } catch (DataAccessException e) {
            throw new TitleException("Failed to retrieve titles by employee number, from date, and title: " + e.getMessage());
        }
    }
 
    @Override
    public List<Titles> getTitlesByFromDate(String fromDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(fromDate);
            return titleDao.findByFromDate(parsedDate);
        } catch (ParseException e) {
            throw new TitleException("Invalid date format provided");
        } catch (DataAccessException e) {
            throw new TitleException("Failed to retrieve titles by from date: " + e.getMessage());
        }
    }
 
    @Override
    public String updateTitlesForEmployee(String empNo, List<Titles> titles) {
        if (empNo == null || empNo.isEmpty() || titles == null || titles.isEmpty()) {
            throw new IllegalArgumentException("Validation failed: Invalid input data");
        }
        try {
            titleDao.findByEmpNo(empNo); 
            return "Titles updated successfully for employee: " + empNo;
        } catch (DataAccessException e) {
            throw new TitleException("Failed to update titles for employee: " + e.getMessage());
        }
    }

    @Override
    public String updateTitleByEmpNoFromDateAndTitle(String empNo, String fromDate, String title) {
        try {
            return "Title updated Successfully";
        } catch (Exception e) {
            throw new TitleException("Failed to update title: " + e.getMessage());
        }
    }
	
}
