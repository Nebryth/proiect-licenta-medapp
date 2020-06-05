package utilities.emailing_service;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;

public class Test {

    public static void main(String[] args) {


        Mail mailService = new Mail("medapp.doctor.smith@gmail.com", "LetDoctorIn!25");

        StringTemplateGroup group =  new StringTemplateGroup("myGroup", "D:\\proiect-licenta-medapp\\PROJECT\\src\\utilities\\emailing_service\\Templates", DefaultTemplateLexer.class);
        StringTemplate appointmentConfirmation = group.getInstanceOf("email_template");



        // name = patient' name
        appointmentConfirmation.setAttribute("name", "World");
        String message = appointmentConfirmation.toString();
        mailService.sendMail("paltinisanu.paula@gmail.com","Doctor's appointment confirmation", message);
    }

}