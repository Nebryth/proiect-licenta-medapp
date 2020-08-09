package ps_project.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ps_project.staff.StaffModel;
import ps_project.staff.StaffRepository;
import ps_project.staff.StaffRequestModel;
import ps_project.utility.email_service.Mail;
import ps_project.utility.logging_in.LoggedIn;
import ps_project.utility.logging_in.LoginRequestModel;

import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.time.LocalDateTime;


/**
 * Holds together the implementations for the methods specified in the UserRepository
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class UserController {
    private final Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().serializeNulls().create();

    char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?")).toCharArray();
    // ^ Code taken from google


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
//    private PatientRepository patientRepository;
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    //Method for creating a new staff member
    @RequestMapping(value = "/staff", method = RequestMethod.POST)
    @ResponseBody
    public String addStaff(@RequestBody StaffRequestModel staffRequestModel) {
        //Generating the user's future password
        String randomStr = RandomStringUtils.random(6, 0, possibleCharacters.length - 1, false, false, possibleCharacters, new SecureRandom());
        // ^ Code taken from google

        UserModel staffUser = new UserModel();
        staffUser.setFirstName(staffRequestModel.getFirst_name());
        staffUser.setLastName(staffRequestModel.getLast_name());
        staffUser.setTelephone(staffRequestModel.getTelephone());
        staffUser.setEmail(staffRequestModel.getEmail());
        staffUser.setPassword(randomStr);

        //Implictly set: using2FA (false) & secretKey (empty)
        staffUser.setUsing2FA(false);
        staffUser.setSecretKey("");
        staffUser.setRoleId(staffRequestModel.getRole_id());
        staffUser.setCustomerId(staffRequestModel.getCustomer_id());
        System.out.println("First Name: " + staffRequestModel.getFirst_name());
        System.out.println("Last Name: " + staffRequestModel.getLast_name());
        System.out.println("Telephone: " + staffRequestModel.getTelephone());
        System.out.println("Email: " + staffRequestModel.getEmail());

        userRepository.save(staffUser);

        //staff.setId();
        StaffModel staff = new StaffModel();
        staff.setUserId(staffUser.getId());
        staffRepository.save(staff);

        //Email the password & the email (randomStr & staffRequestModel.getEmail)
        Mail mailService = new Mail("medapp.doctor.smith@gmail.com", "LetDoctorIn!25");
        StringTemplateGroup group = new StringTemplateGroup("myGroup", "D:\\PS_PROJECT\\paulapaltinisanu\\src\\main\\java\\ps_project\\utility\\email_service", DefaultTemplateLexer.class);
        StringTemplate accountConfirmation = group.getInstanceOf("email_account_created");
        accountConfirmation.setAttribute("user_email", staffRequestModel.getEmail());
        accountConfirmation.setAttribute("user_password", randomStr);
        mailService.sendMail(staffUser.getEmail(), "Înregistrare în rețeaua Med App", accountConfirmation.toString());

        return gson.toJson(staffUser);
    }

    //Method for creating a new patient
//    @RequestMapping(value = "/patient", method = RequestMethod.POST)
//    @ResponseBody
//    public String addPatient(@RequestBody PatientRequestModel patientRequestModel) {
//        String randomStr = RandomStringUtils.random(6, 0, possibleCharacters.length - 1, false, false, possibleCharacters, new SecureRandom());
//        UserModel patientUser = new UserModel();
//        patientUser.setFirstName(patientRequestModel.getFirst_name());
//        patientUser.setLastName(patientRequestModel.getLast_name());
//        patientUser.setEmail(patientRequestModel.getEmail());
//        patientUser.setTelephone(patientRequestModel.getTelephone());
//        patientUser.setPassword(randomStr);
//        patientUser.setUsing2FA(false);
//        patientUser.setSecretKey("");
//        patientUser.setRoleId(4);
//        patientUser.setCustomerId(patientRequestModel.getCustomer_id());
//        userRepository.save(patientUser);
//
//        //Setting the patient's specific data
//        PatientModel patient = new PatientModel();
//        patient.setAddress(patientRequestModel.getAddress());
//        patient.setBirthday(patientRequestModel.getBirthday());
//        patientRepository.save(patient);
//
//        Mail mailService = new Mail("medapp.doctor.smith@gmail.com", "LetDoctorIn!25");
//        StringTemplateGroup group = new StringTemplateGroup("myGroup", "D:\\PS_PROJECT\\paulapaltinisanu\\src\\main\\java\\ps_project\\utility\\email_service", DefaultTemplateLexer.class);
//        StringTemplate accountConfirmation = group.getInstanceOf("email_account_created");
//        accountConfirmation.setAttribute("user_email", patientRequestModel.getEmail());
//        accountConfirmation.setAttribute("user_password", randomStr);
//        mailService.sendMail(patientUser.getEmail(), "Înregistrare în rețeaua Med App", accountConfirmation.toString());
//
//        return gson.toJson(patientUser);
//    }

    //Method for logging into the application
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ResponseBody
    public String auth(HttpSession httpSession, @RequestBody LoginRequestModel loginRequestModel) {
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


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<UserModel> getAllUsers() {

        return userRepository.findAll();
    }


//    @RequestMapping(value = "/controller", method = RequestMethod.GET)
//    @ResponseBody
//    public String foo() {
//        return "Response!";
//    }

}
