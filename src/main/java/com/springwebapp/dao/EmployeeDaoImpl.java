package com.springwebapp.dao;

import com.springwebapp.entity.Employee;
import com.springwebapp.entity.Employee_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * The DAO implementation for Employee entity
 */

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Employee> list() {
        try {

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);

            return em.createQuery(criteriaQuery).getResultList();

        } catch (Exception e) {
            System.err.println("Error in EmployeeDaoImpl.list:: " + e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public Employee save(Employee employee) {
        try {
            em.persist(employee);
        } catch (Exception e) {
            System.err.println("Exception in EmployeeDaoImpl.save:: " + e.getLocalizedMessage());
            return null;
        }

        return employee;
    }

    @Override
    public Employee findById(Long employeeId) {
        try {

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);

            Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);

            criteriaQuery.where(builder.equal(employeeRoot.get(Employee_.id), employeeId));

            return em.createQuery(criteriaQuery).setMaxResults(1).getSingleResult();

        } catch (Exception e) {
            System.err.println("Exception in EmployeeDaoImpl.findById:: " + e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public void update(Employee employee) {

        try {

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaUpdate<Employee> criteriaUpdate = builder.createCriteriaUpdate(Employee.class);

            Root<Employee> employeeRoot = criteriaUpdate.from(Employee.class);

            // criteriaUpdate.set(employeeRoot.get(Employee_.firstName), employee.getFirstName());

        } catch (Exception e) {
            System.err.println("Exception in EmployeeDaoImpl.update:: " + e.getLocalizedMessage());
        }

        em.merge(employee);
    }

    @Override
    public void delete(Long employeeId) {
        em.remove(em.find(Employee.class, employeeId));
    }
}
