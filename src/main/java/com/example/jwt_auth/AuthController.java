package com.example.jwt_auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
        UserDto userDto = userService.login(credentialsDto);
        userDto.setToken(userAuthenticationProvider.createToken(userDto.getLogin()));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid SignUpDto user) {
        UserDto createdUser = userService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(user.getLogin()));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
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
