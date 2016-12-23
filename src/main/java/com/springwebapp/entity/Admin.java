/**
 * Represents an admin user
 *
 * This class also implements the {@link org.springframework.security.core.userdetails.UserDetails}
 * interface, so that authentication information required by Spring Security, like account_expired,
 * account_locked, credentials_expired, and authorities can be made available directly from the entity.
 */

package com.springwebapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "admin")
@NoArgsConstructor
public class Admin implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Getter @Setter
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @Getter @Setter
    private String name;

    @Column(name = "username", nullable = false, unique = true)
    @Setter
    private String username;

    @Column(name = "password", nullable = false, unique = true)
    @Setter
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "admin")
    @Setter
    private Set<AdminRole> authorities;

    @Column(name = "is_account_non_expired", nullable = false)
    @Setter
    private Boolean isAccountNonExpired = Boolean.TRUE;

    @Column(name = "is_account_non_locked", nullable = false)
    @Setter
    private Boolean isAccountNonLocked = Boolean.TRUE;

    @Column(name = "is_credentials_non_expired", nullable = false)
    @Setter
    private Boolean isCredentialsNonExpired = Boolean.TRUE;

    @Column(name = "is_enabled", nullable = false)
    @Setter
    private Boolean isEnabled = Boolean.TRUE;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.authorities == null)   {
            return Collections.emptySet();
        }

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (AdminRole role : this.authorities) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}