package com.hrms.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.beans.Employee;
import com.hrms.beans.Employee.Gender;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {

	@Query("SELECT e FROM Employee e WHERE e.lastName = :lastn")
	List<Employee> findEmployeesByLastName(@Param("lastn") String lastName);

	@Query("SELECT e FROM Employee e WHERE e.firstName = :fn")
	List<Employee> findEmployeesByFirstName(@Param("fn") String firstName);

	List<Employee> findByGender(Gender gender);

	@Query("SELECT e FROM Employee e WHERE e.birthDate = :date")
    List<Employee> findByEmpBirthDate(@Param("date") Date date);

	Employee findByEmpNo(Integer empNo);

	@Query("SELECT e FROM Employee e WHERE YEAR(CURRENT_DATE()) - YEAR(e.hireDate) >= :yearsOfExperience")
	List<Employee> findByEmployeesHireDate(@Param("yearsOfExperience") int yearsOfExperience);

	@Query("SELECT COUNT(e) FROM Employee e WHERE e.gender = :gender")
	long countByGender(@Param("gender") Employee.Gender gender);



	@Query("SELECT COUNT(e) FROM Employee e WHERE e.hireDate >= :date")
	int countByHireDate(@Param("date") LocalDate date);

	List<Employee> findByRole(String role);

	@Query("SELECT e FROM Employee e WHERE FUNCTION('TIMESTAMPDIFF', YEAR, e.birthDate, CURRENT_DATE) BETWEEN 50 AND 59")
	List<Employee> findEmployeesInFifties();

	@Query("SELECT DISTINCT e.role FROM Employee e")
	List<String> findAllDistinctRoles();

	@Query("SELECT COUNT(e) FROM Employee e WHERE e.gender = 'F'")
	Long countWomenEmployees();
	
	List<Employee> findByGender(String string);
	 

    
    @Query(value = "SELECT e.emp_no, e.first_name, e.last_name, e.hire_date FROM employees e JOIN dept_manager dm ON e.emp_no = dm.emp_no WHERE dm.from_date > '1987-12-10'", nativeQuery = true)
    List<Object[]> findManagersAfterDate();
 

}
