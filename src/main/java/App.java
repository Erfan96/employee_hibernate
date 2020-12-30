import entities.Address;
import entities.Employee;
import entities.PhoneNumber;
import service.AddressDao;
import service.EmployeeDao;
import service.PhoneNumberDao;
import util.JpaUtil;
import javax.persistence.EntityManager;

public class App {

    private static EmployeeDao employeeDao;
    private static AddressDao addressDao;
    private static PhoneNumberDao phoneNumberDao;

    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        initializeDao(entityManager);
        entityManager.getTransaction().begin();

//        Employee employee = new Employee();
//        employee.setName("Hasan");
//        employee.setEmpCode("e2");
//        employee.setSalary(400.0);
//
//        employeeDao.save(employee);
//
//        Address address1 = new Address();
//        address1.setPostalCode("11-2225-6578");
//        address1.setPostalAddress("kh miri- k farzin - pelak 10");
//        address1.setCity("tehran");
//        address1.setEmployee(employee);
//
//        addressDao.save(address1);
//
//        Address address2 = new Address();
//        address2.setPostalCode("25-4561-78923");
//        address2.setPostalAddress("kh Amir- k kazemi - pelak 91.1");
//        address2.setCity("tehran");
//        address2.setEmployee(employee);
//
//        addressDao.save(address2);
//
//        PhoneNumber phoneNumber1 = new PhoneNumber();
//        phoneNumber1.setAddress(address1);
//        phoneNumber1.setMobNumber("09124569875");
//        phoneNumber1.setTelNumber("02166609851");
//
//        phoneNumberDao.save(phoneNumber1);
//
//        PhoneNumber phoneNumber2 = new PhoneNumber();
//        phoneNumber2.setAddress(address1);
//        phoneNumber2.setMobNumber("09124791297");
//        phoneNumber2.setTelNumber("02155507139");
//
//        phoneNumberDao.save(phoneNumber2);

//        Address address = addressDao.load(5);
//        System.out.println(address.getPhoneNumbers());

//        Employee employee = entityManager.find(Employee.class, 3);
//        System.out.println(employee.getName());
//        entityManager.remove(employee);


        Double aDouble = employeeDao.getMaxSalary();
        System.out.println(aDouble);

        Employee employee = employeeDao.getEmployeeHasMaxSalary();
        System.out.println(employee);

        entityManager.getTransaction().commit();
        entityManager.close();
        JpaUtil.shutdown();
    }

    private static void initializeDao(EntityManager entityManager) {
        employeeDao = new EmployeeDao(entityManager);
        addressDao = new AddressDao(entityManager);
        phoneNumberDao = new PhoneNumberDao(entityManager);
    }
}
