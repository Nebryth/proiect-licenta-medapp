package ps_project.customer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api")
public class CustomerController {

    private Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().serializeNulls().create();
    @Autowired
    private CustomerRepository customerRepository;

    //Method for retrieving all the existing customers (doctor's offices)


    //Method for retrieving a single customer entity using its id


    //Method for creating a new customer entity


    //Method for updating a customer entity


    //Method for deleting a customer entity


}