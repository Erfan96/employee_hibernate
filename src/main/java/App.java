import entities.Employee;
import util.JpaUtil;
import javax.persistence.EntityManager;

public class App {

    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = new Employee();
        employee.setName("Ali");
        employee.setEmpCode("e1");
        employee.setSalary(250.2);

        entityManager.persist(employee);

        entityManager.getTransaction().commit();
        entityManager.close();
        JpaUtil.shutdown();
    }
}
