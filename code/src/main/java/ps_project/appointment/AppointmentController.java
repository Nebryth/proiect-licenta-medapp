//package ps_project.appointment;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import ps_project.appointment.factory.Appointment;
//import ps_project.appointment.factory.AppointmentFactory;
//import ps_project.appointment.models.AppointmentRequestModel;
//import ps_project.appointment.repo.AppointmentRepository;
//import ps_project.patient.PatientModel;
//import ps_project.patient.PatientRepository;
//
///**
// * Holds together the implementations for the methods specified in the AppointmentRepository
// */
//@Controller
//@CrossOrigin(origins = "*")
//@RequestMapping(path = "/api")
//public class AppointmentController {
//
//    Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().serializeNulls().create();
//    Subject subject = new Subject();
//    EmailService emailService = new EmailService(subject);
//
//    @Autowired
//    AppointmentRepository appointmentRepository;
//    @Autowired
//    PatientRepository patientRepository;
//
//
//    /**
//     * Method for returning all the existing appointments
//     *
//     * @return
//     */
//    @GetMapping(path = "/appointments")
//    public @ResponseBody
//    Iterable<AppointmentRequestModel> getAllAppointments() {
//
//        return appointmentRepository.findAll();
//    }
//
//
//    @GetMapping(path = "/appointments",
//            params = {"user_id"})
//    public @ResponseBody
//    Iterable<AppointmentRequestModel> getAllAppointments(@RequestParam(value = "user_id") Integer user_id) {
//        if (user_id != null)
//            return appointmentRepository.findAppointmentByUserIdAndAppointmentTypeNot(user_id, "denied");
//        return appointmentRepository.findAll();
//    }
//
//    @GetMapping(path = "/appointments",
//            params = {"begin_date", "end_date"})
//    public @ResponseBody
//    Iterable<AppointmentRequestModel> getAllAppointments(
//            @RequestParam(value = "begin_date") Integer begin_date,
//            @RequestParam(value = "end_date") Integer end_date) {
//        if (begin_date != null && end_date != null)
//            return appointmentRepository.findByAppointmentDateBetweenAndAppointmentTypeNot(begin_date, end_date, "denied");
//        return appointmentRepository.findAll();
//    }
//
//    @GetMapping(path = "/appointments",
//            params = {"begin_date", "end_date", "user_id"})
//    public @ResponseBody
//    Iterable<AppointmentRequestModel> getAllAppointments(
//            @RequestParam(value = "begin_date") Integer begin_date,
//            @RequestParam(value = "end_date") Integer end_date,
//            @RequestParam(required = false, value = "user_id") Integer user_id) {
//        if (begin_date != null && end_date != null && user_id != null)
//            return appointmentRepository.findByAppointmentDateBetweenAndUserIdAndAppointmentTypeNot(begin_date, end_date, user_id, "denied");
//        return appointmentRepository.findAll();
//    }
//
//    /**
//     * Method for retrieving a specific appointment
//     *
//     * @param id
//     * @return AppointmentRequestModel
//     */
//    @GetMapping(path = "/appointments", params = {"id"})
//    public @ResponseBody
//    AppointmentRequestModel getAllAppointments(@RequestParam(value = "id") int id) {
//        return appointmentRepository.findAppointmentById(id);
//    }
//
//
//    /**
//     * Method for creating a new appointment (an annual check-up, a routine check or a vaccination)
//     *
//     * @param appointmentRequest
//     * @return appointment
//     */
//    @PostMapping(path = "/appointments")
//    public @ResponseBody
//    String addNewAppointment(@RequestBody AppointmentRequestModel appointmentRequest) {
//        AppointmentFactory appointmentFactory = new AppointmentFactory();
//        Appointment factoryAppointment = appointmentFactory.createAppointment(appointmentRequest.getAppointmentType());
//        AppointmentRequestModel appointment = new AppointmentRequestModel(factoryAppointment);
//
//        appointment.setUserId(appointmentRequest.getUserId());
//        appointment.setPatientId(appointmentRequest.getPatientId());
//        appointment.setAppointmentDate(appointmentRequest.getAppointmentDate());
//        appointment.setAppointment_status("NEW");
//        appointmentRepository.save(appointment);
//
//        return gson.toJson(appointment);
//    }
//
//
//    /**
//     * Method for updating an appointment's information
//     *
//     * @param appointmentRequestModel
//     * @param id
//     * @return String (json)
//     */
//    @PatchMapping(path = "/appointments")
//    public @ResponseBody
//    String updateAppointment(@RequestBody AppointmentRequestModel appointmentRequestModel, @RequestParam int id) {
//        AppointmentFactory appointmentFactory = new AppointmentFactory();
//        Appointment factoryAppointment = appointmentFactory.createAppointment(appointmentRequestModel.getAppointmentType());
//        AppointmentRequestModel appointment = new AppointmentRequestModel(factoryAppointment);
//        AppointmentRequestModel found = appointmentRepository.findAppointmentById(id);
//
//        appointment.setId(found.getId());
//        appointment.setUserId(appointmentRequestModel.getUserId() != null ? appointmentRequestModel.getUserId() : found.getUserId());
//        appointment.setPatientId(appointmentRequestModel.getPatientId() != null ? appointmentRequestModel.getPatientId() : found.getPatientId());
//        appointment.setAppointmentDate(appointmentRequestModel.getAppointmentDate() != null ? appointmentRequestModel.getAppointmentDate() : found.getAppointmentDate());
//        appointment.setAppointment_status(appointmentRequestModel.getAppointment_status() != null ? appointmentRequestModel.getAppointment_status() : found.getAppointment_status());
//
//        PatientModel foundPatient = patientRepository.findPatientById(appointment.getPatientId());
//
//        if (!appointment.getAppointment_status().equals(found.getAppointment_status())) {
//            subject.updateAppointmentStatus(foundPatient.getEmail(), foundPatient.getFullName(), appointment.getAppointment_status(), appointment.getAppointmentDate());
//        }
//        appointmentRepository.save(appointment);
//
//        return gson.toJson(appointment);
//    }
//
//
//    /**
//     * Method for deleting an existing appointment
//     *
//     * @param appointmentRequestModel
//     * @return ResponseEntity
//     */
//    @DeleteMapping(path = "/appointments")
//    public ResponseEntity deleteAppointment(@RequestBody AppointmentRequestModel appointmentRequestModel) {
//        AppointmentRequestModel found = appointmentRepository.findAppointmentById(appointmentRequestModel.getId());
//        appointmentRepository.delete(found);
//
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
//    }
//
//}
