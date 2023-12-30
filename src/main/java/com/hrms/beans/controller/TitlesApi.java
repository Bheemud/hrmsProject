package com.hrms.beans.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.Titles;
import com.hrms.service.TitleService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/titles")
@SecurityRequirement(name = "Bearer Authentication")
public class TitlesApi {
	
	@Autowired
	private TitleService titleService;
	@GetMapping
	public List<Titles> getAllTitles() {
		return titleService.getAllTitles();
	}
	@PutMapping("/update/{date}")
	public List<Titles> updateTitleByDate(@PathVariable("date") String date,String title){
		return titleService.updateTitleByFromDate(date,title);
	}
	@PutMapping("/update/title/{title}")
	public List<Titles> updateTitle(@PathVariable("title") String title,String upTitle){
		return titleService.updateTitleByTitle(title,upTitle);
	}
    @PostMapping("/add")
    public String addNewTitle(@RequestBody Titles title) {
        return titleService.addNewTitle(title);
    }

    @GetMapping("/empno/{empNo}/title/{title}")
    public List<Titles> getTitlesByEmpNoAndTitle(@PathVariable String empNo, @PathVariable String title) {
        return titleService.getTitlesByEmpNoAndTitle(empNo, title);
    }
    @Transactional
    @DeleteMapping("/empno/{empNo}")
    public List<Titles> deleteTitlesByEmpNo(@PathVariable("empNo") String empNo) {
        return titleService.deleteTitlesByEmpNo(empNo);
    }
    @Transactional
    @DeleteMapping("/title/{title}")
    public List<Titles> deleteTitlesByTitle(@PathVariable("title") String title) {
        return titleService.deleteTitlesByTitle(title);
    }
    @GetMapping("/title/{title}")
    public List<Titles> getTitlesByTitle(@PathVariable String title) {
        return titleService.getTitlesByTitle(title);
    }
    @Transactional
    @DeleteMapping("/empno/{empNo}/fromdate/{fromDate}/title/{title}")
    public String deleteTitleByEmpNoFromDateAndTitle(
            @PathVariable("empNo") String empNo,
            @PathVariable("fromDate") String fromDateStr,
            @PathVariable("title") String title) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fromDate = dateFormat.parse(fromDateStr);
            return titleService.deleteTitleByEmpNoFromDateAndTitle(empNo, fromDate, title);
        } catch (ParseException e) {
            return "Invalid date format or date: " + e.getMessage();
        }
    }
    @GetMapping("/empno/{empNo}/fromdate/{fromDate}/title/{title}")
    public List<Titles> getTitlesByEmpNoFromDateAndTitle(
            @PathVariable String empNo,
            @PathVariable String fromDate,
            @PathVariable String title) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedFromDate = dateFormat.parse(fromDate);
            return titleService.getTitlesByEmpNoFromDateAndTitle(empNo, parsedFromDate, title);
        } catch (ParseException e) {
            return Collections.emptyList();
        }
    }

    @GetMapping("/fromdate/{fromdate}")
    public List<Titles> getTitlesByFromDate(@PathVariable("fromdate") String fromDate) {
        return titleService.getTitlesByFromDate(fromDate);
    }
    @PutMapping("/{empNo}")
    public String updateTitlesForEmployee(
            @RequestParam("empNo") String empNo,
            @RequestBody List<Titles> titles) {
        return titleService.updateTitlesForEmployee(empNo, titles);
    }
    @PutMapping("/empno/{empNo}/fromdate/{fromDate}/title/{title}")
    public String updateTitleByEmpNoFromDateAndTitle(
            @PathVariable String empNo,
            @PathVariable String fromDate,
            @PathVariable String title) {
        return titleService.updateTitleByEmpNoFromDateAndTitle(empNo, fromDate, title);
    }

}
