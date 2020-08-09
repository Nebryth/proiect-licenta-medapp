package ps_project.appointment.factory;

/**
 * A specific type of appointment
 */
public class RoutineCheckup implements Appointment {

    public String getType() {
        return "routine checkup";
    }
}
