package service;

import entities.Employee;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class EmployeeDao extends EntityDao<Employee, Integer> {

    private static final String JPQL_MaxSalary = "select max(emp.salary) from Employee emp";
    private static final String JPQL_EmployeeHasMaxSalary =
            "select emp from Employee emp where emp.salary = (select max(emp2.salary) from Employee emp2)";

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
        TypedQuery<Employee> typedQuery = super.entityManager.createQuery(JPQL_EmployeeHasMaxSalary, Employee.class);
        return typedQuery.getSingleResult();
    }
}
