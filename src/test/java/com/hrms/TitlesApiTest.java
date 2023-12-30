package com.hrms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
 
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
 
import com.hrms.beans.Titles;
import com.hrms.beans.controller.TitlesApi;
import com.hrms.service.TitleService;
 
@SpringBootTest
 class TitlesApiTest {
 
    @Mock
    private TitleService titleServiceMock;
 
    @InjectMocks
    private TitlesApi titlesApi;
 
    @Test
     void testUpdateTitleByDate() {
        String date = "2023-12-31";
        String title = "OldTitle";
 
        List<Titles> updatedTitles = Arrays.asList();
        when(titleServiceMock.updateTitleByFromDate(date, title)).thenReturn(updatedTitles);
 
        List<Titles> result = titlesApi.updateTitleByDate(date, title);
 
        assertEquals(updatedTitles, result);
        verify(titleServiceMock, times(1)).updateTitleByFromDate(date, title);
    }
 
    @Test
     void testUpdateTitle() {
        String title = "OldTitle";
        String updatedTitle = "NewTitle";
 
        List<Titles> updatedTitles = Arrays.asList();
        when(titleServiceMock.updateTitleByTitle(title, updatedTitle)).thenReturn(updatedTitles);
 
        List<Titles> result = titlesApi.updateTitle(title, updatedTitle);
 
        assertEquals(updatedTitles, result);
        verify(titleServiceMock, times(1)).updateTitleByTitle(title, updatedTitle);
    }
 
    @Test
     void testGetAllTitles() {
        List<Titles> mockTitles = Arrays.asList();
 
        when(titleServiceMock.getAllTitles()).thenReturn(mockTitles);
 
        List<Titles> result = titlesApi.getAllTitles();
 
        assertEquals(mockTitles, result);
        verify(titleServiceMock, times(1)).getAllTitles();
    }
 
    @Test
     void testAddNewTitle() {
        Titles title = new Titles();
 
        String successMessage = "Title added successfully";
        when(titleServiceMock.addNewTitle(title)).thenReturn(successMessage);
 
        String result = titlesApi.addNewTitle(title);
 
        assertEquals(successMessage, result);
        verify(titleServiceMock, times(1)).addNewTitle(title);
    }
 
    @Test
     void testGetTitlesByEmpNoAndTitle() {
        String empNo = "123";
        String title = "TestTitle";
 
        List<Titles> titlesList = Arrays.asList();
        when(titleServiceMock.getTitlesByEmpNoAndTitle(empNo, title)).thenReturn(titlesList);
 
        List<Titles> result = titlesApi.getTitlesByEmpNoAndTitle(empNo, title);
 
        assertEquals(titlesList, result);
        verify(titleServiceMock, times(1)).getTitlesByEmpNoAndTitle(empNo, title);
    }
 
    @Test
     void testDeleteTitlesByEmpNo() {
        String empNo = "123";
 
        List<Titles> deletedTitles = Arrays.asList();
        when(titleServiceMock.deleteTitlesByEmpNo(empNo)).thenReturn(deletedTitles);
 
        List<Titles> result = titlesApi.deleteTitlesByEmpNo(empNo);
 
        assertEquals(deletedTitles, result);
        verify(titleServiceMock, times(1)).deleteTitlesByEmpNo(empNo);
    }
 
    @Test
     void testDeleteTitlesByTitle() {
        String title = "TestTitle";
 
        List<Titles> deletedTitles = Arrays.asList();
        when(titleServiceMock.deleteTitlesByTitle(title)).thenReturn(deletedTitles);
 
        List<Titles> result = titlesApi.deleteTitlesByTitle(title);
 
        assertEquals(deletedTitles, result);
        verify(titleServiceMock, times(1)).deleteTitlesByTitle(title);
    }
 
    @Test
     void testGetTitlesByTitle() {
        String title = "TestTitle";
 
        List<Titles> titlesList = Arrays.asList();
        when(titleServiceMock.getTitlesByTitle(title)).thenReturn(titlesList);
 
        List<Titles> result = titlesApi.getTitlesByTitle(title);
 
        assertEquals(titlesList, result);
        verify(titleServiceMock, times(1)).getTitlesByTitle(title);
    }
 
    @Test
     void testDeleteTitleByEmpNoFromDateAndTitle() throws ParseException {
        String empNo = "123";
        String fromDate = "2023-01-01";
        String title = "TestTitle";
 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedFromDate = dateFormat.parse(fromDate);
 
        String successMessage = "Title deleted successfully";
        when(titleServiceMock.deleteTitleByEmpNoFromDateAndTitle(empNo, parsedFromDate, title))
                .thenReturn(successMessage);
 
        String result = titlesApi.deleteTitleByEmpNoFromDateAndTitle(empNo, fromDate, title);
 
        assertEquals(successMessage, result);
        verify(titleServiceMock, times(1)).deleteTitleByEmpNoFromDateAndTitle(empNo, parsedFromDate, title);
    }
 
    @Test
     void testGetTitlesByEmpNoFromDateAndTitle() throws ParseException {
        String empNo = "123";
        String fromDate = "2023-01-01";
        String title = "TestTitle";
 
        List<Titles> expectedTitles = Arrays.asList();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedFromDate = dateFormat.parse(fromDate);
        when(titleServiceMock.getTitlesByEmpNoFromDateAndTitle(empNo, parsedFromDate, title))
            .thenReturn(expectedTitles);
 
        List<Titles> result = titlesApi.getTitlesByEmpNoFromDateAndTitle(empNo, fromDate, title);
 
        assertEquals(expectedTitles, result);
        verify(titleServiceMock, times(1)).getTitlesByEmpNoFromDateAndTitle(empNo, parsedFromDate, title);
    }
 
    @Test
     void testGetTitlesByFromDate() {
        String fromDate = "2023-01-01";
 
        List<Titles> expectedTitles = Arrays.asList();
        when(titleServiceMock.getTitlesByFromDate(fromDate)).thenReturn(expectedTitles);
 
        List<Titles> result = titlesApi.getTitlesByFromDate(fromDate);
 
        assertEquals(expectedTitles, result);
        verify(titleServiceMock, times(1)).getTitlesByFromDate(fromDate);
    }
 
    @Test
     void testUpdateTitlesForEmployee() {
        String empNo = "123";
        List<Titles> titles = Arrays.asList();
 
        String expectedResult = "Success";
        when(titleServiceMock.updateTitlesForEmployee(empNo, titles)).thenReturn(expectedResult);
 
        String result = titlesApi.updateTitlesForEmployee(empNo, titles);
 
        assertEquals(expectedResult, result);
        verify(titleServiceMock, times(1)).updateTitlesForEmployee(empNo, titles);
    }
 
    @Test
     void testUpdateTitleByEmpNoFromDateAndTitle() {
        String empNo = "123";
        String fromDate = "2023-01-01";
        String title = "TestTitle";
 
        String expectedResult = "Success";
        when(titleServiceMock.updateTitleByEmpNoFromDateAndTitle(empNo, fromDate, title)).thenReturn(expectedResult);
 
        String result = titlesApi.updateTitleByEmpNoFromDateAndTitle(empNo, fromDate, title);
 
        assertEquals(expectedResult, result);
        verify(titleServiceMock, times(1)).updateTitleByEmpNoFromDateAndTitle(empNo, fromDate, title);
    }
}
