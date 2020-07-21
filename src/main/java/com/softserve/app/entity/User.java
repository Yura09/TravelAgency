package com.softserve.app.entity;

import com.softserve.app.entity.enums.ROLE;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "reservations")
@ToString(exclude = "reservations")
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    @NotNull
    @Pattern(regexp = "[A-Z][a-zA-Z]*", message = "Use letters only please")
    @Min(3)
    private String firstName;
    @Column(name = "last_name")
    @NotNull
    @Pattern(regexp = "[A-Z][a-zA-Z]*", message = "Use letters only please")
    @Min(3)
    private String lastName;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ROLE role;
    @NotNull
    @Email(message = "Enter a valid email address.")
    private String email;
    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    private String password;
    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
     //   AuthorityUtils.createAuthorityList(role.name());
        return AuthorityUtils.createAuthorityList(role.name());
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
