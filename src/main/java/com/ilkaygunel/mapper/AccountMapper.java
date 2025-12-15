package com.ilkaygunel.mapper;

import com.ilkaygunel.dto.AccountInputDTO;
import com.ilkaygunel.dto.AccountOutputDTO;
import com.ilkaygunel.entity.Account;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class AccountMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    public abstract Account dtoToEntity(AccountInputDTO source);

    public abstract AccountOutputDTO entityToDto(Account account);

    @AfterMapping
    protected void encodePassword(AccountInputDTO source, @MappingTarget Account target) {
        if (source.getPassword() != null) {
            target.setPassword(passwordEncoder.encode(source.getPassword()));
        }
    }
}
