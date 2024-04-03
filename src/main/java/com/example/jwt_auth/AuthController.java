package com.example.jwt_auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin(origins = "http://localhost:3000",  allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,})
@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    public AuthController(UserService userService, UserAuthenticationProvider userAuthenticationProvider) {
        this.userService = userService;
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    @GetMapping("/it")
    public String hi() {
        return "Hello World";
    }

    @PostMapping("/mylogin")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
        UserDto userDto = userService.login(credentialsDto);
        userDto.setToken(userAuthenticationProvider.createToken(userDto.getLogin()));
        return ResponseEntity.ok(userDto);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserDto user) {

        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash password before saving
        UserDto createdUser = userService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(user.getLogin()));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, BlacklistedTokenRepository repository) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // Skip "Bearer " prefix
        }

        if (token != null) {
            // Check if token is already blacklisted
            if (!repository.existsByToken(token)) {
                repository.save(new BlacklistedToken(token)); // Add token to blacklist
            }
        }

        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }


}
//@RestController
//public class AuthController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/api/register")
//    public ResponseEntity<Object> registerUser(@Validated @RequestBody UserDto userDTO) {
//        try {
//            userService.registerNewUser(userDTO);
//            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
//        } catch (IllegalArgumentException ex) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
//        }
//    }
//
//    @RequiredArgsConstructor
//    public static class JwtAuthFilter extends OncePerRequestFilter {
//
//        private final UserAuthenticationProvider userAuthenticationProvider;
//
//        @Override
//        protected void doFilterInternal(
//                HttpServletRequest httpServletRequest,
//                HttpServletResponse httpServletResponse,
//                FilterChain filterChain) throws ServletException, IOException {
//            String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
//
//            if (header != null) {
//                String[] authElements = header.split(" ");
//
//                if (authElements.length == 2
//                        && "Bearer".equals(authElements[0])) {
//                    try {
//                        SecurityContextHolder.getContext().setAuthentication(
//                                userAuthenticationProvider.validateToken(authElements[1]));
//                    } catch (RuntimeException e) {
//                        SecurityContextHolder.clearContext();
//                        throw e;
//                    }
//                }
//            }
//
//            filterChain.doFilter(httpServletRequest, httpServletResponse);
//        }
//    }
//}
