package com.hrms.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.Departments;
import com.hrms.dao.DepartmentDao;
import com.hrms.exception.DepartmentCreationException;

import jakarta.transaction.Transactional;

@Service
//@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao deptDao;

	@Override
	public Departments createDepartment(Departments department) {
	    try {
	        return deptDao.save(department);
	    } catch (Exception e) {
	        throw new DepartmentCreationException("Error occurred while creating department");
	    }
	}


	@Override
	public List<Departments> getAllDepartments() {
		try {
			return deptDao.findAll();
		} catch (Exception e) {
			
			throw new DepartmentCreationException("Error occurred while retrieving departments");
		}
	}

	@Override
	public Departments updateDepartments(String deptNo, Departments departments) {
		try {
			Optional<Departments> optionalDepartment = deptDao.findByDeptNo(deptNo);
			return optionalDepartment.map(existingDepartment -> {
				existingDepartment.setDeptName(departments.getDeptName());
				return deptDao.save(existingDepartment);
			}).orElse(null);
		} catch (Exception e) {

			throw new DepartmentCreationException("Error occurred while updating departments");

		}

	}

	 @Override
	    public String deleteDepartmentString(String deptNo) {
	        try {
	            Optional<Departments> optionalDepartment = deptDao.findById(deptNo);
	            if (optionalDepartment.isPresent()) {
	                deptDao.deleteById(deptNo);
	                return "Department deleted successfully";
	            } else {
	                throw new DepartmentCreationException("No such department in the database");
	            }
	        } catch (NoSuchElementException e) {
	            return "Department not found";
	        } catch (Exception e) {
	            throw new DepartmentCreationException("Error occurred while deleting department");
	        }
	    }


	@Override
	public String deleteDepartment(String deptName) {
		try {
			Optional<Departments> optionalDepartment = deptDao.findById(deptName);
			if (optionalDepartment.isPresent()) {

				deptDao.deleteById(deptName);
				return "Department deleted successfully";
			} else {
				throw new DepartmentCreationException("No such department in the database");
			}
		} catch (DepartmentCreationException e) {

			return "Department not found";
		} catch (Exception e) {

			throw new DepartmentCreationException("Error occurred while deleting department");
		}
	}

	@Override
	public Departments getDepartmentByDeptNo(String deptNo) {
		try {
			Optional<Departments> optionalDepartment = deptDao.findByDeptNo(deptNo);
			return optionalDepartment.orElseThrow(
					() -> new DepartmentCreationException("No department found with department number: " + deptNo));

		} catch (Exception e) {
			throw new DepartmentCreationException("Error occurred while retrieving department");
		}
	}

	@Override
	public List<Departments> getDepartmentsByName(String deptName) {
		try {
			return deptDao.findByDeptName(deptName);
		} catch (Exception e) {

			throw new DepartmentCreationException("Error occurred while retrieving departments by name");
		}
	}

	@Override
	public Departments updateDepartmentsByName(String deptName, Departments departments) {
		try {
			Optional<Departments> optionalDepartment = deptDao.findByDeptNo(deptName);
			return optionalDepartment.map(existingDepartment -> {
				existingDepartment.setDeptName(departments.getDeptName());
				return deptDao.save(existingDepartment);
			}).orElse(null);
		} catch (Exception e) {

			throw new DepartmentCreationException("Error occurred while updating departments");
		}
	}
}
