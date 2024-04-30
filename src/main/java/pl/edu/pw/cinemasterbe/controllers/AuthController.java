package pl.edu.pw.cinemasterbe.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.cinemasterbe.model.dto.UserDto;
import pl.edu.pw.cinemasterbe.model.dto.UserLogin;
import pl.edu.pw.cinemasterbe.model.mappers.UserMapper;
import pl.edu.pw.cinemasterbe.security.SecurityConfiguration;
import pl.edu.pw.cinemasterbe.services.JwtService;
import pl.edu.pw.cinemasterbe.services.UserService;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthenticationManager authManager;
    private final UserService userService;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<UserDto> login(@RequestBody UserLogin credentials, HttpServletResponse response) {
        var authToken = authManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authToken);
        var user = userService.getUserByUsername(credentials.getUsername());
        var token = jwtService.generateJwt(user.getUsername());

        var jwtCookie = new Cookie(SecurityConfiguration.JWT_COOKIE_NAME, token);
        jwtCookie.setHttpOnly(true);
        response.addCookie(jwtCookie);

        return ResponseEntity.ok(userMapper.map(user));
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        var jwtCookie = new Cookie(SecurityConfiguration.JWT_COOKIE_NAME, "");
        jwtCookie.setHttpOnly(true);
        response.addCookie(jwtCookie);

        return ResponseEntity.ok().build();
    }
}
