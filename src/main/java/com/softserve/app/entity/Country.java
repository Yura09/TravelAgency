package com.softserve.app.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "cities")
@ToString(exclude = "cities")
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "cannot be empty")
    @Size(min = 3, message = "At least 3 letters")
    @Pattern(regexp = "[A-Z][a-zA-Z]*", message = "invalid name of country")
    private String name;

    @NotNull(message = "cannot be empty")
    private Integer code;
    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<City> cities;

}
