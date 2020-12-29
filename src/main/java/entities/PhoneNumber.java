package entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "phone_number")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tel_number", nullable = false, length = 11)
    private Integer telNumber;

    @Column(name = "mob_number", nullable = false, length = 11)
    private Integer mobNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "add_id", nullable = false)
    private Address address;
}
