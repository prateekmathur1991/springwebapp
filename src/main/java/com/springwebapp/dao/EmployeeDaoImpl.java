package com.springwebapp.dao;

import com.springwebapp.entity.Employee;
import com.springwebapp.entity.Employee_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
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

            criteriaQuery.select(criteriaQuery.from(Employee.class));

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
            CriteriaUpdate<Employee> employeeCriteriaUpdate = builder.createCriteriaUpdate(Employee.class);

            Root<Employee> employeeRoot = employeeCriteriaUpdate.from(Employee.class);

            employeeCriteriaUpdate.set(employeeRoot.get(Employee_.firstName), employee.getFirstName());
            employeeCriteriaUpdate.set(employeeRoot.get(Employee_.lastName), employee.getLastName());
            employeeCriteriaUpdate.set(employeeRoot.get(Employee_.salary), employee.getSalary());

            employeeCriteriaUpdate.where(builder.equal(employeeRoot.get(Employee_.id), employee.getId()));

            em.createQuery(employeeCriteriaUpdate).executeUpdate();

        } catch (Exception e) {
            System.err.println("Exception in EmployeeDaoImpl.update:: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(Long employeeId) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaDelete<Employee> employeeCriteriaDelete = builder.createCriteriaDelete(Employee.class);

        Root<Employee> employeeRoot = employeeCriteriaDelete.from(Employee.class);

        employeeCriteriaDelete.where(builder.equal(employeeRoot.get(Employee_.id), employeeId));

        em.createQuery(employeeCriteriaDelete).executeUpdate();
    }
}
