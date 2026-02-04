package com.ilkaygunel.api;

import com.ilkaygunel.dto.AuthRequestDTO;
import com.ilkaygunel.security.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Data
@Tag(name = "Auth Management", description = "Services for managing auth operations.")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    @Operation(summary = "Login", description = "Authenticates and gives the JWT")
    @SuppressWarnings("unused")
    @PostMapping("/login")
    public String login(@RequestBody AuthRequestDTO authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

        return jwtUtil.generateToken(authentication.getName());
    }
}