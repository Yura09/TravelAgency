package com.softserve.app.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "rooms")
@ToString(exclude = "rooms")
@Table(name = "room_types")
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Pattern(regexp = "[A-Z][a-zA-Z]*", message = "Use letters only please")
    private String name;
    @NotNull
    private String description;
    @OneToMany(mappedBy = "roomType")
    private List<Room> rooms;
}
