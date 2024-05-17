package com.example.homework11.service;

import com.example.homework11.model.Account;
import com.example.homework11.model.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class AccountService implements UserDetailsService {
    @Autowired
    AccountMapper accountMapper;
    /**
     * 1. 先根据用户名拿到账户信息;
     * 2. 再根据账户信息拿到该账户对应的角色信息
     * @param username
     * @return
     * @throws
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        Account accountByUsername = accountMapper.getAccountByUsername(username);
        if (accountByUsername == null){
            throw new UsernameNotFoundException("用户名不存在!");
        }
        accountByUsername.setRoles(accountMapper.getAccountRoleById(accountByUsername.getId()));
        return accountByUsername;
    }
}