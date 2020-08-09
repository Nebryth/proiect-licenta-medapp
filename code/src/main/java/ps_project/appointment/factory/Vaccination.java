package ps_project.appointment.factory;

/**
 *  A specific type of appointment
 */
public class Vaccination implements Appointment {

    public String getType() {
        return "vaccination";
    }
}
