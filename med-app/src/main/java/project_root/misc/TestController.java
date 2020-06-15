package project_root.misc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/add")
public class TestController {

    @GetMapping(value = "/hello")
    public String add(){
        return "Hello";
    }
}
