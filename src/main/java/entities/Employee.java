package entities;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employee")
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany
    private Set<Address> address;
}
