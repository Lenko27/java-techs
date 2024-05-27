package Lenko27.entities;

import Lenko27.enums.Breeds;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(schema = "cats_and_owners", name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "breed")
    private Breeds breed;

    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToMany
    @JoinTable(schema = "cats_and_owners",
            name="cat_friends",
            joinColumns=  @JoinColumn(name="cat_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="friend_id", referencedColumnName="id"))
    private List<Cat> friends;
}