package AccioJob.Book_My_Show_Backend.Service;

import AccioJob.Book_My_Show_Backend.DTOs.MovieRequestDto;
import AccioJob.Book_My_Show_Backend.Models.Movie;
import AccioJob.Book_My_Show_Backend.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieRequestDto movieRequestDto){
        //Convert Dto to Entity layer for saving it to database.
        Movie movie = Movie.builder().movieName(movieRequestDto.getName()).duration(movieRequestDto.getDuration()).releaseDate(movieRequestDto.getReleaseDate()).build();
        movieRepository.save(movie);
        return "Movie added successfully";
    }

    public Movie findMovieByName(String movieName) {
        Movie movie = movieRepository.findByMovieName(movieName);
        return movie;
    }

    public Movie findById(Integer movieId) {
        return movieRepository.findById(movieId).get();
    }

    public List<String> findAllMovies() {
        List<String> moviesList = new ArrayList<>();
        for(Movie movie : movieRepository.findAll()){
            moviesList.add(movie.getMovieName());
        }

        return moviesList;
    }
}
