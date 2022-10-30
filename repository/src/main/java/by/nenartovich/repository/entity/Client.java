package by.nenartovich.repository.entity;

import by.nenartovich.repository.Role;
import by.nenartovich.repository.utils.RoleConverter;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "surname", length = 40)
    private String surname;

    @Column(name = "name", length = 40)
    private String name;

    @Column(name = "patronymic", length = 40)
    private String patronymic;

    @Column(name = "email")
    private String email;

    @Convert(converter = RoleConverter.class)
    @Column(name = "role")
    private Role role;
}