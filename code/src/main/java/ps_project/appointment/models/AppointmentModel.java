package ps_project.appointment.models;

import javax.persistence.*;
import java.util.Date;

//Translates the appointments table (containing the patient, doctor, the date and the type) into a class entity, also used within the requests.

@Entity
@Table(name = "appointments")
public class AppointmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //The 'doctorId' field stands for the 'staffId' of the doctor who is in charge of the appointment
    private Integer doctorId;
    private Integer patientId;
    private Date appointmentDate;
    private String appointmentType;


    public AppointmentModel() {

    }

    public AppointmentModel(int patient_id, int user_id, String appointmentType, Date appointmentDate) {
        this.appointmentType = appointmentType;
        this.patientId = patient_id;
        this.doctorId = user_id;
        this.appointmentDate = appointmentDate;
    }

    public AppointmentModel(ps_project.appointment.factory.Appointment appointment) {
        appointmentType = appointment.getType();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}