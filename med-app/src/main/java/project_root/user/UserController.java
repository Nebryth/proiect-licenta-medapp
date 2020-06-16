package project_root.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import project_root.utilities.logging_in.LoggedIn;
import project_root.utilities.logging_in.LoginRequestModel;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;


/**
 * Holds together the implementations for the methods specified in the UserRepository
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class UserController {
    private final Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().serializeNulls().create();
    @Autowired(required = true)
    private UserRepository userRepository;
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    //Method for logging into the application
    @PostMapping(value = "/auth")
    public @ResponseBody
    String auth(HttpSession httpSession, @RequestBody LoginRequestModel loginRequestModel) {
        UserModel found = userRepository.findByEmail(loginRequestModel.getEmail());

        String sessionKey = "firstAccessTime";
        Object time = httpSession.getAttribute(sessionKey);
        if (time == null) {
            time = LocalDateTime.now();
            httpSession.setAttribute(sessionKey, time);
        }

        String plainTextPassword = loginRequestModel.getPassword();
        String hashedPassword = found.getPassword();

        if (PASSWORD_ENCODER.matches(plainTextPassword, hashedPassword)) {
            return gson.toJson(new LoggedIn(found));
        } else {
            return "{\"error\":\"Wrong credentials!\"}";
        }
    }

    @GetMapping(value = "/hello")
    public String add(){
        return "HI";
    }
}
