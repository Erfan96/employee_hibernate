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
//        employee.setName("Shahram");
//        employee.setEmpCode("e4");
//        employee.setSalary(500.0);
//
//        employeeDao.save(employee);
//
//        Address address1 = new Address();
//        address1.setPostalCode("49-7364-1982");
//        address1.setPostalAddress("kh akrami- k lesani - pelak 753");
//        address1.setCity("kerman");
//        address1.setEmployee(employee);
//
//        addressDao.save(address1);
//
//        Address address2 = new Address();
//        address2.setPostalCode("25-4561-78923");
//        address2.setPostalAddress("kh shiri- k hakem - pelak 22");
//        address2.setCity("kerman");
//        address2.setEmployee(employee);
//
//        addressDao.save(address2);
//
//        PhoneNumber phoneNumber1 = new PhoneNumber();
//        phoneNumber1.setAddress(address1);
//        phoneNumber1.setMobNumber("09174569875");
//        phoneNumber1.setTelNumber("09176564555");
//
//        phoneNumberDao.save(phoneNumber1);
//
//        PhoneNumber phoneNumber2 = new PhoneNumber();
//        phoneNumber2.setAddress(address1);
//        phoneNumber2.setMobNumber("09172226644");
//        phoneNumber2.setTelNumber("09173456789");
//
//        phoneNumberDao.save(phoneNumber2);

//        Address address = addressDao.load(5);
//        System.out.println(address.getPhoneNumbers());
//
//        Employee employee = entityManager.find(Employee.class, 3);
//        System.out.println(employee.getName());
//        entityManager.remove(employee);


        Double aDouble = employeeDao.getMaxSalary();
        System.out.println(aDouble);

        Employee employeeA = employeeDao.getEmployeeHasMaxSalary();
        System.out.println(employeeA);


        employeeDao.getMaxSalaryPerCity();

        employeeDao.getEmployeeHasMaxSalaryPerCity();

        employeeDao.getEmployeeWithPostalCode("54-9874-2594");

        employeeDao.getEmployeeWithTelNumber("09173456789");

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
