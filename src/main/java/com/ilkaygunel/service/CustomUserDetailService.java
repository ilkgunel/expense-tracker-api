package com.ilkaygunel.service;

import com.ilkaygunel.entity.Account;
import com.ilkaygunel.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Account couldn't not found with email: " + username));

        return new User(account.getEmail(), account.getPassword(), account.isEnabled(), true, true, true,
                account.getRoles().stream().map(role -> new SimpleGrantedAuthority(account.getName())).collect(Collectors.toList()));
    }
}
