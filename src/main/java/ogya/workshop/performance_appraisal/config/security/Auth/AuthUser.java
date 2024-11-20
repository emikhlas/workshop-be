package ogya.workshop.performance_appraisal.config.security.Auth;

import jakarta.persistence.Entity;
import lombok.*;
import ogya.workshop.performance_appraisal.entity.Role;
import ogya.workshop.performance_appraisal.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class AuthUser implements UserDetails {

    @Getter
    private User user;

    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public AuthUser(User user, List<Role> roles) {
        this.user = user;
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRolename()))
                .collect(Collectors.toList());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public String toString() {
        return "AuthUser{" +
                "username='" + user.getUsername() + '\'' +
                ", roles=" + authorities.stream()
                .map(GrantedAuthority::getAuthority)  // Extract the role names (strings)
                .collect(Collectors.joining(", ")) +
                '}';
    }


    public UUID getId() {
        return user.getId();
    }
}
