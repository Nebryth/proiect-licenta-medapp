package ps_project.appointment.factory;

/**
 * A specific type of appointment
 */
public class AnnualCheckup implements Appointment {

    public String getType() {
        return "annual checkup";
    }
}
