package ps_project.diagnose;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api")
public class DiagnoseController {
    private Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().serializeNulls().create();
    @Autowired
    private DiagnoseRepository diagnoseRepository;

    /**
     * Method for returning all the existing diagnoses
     *
     * @return
     */
    @GetMapping(path = "/diagnoses")
    public @ResponseBody
    Iterable<DiagnoseModel> getAllDiagnoses() {
        return diagnoseRepository.findAll();
    }


    /**
     * Method for returning a specific diagnose, based on the provided id
     *
     * @param id
     * @return DiagnoseModel
     */
    @GetMapping(path = "/diagnoses", params = {"id"})
    public @ResponseBody
    DiagnoseModel getDiagnoseById(@RequestParam(value = "id") int id) {
        return diagnoseRepository.findDiagnoseById(id);
    }


    /***
     * Method for creating a new diagnose
     * @param diagnoseRequestModel
     * @return String (json)
     */
    @PostMapping(path = "/diagnoses")
    public @ResponseBody
    String createDiagnose(@RequestBody DiagnoseModel diagnoseRequestModel) {
        DiagnoseModel diagnose = new DiagnoseModel();
        diagnose.setPatient_id(diagnoseRequestModel.getPatient_id());
        diagnose.setUser_id(diagnoseRequestModel.getUser_id());
        diagnose.setCreation_date(diagnoseRequestModel.getCreation_date());
        diagnose.setResult(diagnoseRequestModel.getResult());
        diagnoseRepository.save(diagnose);

        return gson.toJson(diagnose);
    }


    /**
     * Method for updating a diagnose
     *
     * @param diagnoseRequestModel
     * @param id
     * @return String (json)
     */
    @PatchMapping(path = "/diagnoses")
    public @ResponseBody
    String updateDiagnose(@RequestBody DiagnoseModel diagnoseRequestModel, @RequestParam(value = "id") int id) {
        DiagnoseModel diagnose = new DiagnoseModel();
        DiagnoseModel found = diagnoseRepository.findDiagnoseById(id);
        diagnose.setId(found.getId());
        diagnose.setPatient_id(diagnoseRequestModel.getPatient_id() != 0 ? diagnoseRequestModel.getPatient_id() : found.getPatient_id());
        diagnose.setUser_id(diagnoseRequestModel.getUser_id() != 0 ? diagnoseRequestModel.getUser_id() : found.getUser_id());
        diagnose.setCreation_date(diagnoseRequestModel.getCreation_date() != null ? diagnoseRequestModel.getCreation_date() : found.getCreation_date());
        diagnose.setResult(diagnoseRequestModel.getResult() != null ? diagnoseRequestModel.getResult() : found.getResult());
        diagnoseRepository.save(diagnose);

        return gson.toJson(diagnose);
    }

    @DeleteMapping(path = "/diagnoses")
    public ResponseEntity deleteUser(@RequestBody DiagnoseModel diagnoseRequestModel) {
        DiagnoseModel found = diagnoseRepository.findDiagnoseById(diagnoseRequestModel.getId());
        diagnoseRepository.delete(found);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
