package AccioJob.Book_My_Show_Backend.Controllers;

import AccioJob.Book_My_Show_Backend.DTOs.MovieRequestDto;
import AccioJob.Book_My_Show_Backend.Models.MovieEntity;
import AccioJob.Book_My_Show_Backend.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/find_by_id")
    public MovieEntity findMovieByName(@RequestParam String movieName){
        return movieService.findMovieByName(movieName);
    }

}
