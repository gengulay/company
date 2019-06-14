package com.gengulay.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gengulay.demo.entity.DepartmentEntity;

@Repository(value = "departmentDAO")
@Transactional
public class DepartmentDAOImpl implements DepartmentDAO {

	@PersistenceContext
	private EntityManager manager;

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DepartmentEntity> getAllDepartments() {
		List<DepartmentEntity> depts = manager
				.createNativeQuery("Select a From DepartmentEntity a", DepartmentEntity.class).getResultList();
		return depts;
	}

	@Override
	public DepartmentEntity getDepartmentById(Integer id) {
		return manager.find(DepartmentEntity.class, id);
	}

	@Override
	public boolean addDepartment(DepartmentEntity dept) {
		try {
			manager.persist(dept);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean removeDepartment(DepartmentEntity dept) {
		try {
			manager.remove(dept);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean removeAllDepartments() {
		try {
			Query query = manager.createNamedQuery("DELETE FROM DEPARTMENT");
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}