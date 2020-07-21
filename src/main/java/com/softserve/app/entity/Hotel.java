package com.softserve.app.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "rooms")
@ToString(exclude = "rooms")
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "cannot be empty")
    private String name;
    @NotNull(message = "cannot be empty")
    private String address;
    @Column(name = "star_rating")
    @NotNull(message = "cannot be empty")
    @Min(1)
    @Max(5)
    private Integer starRating;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;
}
