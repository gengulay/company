package com.gengulay.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gengulay.demo.entity.EmployeeEntity;

@Service
public class EmployeeDAOImpl implements EmployeeDAO {

	@PersistenceContext
	private EntityManager manager;

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeEntity> getAllEmployees() {
		Query q = manager.createQuery("Select a From EmployeeEntity a");
		List<EmployeeEntity> employees = q.getResultList();
		return employees;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeEntity> getAllEmployeesByDeptId(Integer id) {
		List<EmployeeEntity> employees = manager.createQuery("Select a From EmployeeEntity a").getResultList();
		return employees;
	}

	@Override
	public EmployeeEntity getEmployeeById(Integer id) {
		return manager.find(EmployeeEntity.class, id);
	}

	@Override
	public boolean addEmployee(EmployeeEntity employee) {
		try {
			manager.persist(employee);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean removeEmployee(EmployeeEntity employee) {
		try {
			manager.remove(employee);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean removeAllEmployees() {
		try {
			Query query = manager.createNativeQuery("DELETE FROM EMPLOYEE");
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
