package com.example.coursework.entity;

import com.example.coursework.dto.UserRegisterDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "firstname", length = 50)
    private String firstname;

    @Column(name = "patronymic", length = 50)
    private String patronymic;

    @Column(name = "lastname", length = 50)
    private String lastname;

    @Column(name = "phone_number", length = 10)
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "activate")
    private Boolean activate;

    @Column(name = "activationcode", length = Integer.MAX_VALUE)
    private String activationcode;

    @Column(name = "number_of_hidden_ads")
    private Integer numberOfHiddenAds;

    @Column(name = "locked")
    private Boolean locked;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
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

   /* @OneToMany(mappedBy = "userEmail")
    private Set<Advertisement> advertisements = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userEmail")
    private Set<LockUser> lockUsers = new LinkedHashSet<>();*/

    public static User fromUserDTO(UserRegisterDTO userRegisterDTO) {

        return User.builder()
                .email(userRegisterDTO.getEmail())
                .password(userRegisterDTO.getPassword())
                .lastname(userRegisterDTO.getLastname())
                .firstname(userRegisterDTO.getFirstname())
                .phoneNumber(userRegisterDTO.getPhoneNumber())
                .patronymic(userRegisterDTO.getPatronymic())
                .locked(false)
                .activate(true)
                .numberOfHiddenAds(0)
                .role(Role.USER)
                .build();
    }
}