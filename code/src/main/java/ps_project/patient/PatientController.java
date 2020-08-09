//package ps_project.patient;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@CrossOrigin(origins = "*")
//@RequestMapping(path = "/api")
//public class PatientController {
//
//    private Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().serializeNulls().create();
//    @Autowired
//    private PatientRepository patientRepository;
//
//    //Method for returning all the existing patients
//    @GetMapping(path = "/patients")
//    public @ResponseBody
//    Iterable<PatientModel> getAllPatients() {
//        return patientRepository.findAll();
//    }
//
//    //Method for retrieving a specific patient's data
//    @GetMapping(path = "/patients", params = {"id"})
//    public @ResponseBody
//    PatientModel getAppointmentById(@RequestParam(value = "id") int id) {
//        return patientRepository.findPatientById(id);
//    }
//
//    //Method for creating a new patient record
//    @RequestMapping(value = "/patient", method = RequestMethod.POST)
//    @ResponseBody
//    //public String addPatient(@)
//
//    //Method for updating a patient's information
////    @PatchMapping(path = "/patients")
////    public @ResponseBody
////    String updatePatient(@RequestBody PatientRM patientRequestModel) {
////        PatientModel patient = new PatientModel();
////        PatientModel found = patientRepository.findPatientById(patientRequestModel.getId());
////        patient.setId(found.getId());
////        patient.setFirst_name(patientRequestModel.getFirst_name() != null ? patientRequestModel.getFirst_name() : found.getFirst_name());
////        patient.setLast_name(patientRequestModel.getLast_name() != null ? patientRequestModel.getLast_name() : found.getLast_name());
////        patient.setAddress(patientRequestModel.getAddress() != null ? patientRequestModel.getAddress() : found.getAddress());
////        patient.setTelephone(patientRequestModel.getTelephone() != null ? patientRequestModel.getTelephone() : found.getTelephone());
////        patient.setEmail(patientRequestModel.getEmail() != null ? patientRequestModel.getEmail() : found.getEmail());
////        patientRepository.save(patient);
////
////        return gson.toJson(patient);
////    }
//
//
//
//    @DeleteMapping(path = "/patients")
//    public ResponseEntity deletePatient(@RequestBody PatientModel patientRequestModel) {
//        PatientModel found = patientRepository.findPatientById(patientRequestModel.getId());
//        patientRepository.delete(found);
//
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
//    }
//}
