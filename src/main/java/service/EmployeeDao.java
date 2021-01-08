package service;

import entities.Address;
import entities.Employee;
import entities.PhoneNumber;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

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

    public void sample() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteria = cb.createTupleQuery();
        Root<Employee> fromEmployee = criteria.from(Employee.class);
        Root<Address> fromAddress = criteria.from(Address.class);

        Path<String> city = fromAddress.get("city");
        Path<Double> salary = fromEmployee.get("salary");

        //criteria.multiselect(city, cb.greatest(fromEmployee.get("salary")));
        List<Tuple> resultList = entityManager.createQuery(criteria).getResultList();

        resultList.forEach(row ->
                System.out.printf("City : %s, Max salary : %f%n", row.get(city), row.get(salary) ));
    }

    public void sample2() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteria = cb.createTupleQuery();
        Root<Employee> fromEmployee = criteria.from(Employee.class);
        Root<Address> fromAddress = criteria.from(Address.class);

        criteria.multiselect(
                criteria.from(Employee.class),
                criteria.from(Address.class)
        );

        TypedQuery<Tuple> query = entityManager.createQuery(criteria);
        List<Tuple> result = query.getResultList();

        for (Tuple tuple : result) {
            Employee employee = tuple.get(0, Employee.class);
            System.out.println(employee);
            Address address = tuple.get(1, Address.class);
            System.out.println(address);
        }
    }

    public void getMaxSalaryPerCity() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteria = cb.createTupleQuery();
        Root<Employee> fromEmployee = criteria.from(Employee.class);

        Join<Employee, Address> employeeAddressJoin = fromEmployee.join("addresses");
        criteria.multiselect(employeeAddressJoin.get("city"), cb.max(fromEmployee.get("salary")))
                .groupBy(employeeAddressJoin.get("city"));
        TypedQuery<Tuple> typedQuery = entityManager.createQuery(criteria);
        List<Tuple> list = typedQuery.getResultList();

        list.forEach( r ->
            System.out.println(r.get(0)+ " --> " + r.get(1)));
    }

    public void getEmployeeHasMaxSalaryPerCity(){
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
        CriteriaQuery<Tuple> criteria = criteriaBuilder.createTupleQuery();
        Root<Employee> fromEmployee = criteria.from(Employee.class);

        Subquery<Double> sq = criteria.subquery(Double.class);
        Root<Employee> subRoot = sq.from(Employee.class);
        Join<Employee, Address> empAddJoin = subRoot.join("addresses");

        sq.select(criteriaBuilder.max(subRoot.get("salary"))).groupBy(empAddJoin.get("city"));
        criteria.multiselect(fromEmployee).where((fromEmployee.get("salary").in(sq)));

        TypedQuery<Tuple> typedQuery = entityManager.createQuery(criteria);
        List<Tuple> list = typedQuery.getResultList();

        list.forEach( r ->
                System.out.println(r.get(0)+ " --> "));
    }

    public void getEmployeeWithPostalCode(String postalCode) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = getCriteriaQuery();
        Root<Employee> fromEmployee = criteria.from(Employee.class);

        Join<Employee, Address> empAddJoin = fromEmployee.join("addresses");
        criteria.select(fromEmployee).where(cb.equal(empAddJoin.get("postalCode"), postalCode));

        TypedQuery<Employee> typedQuery = entityManager.createQuery(criteria);
        System.out.println(typedQuery.getSingleResult());
    }

    public void getEmployeeWithTelNumber(String telNumber) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = getCriteriaQuery();
        Root<Employee> fromEmployee = criteria.from(Employee.class);

        Join<Employee, Address> empAddJoin = fromEmployee.join("addresses");
        Join<Address, PhoneNumber> addPhoJoin = empAddJoin.join("phoneNumbers");
        criteria.select(fromEmployee).where(cb.equal(addPhoJoin.get("telNumber"), telNumber));

        TypedQuery<Employee> typedQuery = entityManager.createQuery(criteria);
        System.out.println(typedQuery.getSingleResult());
    }
}
