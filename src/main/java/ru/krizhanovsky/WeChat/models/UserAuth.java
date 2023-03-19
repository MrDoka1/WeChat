package ru.krizhanovsky.WeChat.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.krizhanovsky.WeChat.classes.Password;

import java.util.Collection;
import java.util.Set;

// ***** База данных для аутентификации пользователей *****
@Entity
@Table(name = "users_auth")
public class UserAuth implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String phonenumber;
    private String password;
    private boolean is_twofactor_auth;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> role;

    public UserAuth() {
    }

    public UserAuth(String phonenumber, String password, boolean is_twofactor_auth) {
        Password password1 = new Password();

        this.phonenumber = phonenumber;
        this.password = password1.encodePassword(password);
        this.is_twofactor_auth = is_twofactor_auth;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRole();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
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
        return isEnabled();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIs_twofactor_auth() {
        return is_twofactor_auth;
    }

    public void setIs_twofactor_auth(boolean is_twofactor_auth) {
        this.is_twofactor_auth = is_twofactor_auth;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }
}