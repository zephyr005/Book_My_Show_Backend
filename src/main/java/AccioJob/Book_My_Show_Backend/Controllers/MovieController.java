package AccioJob.Book_My_Show_Backend.Controllers;

import AccioJob.Book_My_Show_Backend.DTOs.MovieRequestDto;
import AccioJob.Book_My_Show_Backend.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    //Add movie
    @PostMapping("/add")
    public String addMovie(@RequestBody MovieRequestDto movieRequestDto){
        return movieService.addMovie(movieRequestDto);
    }

    //Get movie by Name

}
