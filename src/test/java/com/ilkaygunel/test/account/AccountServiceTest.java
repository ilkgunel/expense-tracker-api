package com.ilkaygunel.test.account;

import com.ilkaygunel.dto.AccountInputDTO;
import com.ilkaygunel.dto.AccountOutputDTO;
import com.ilkaygunel.entity.Account;
import com.ilkaygunel.mapper.AccountMapper;
import com.ilkaygunel.repository.AccountRepository;
import com.ilkaygunel.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Spy
    @InjectMocks
    private AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

    @InjectMocks
    private AccountService accountService;

    @Test
    public void saveAccountTest() {
        Account savedAccount = new Account();
        savedAccount.setId(1L);
        savedAccount.setName("İlkay Günel");
        savedAccount.setEmail("ilkgunel93@gmail.com");
        savedAccount.setPassword("admin");
        savedAccount.setEnabled(true);
        when(accountRepository.save(any(Account.class))).thenReturn(savedAccount);

        AccountInputDTO accountInputDTO = new AccountInputDTO();
        accountInputDTO.setEmail("ilkgunel93@gmail.com");
        accountInputDTO.setName("İlkay Günel");
        accountInputDTO.setPassword("admin");

        AccountOutputDTO actualSavedAccountOutputDto = accountService.saveAccount(accountInputDTO);

        assertEquals(actualSavedAccountOutputDto.getId(), 1L);
        assertEquals(actualSavedAccountOutputDto.getEmail(), accountInputDTO.getEmail());

    }
}
