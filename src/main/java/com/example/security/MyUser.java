package com.example.security;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: GuanBin
 * @date: Created in 上午10:14 2020/9/22
 */
@Data
public class MyUser implements Serializable {
    private static final long serialVersionUID = 7672152707514151316L;

    private String userName;
    private String password;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
}
