package com.ilkaygunel.service;

import com.ilkaygunel.dto.AccountInputDTO;
import com.ilkaygunel.dto.AccountOutputDTO;
import com.ilkaygunel.entity.Account;
import com.ilkaygunel.mapper.AccountMapper;
import com.ilkaygunel.repository.AccountRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class AccountService {

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    public AccountOutputDTO saveUser(AccountInputDTO accountInputDTO) {
        Account entity = accountMapper.toUserEntity(accountInputDTO);
        Account savedEntity = accountRepository.save(entity);
        return accountMapper.toUserRegisterOutputDTO(savedEntity);
    }
}
