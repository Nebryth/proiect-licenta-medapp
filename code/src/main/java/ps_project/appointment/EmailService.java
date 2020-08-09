package ps_project.appointment;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;
import ps_project.utility.email_service.Mail;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class represents the Observer's 'Subscriber' component
 * This is only invoked when an appointment's status changes to either 'Accepted' or 'Denied' and thereby triggers an according email
 */
public class EmailService extends Observer {

    EmailService(Subject subject) {
        this.subject = subject;
        this.subject.registerObserver(this);
    }

    /**
     * The methods is in charge of determining the type of email to be sent, based on the subject's changes (appointment status)
     */
    @Override
    public void update() {
        if (subject.getAppointmentStatus().equals("Accepted")) {
            sendEmail(subject.getPatientFullName(), subject.getPatientEmail(), subject.getAppointmentDate().toString(), "email_accepted_template");
        } else if (subject.getAppointmentStatus().equals("Denied")) {
            sendEmail(subject.getPatientFullName(), subject.getPatientEmail(), subject.getAppointmentDate().toString(), "email_denied_template");
        }
    }

    /**
     * The methods is in charge of sending an email to the patient
     *
     * @param clientName
     * @param clientEmail
     * @param appointmentDate
     * @param email_template
     */
    private static void sendEmail(String clientName, String clientEmail, String appointmentDate, String email_template) {
        Mail mailService = new Mail("medapp.doctor.smith@gmail.com", "LetDoctorIn!25");
        Path path = Paths.get("");
        String emailTemplate = path.toAbsolutePath().toString() + "\\src\\main\\java\\ps_project\\utility\\email_service";
        StringTemplateGroup group = new StringTemplateGroup("myGroup", emailTemplate, DefaultTemplateLexer.class);
        StringTemplate appointmentConfirmation = group.getInstanceOf(email_template);
        appointmentConfirmation.setAttribute("name", clientName);
        appointmentConfirmation.setAttribute("date", appointmentDate);

        String message = appointmentConfirmation.toString();
        mailService.sendMail(clientEmail, "Doctor's appointment", message);
    }
}
