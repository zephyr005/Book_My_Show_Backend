package AccioJob.Book_My_Show_Backend.Controllers;

import AccioJob.Book_My_Show_Backend.DTOs.MovieRequestDto;
import AccioJob.Book_My_Show_Backend.Models.Movie;
import AccioJob.Book_My_Show_Backend.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //Get movie by id
    @GetMapping("/find_by_id")
    public Movie findById(@RequestParam Integer movieId){
        return movieService.findById(movieId);
    }

    //Get movie by Name
    @GetMapping("/find_by_name")
    public Movie findMovieByName(@RequestParam String movieName){
        return movieService.findMovieByName(movieName);
    }

    //Get all listed movies
    @GetMapping("/find_movie_list")
    public List<String> findAllMovies(){
        return movieService.findAllMovies();
    }
}
