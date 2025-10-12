package com.ilkaygunel.api;

import com.ilkaygunel.dto.AccountInputDTO;
import com.ilkaygunel.dto.AccountOutputDTO;
import com.ilkaygunel.service.AccountService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@Data
public class AccountAPI {

    private final AccountService accountService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountOutputDTO> createUser(@RequestBody AccountInputDTO accountInputDTO) {
        AccountOutputDTO accountOutputDTO = accountService.saveUser(accountInputDTO);
        return new ResponseEntity<>(accountOutputDTO, HttpStatus.CREATED);
    }

}
