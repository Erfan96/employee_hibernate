package entities;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
    private Set<PhoneNumber> phoneNumbers;
}
