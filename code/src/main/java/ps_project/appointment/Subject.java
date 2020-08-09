package ps_project.appointment;


import java.util.ArrayList;

/**
 * This class represent the Observer's "Publisher' component
 * The subject itself represents a certain appointment
 */
public class Subject {
    private String patientEmail;
    private String patientFullName;
    private String appointmentStatus;
    private Integer appointmentDate;
    private ArrayList<Observer> observers = new ArrayList<>();

    String getPatientEmail() {
        return patientEmail;
    }

    String getPatientFullName() {
        return patientFullName;
    }

    String getAppointmentStatus() {
        return appointmentStatus;
    }

    Integer getAppointmentDate() {
        return appointmentDate;
    }


    void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observerElement : observers) {
            observerElement.update();
        }
    }

    /**
     * This methods sends the updated subject information to its subscriber (the email service)
     *
     * @param patientEmail
     * @param patientFullName
     * @param appointmentStatus
     * @param appointmentDate
     */
    void updateAppointmentStatus(String patientEmail, String patientFullName, String appointmentStatus, Integer appointmentDate) {
        this.patientEmail = patientEmail;
        this.patientFullName = patientFullName;
        this.appointmentStatus = appointmentStatus;
        this.appointmentDate = appointmentDate;

        notifyObservers();
    }
}
