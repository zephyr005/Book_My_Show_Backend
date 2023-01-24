package AccioJob.Book_My_Show_Backend.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    public String index(){
        return "Greeting from Spring Boot!";
    }

    @GetMapping("/error")
    public String index1(){
        return "Greeting from Spring Boot!";
    }
}
