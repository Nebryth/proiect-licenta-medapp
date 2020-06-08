package utilities.emailing_service;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;

public class EmailTest {

    public static void main(String[] args) {
        Mail mailService = new Mail("medapp.doctor.smith@gmail.com", "LetDoctorIn!25");
        StringTemplateGroup group = new StringTemplateGroup("myGroup", "D:\\proiect-licenta-medapp\\med-app\\src\\main\\java\\utilities\\emailing_service\\resources", DefaultTemplateLexer.class);
        StringTemplate appointmentConfirmation = group.getInstanceOf("email_template");
        appointmentConfirmation.setAttribute("name", "World");
        String message = appointmentConfirmation.toString();
        mailService.sendMail("paltinisanu.paula@gmail.com", "Doctor's appointment confirmation", message);
    }
}

