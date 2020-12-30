package service;

import entities.Employee;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class EmployeeDao extends EntityDao<Employee, Integer> {

    private static final String JPQL_MaxSalary = "select max(emp.salary) from Employee emp";
    private static final String JPQL_EmployeeHasMaxSalary = "";

    public EmployeeDao(EntityManager entityManager) {
        super(entityManager);
    }

    public Class<Employee> getEntityClass() {
        return Employee.class;
    }

    public Double getMaxSalary() {
        TypedQuery<Double> query = super.entityManager.createQuery(JPQL_MaxSalary, Double.class);
        return query.getSingleResult();
    }

    public Employee getEmployeeHasMaxSalary() {
        TypedQuery<Employee> typedQuery = super.entityManager.createQuery(, Employee.class);
        return typedQuery.getSingleResult();
    }
}
