package com.itrifid.reeldekho.entity;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.itrifid.reeldekho.entity.Permission.*;


@Getter
public enum Role {

  ADMIN(
          Set.of(
                  ADMIN_READ,
                  ADMIN_UPDATE,
                  ADMIN_DELETE
          )
  ),
  MANAGER(
          Set.of(
                  MANAGER_UPDATE,
                  MANAGER_DELETE
          )
  ),
  USER(
          Set.of(
                  USER_READ,
                  USER_UPDATE,
                  USER_DELETE
          )
  )
  ;



  private final Set<Permission> permissions;

  private Role(Set<Permission> permissions) {
    this.permissions = permissions;
  }


  public List<SimpleGrantedAuthority> getAuthorities() {
    System.out.println(permissions);
    var authorities = permissions
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    System.out.println(authorities);
    return authorities;
  }
}
