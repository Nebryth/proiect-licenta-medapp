package ps_project.appointment.factory;

/**
 * Implements the factory for the 'Appointment' entity'
 */
public class AppointmentFactory {
    /**
     * Method for determining which type of appointment class needs to be instantiated
     *
     * @return appointment
     */

    public Appointment createAppointment(String type) {
        Appointment appointment;
        System.out.println(type);
        switch (type) {
            case "annual checkup":
                appointment = new AnnualCheckup();
                break;

            case "routine checkup":
                System.out.println("I AM A ROUTINE CHECKUP");
                appointment = new RoutineCheckup();
                break;

            case "vaccination":
                appointment = new Vaccination();
                break;

            default:
                throw new IllegalArgumentException("No such appointment!");
        }
        return appointment;
    }
}
