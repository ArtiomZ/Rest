package ru.netology.restproject;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Configuration
@Controller
public class AuthorizationController {

    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );
        this.service = context.getBean("service", AuthorizationService.class);
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user, @RequestParam("password") String password) {
        List<Authorities> list;
        try {
            list = service.getAuthorities(user, password);
        } catch (InvalidCredentials e) {
            throw new InvalidCredentials("User name or password is empty");
        } catch (UnauthorizedUser e) {
            throw new UnauthorizedUser("Unknown user " + user);
        }
        return list;
    }

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> ICHandler(InvalidCredentials e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> UUHandler(UnauthorizedUser e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}