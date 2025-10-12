package com.ilkaygunel.mapper;

import com.ilkaygunel.dto.AccountInputDTO;
import com.ilkaygunel.dto.AccountOutputDTO;
import com.ilkaygunel.entity.Account;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Data
public class AccountMapper {

    private final PasswordEncoder passwordEncoder;

    public Account toUserEntity(AccountInputDTO accountInputDTO) {
        Account entity = new Account();

        entity.setEmail(accountInputDTO.getEmail());
        entity.setName(accountInputDTO.getName());
        entity.setPassword(passwordEncoder.encode(accountInputDTO.getPassword()));

        return entity;
    }

    public AccountOutputDTO toUserRegisterOutputDTO(Account account) {
        AccountOutputDTO accountOutputDTO = new AccountOutputDTO();

        accountOutputDTO.setId(account.getId());
        accountOutputDTO.setEmail(account.getEmail());
        accountOutputDTO.setName(account.getName());

        return accountOutputDTO;
    }
}
