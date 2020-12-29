package entities;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @Column(name = "postal_address", nullable = false)
    private String postalAddress;

    @Column(name = "city", nullable = false)
    private String city;

    @ManyToOne
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;
}
