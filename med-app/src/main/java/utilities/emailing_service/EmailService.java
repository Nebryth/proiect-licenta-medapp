package utilities.emailing_service;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class EmailService {

    public static void sendAppointmentNotification(String clientName, String clientEmail, Date appointmentDate) {
        sendEmail(clientName, clientEmail, appointmentDate, "email_template");
    }

    public static void sendAppointmentAccepted(String clientName, String clientEmail, Date appointmentDate) {
        sendEmail(clientName, clientEmail, appointmentDate, "email_accepted_template");
    }

    public static void sendAppointmentDenied(String clientName, String clientEmail, Date appointmentDate) {
        sendEmail(clientName, clientEmail, appointmentDate, "email_denied_template");
    }

    private static void sendEmail(String clientName, String clientEmail, Date appointmentDate, String email_accepted_template) {
        Mail mailService = new Mail("medapp.doctor.smith@gmail.com", "LetDoctorIn!25");
        Path path = Paths.get("");
        String emailTemplate = path.toAbsolutePath().toString() + "\\src\\main\\java\\med_app\\services\\emailing";
        StringTemplateGroup group = new StringTemplateGroup("myGroup", emailTemplate, DefaultTemplateLexer.class);
        StringTemplate appointmentConfirmation = group.getInstanceOf(email_accepted_template);
        appointmentConfirmation.setAttribute("name", clientName);
        appointmentConfirmation.setAttribute("date", appointmentDate.toString());

        String message = appointmentConfirmation.toString();
        mailService.sendMail(clientEmail, "Doctor's appointment", message);
    }
}
