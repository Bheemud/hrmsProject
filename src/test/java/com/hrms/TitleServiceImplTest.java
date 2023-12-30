package com.hrms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
 
import com.hrms.beans.Titles;
import com.hrms.dao.TitleDao;
import com.hrms.service.TitleServiceImpl;
 
 
@SpringBootTest
 class TitleServiceImplTest {
 
    @Mock
    private TitleDao titleDaoMock;
 
    @InjectMocks
    private TitleServiceImpl titleService;
 
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
 
    
 
    @Test
     void testUpdateTitleByFromDate() throws ParseException {
        String date = "2023-01-01";
        String oldTitleName = "Old Title";
        String newTitleName = "New Title";

        Titles titleObject = new Titles(/* construct Titles object with necessary parameters */);
        titleObject.setTitle(oldTitleName);

        List<Titles> titlesList = new ArrayList<>();
        titlesList.add(titleObject);

        when(titleDaoMock.findByFromDate(any(Date.class))).thenReturn(titlesList);

        List<Titles> result = titleService.updateTitleByFromDate(date, newTitleName);

        // Assertions to verify that the titles were updated
        assertNotNull(result);
        assertFalse(result.isEmpty());

        Titles updatedTitle = result.get(0);
        assertEquals(newTitleName, updatedTitle.getTitle());
        // Add more assertions as needed
    }
    @Test
     void testUpdateTitleByTitle() {
        // Mock data
        String titleToFind = "Old Title";
        String updatedTitle = "New Title";
 
        // Creating mock Titles
        Titles title1 = new Titles(/* constructor parameters */);
        title1.setTitle(titleToFind);
 
        Titles title2 = new Titles(/* constructor parameters */);
        title2.setTitle(titleToFind);
 
        List<Titles> titlesList = new ArrayList<>();
        titlesList.add(title1);
        titlesList.add(title2);
 
        // Mocking behavior of TitleDao findByTitle method
        when(titleDaoMock.findByTitle(titleToFind)).thenReturn(titlesList);
 
        // Perform the update
        List<Titles> result = titleService.updateTitleByTitle(titleToFind, updatedTitle);
 
        // Verify that the update was performed
        for (Titles title : titlesList) {
            verify(titleDaoMock).save(title);
            assertEquals(updatedTitle, title.getTitle(), "Title should be updated");
        }
 
        assertEquals(titlesList, result, "Returned list should match the list from findByTitle");
    }
    

    
    @Test
     void testAddNewTitle_InvalidData() {
        Titles invalidTitle = new Titles(); // An invalid Titles object with empty title
 
        String result = titleService.addNewTitle(invalidTitle);
 
        assertEquals("Failed to add title. Invalid data provided.", result);
        verify(titleDaoMock, never()).save(invalidTitle); // Verify that save was never called with invalidTitle
    }
 
    @Test
     void testGetTitlesByTitle() {
        String titleToSearch = "Some Title";
 
        List<Titles> titlesList = new ArrayList<>();
        titlesList.add(new Titles(/* Construct Titles objects with titleToSearch */));
 
        when(titleDaoMock.findByTitle(titleToSearch)).thenReturn(titlesList);
 
        List<Titles> result = titleService.getTitlesByTitle(titleToSearch);
 
        assertEquals(titlesList, result);
        verify(titleDaoMock, times(1)).findByTitle(titleToSearch); // Verify that findByTitle was called once with titleToSearch
    }
 
    @Test
     void testGetTitlesByEmpNoAndTitle() {
        String empNo = "123";
        String titleToSearch = "Some Title";
 
     
        List<Titles> titlesList = new ArrayList<>();
        titlesList.add(new Titles(/* Construct Titles objects with empNo and titleToSearch */));
 
        when(titleDaoMock.findByEmpNoAndTitle(empNo, titleToSearch)).thenReturn(titlesList);
 
        List<Titles> result = titleService.getTitlesByEmpNoAndTitle(empNo, titleToSearch);
 
        assertEquals(titlesList, result);
        verify(titleDaoMock, times(1)).findByEmpNoAndTitle(empNo, titleToSearch); // Verify that findByEmpNoAndTitle was called once with empNo and titleToSearch
    }
    @Test
     void testDeleteTitlesByEmpNo() {
        String empNo = "123";
        List<Titles> titlesToDelete = Collections.singletonList(new Titles(/* Construct Titles object */));
 
        // Mock behavior of titleDao.findByEmpNo(empNo)
        when(titleDaoMock.findByEmpNo(empNo)).thenReturn(titlesToDelete);
 
        // Perform the delete operation
        List<Titles> deletedTitles = titleService.deleteTitlesByEmpNo(empNo);
 
        verify(titleDaoMock, times(1)).deleteByEmpNo(empNo);
 
        // Verify that the deletedTitles list matches the result of findByEmpNo
        assertEquals(titlesToDelete, deletedTitles);
    }
    
    @Test
     void testDeleteTitlesByTitle() {
        // Mock data
        String title = "Title";
        List<Titles> titlesToDelete = Collections.singletonList(new Titles(/* Construct Titles object */));
 
        // Mock behavior of titleDao.findByTitle(title)
        when(titleDaoMock.findByTitle(title)).thenReturn(titlesToDelete);
 
        // Perform the delete operation
        List<Titles> deletedTitles = titleService.deleteTitlesByTitle(title);
 
        // Verify that titleDao.deleteByTitle(title) was called only if titles were found
        if (!titlesToDelete.isEmpty()) {
            verify(titleDaoMock, times(1)).deleteByTitle(title);
        } else {
            verify(titleDaoMock, never()).deleteByTitle(title);
        }
 
        // Verify that the deletedTitles list matches the result of findByTitle
        assertEquals(titlesToDelete, deletedTitles);
    }
    @Test
     void testDeleteTitleByEmpNoFromDateAndTitle() {
        // Mock data
        String empNo = "123";
        Date fromDate = new Date();
        String title = "Title";
 
        try {
            // Perform the delete operation
            String result = titleService.deleteTitleByEmpNoFromDateAndTitle(empNo, fromDate, title);
 
            // Verify that titleDao.deleteByEmpNoFromDateAndTitle(empNo, fromDate, title) was called
            verify(titleDaoMock, times(1)).deleteByEmpNoFromDateAndTitle(empNo, fromDate, title);
 
            // Verify the result
            assertEquals("Titles deleted successfully", result);
        } catch (Exception e) {
            fail("Exception thrown during test: " + e.getMessage());
        }
    }
 
    @Test
     void testGetTitlesByEmpNoFromDateAndTitle() {
        // Mock data
        String empNo = "123";
        Date fromDate = new Date();
        String title = "Title";
        List<Titles> expectedTitles = Collections.singletonList(new Titles(/* Construct Titles object */));
 
        // Mock behavior of titleDao.findByEmpNoFromDateAndTitle(empNo, fromDate, title)
        when(titleDaoMock.findByEmpNoFromDateAndTitle(empNo, fromDate, title)).thenReturn(expectedTitles);
 
        // Perform the operation
        List<Titles> result = titleService.getTitlesByEmpNoFromDateAndTitle(empNo, fromDate, title);
 
        // Verify that the method called titleDao.findByEmpNoFromDateAndTitle
        verify(titleDaoMock, times(1)).findByEmpNoFromDateAndTitle(empNo, fromDate, title);
 
        // Verify the result
        assertEquals(expectedTitles, result);
    }
 
    @Test
     void testGetTitlesByFromDate() {
        // Mock data
        String fromDate = "2023-12-31";
        List<Titles> expectedTitles = Collections.singletonList(new Titles(/* Construct Titles object */));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 
        try {
            // Mock behavior of titleDao.findByFromDate
            when(titleDaoMock.findByFromDate(any(Date.class))).thenReturn(expectedTitles);
 
            // Perform the operation
            List<Titles> result = titleService.getTitlesByFromDate(fromDate);
 
            verify(titleDaoMock, times(1)).findByFromDate(dateFormat.parse(fromDate));
 
            assertEquals(expectedTitles, result);
        } catch (ParseException e) {
            fail("ParseException thrown during test: " + e.getMessage());
        }
    }

 
 
 
 
    @Test
     void testUpdateTitlesForEmployee_InvalidInput() {
        String empNo = null;
        List<Titles> titles = null; 
 
        try {
            titleService.updateTitlesForEmployee(empNo, titles);
 
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            verify(titleDaoMock, never()).findByEmpNo(any());
 
            assertNotNull(e.getMessage());
        }
    }  
}