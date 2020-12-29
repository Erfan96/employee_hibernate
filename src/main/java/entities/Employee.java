package entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "employee")
@Setter @Getter
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "emp_name", nullable = false, length = 40)
    private String name;

    @Column(name = "emp_code", nullable = false, length = 50)
    private String empCode;

    @Column(name = "salary", nullable = false)
    private Double salary;
}
