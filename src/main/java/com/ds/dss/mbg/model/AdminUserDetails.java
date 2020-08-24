package com.ds.dss.mbg.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AdminUserDetails implements UserDetails
{
    private DsiAdmin umsAdmin;
    private List<DsiPermission> permissionList;
    
    public AdminUserDetails(final DsiAdmin umsAdmin, final List<DsiPermission> permissionList) {
        this.umsAdmin = umsAdmin;
        this.permissionList = permissionList;
    }
    
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissionList.stream().filter(permission -> permission.getValue() != null).map(permission -> new SimpleGrantedAuthority(permission.getValue())).collect(Collectors.toList());
    }
    
    public String getPassword() {
        return this.umsAdmin.getPassword();
    }
    
    public String getUsername() {
        return this.umsAdmin.getUsername();
    }
    
    public boolean isAccountNonExpired() {
        return true;
    }
    
    public boolean isAccountNonLocked() {
        return true;
    }
    
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    public boolean isEnabled() {
        return this.umsAdmin.getStatus().equals(1);
    }
}
