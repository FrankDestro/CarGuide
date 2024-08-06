package com.sys.CarGuide.models.projection;

public interface UserDetailsProjection {

    Long getId();
    String getUsername();
    String getPassword();
    Long getRoleId();
    String getAuthority();
}
