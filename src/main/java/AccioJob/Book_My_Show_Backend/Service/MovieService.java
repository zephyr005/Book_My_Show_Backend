package AccioJob.Book_My_Show_Backend.Service;

import AccioJob.Book_My_Show_Backend.DTOs.MovieRequestDto;
import AccioJob.Book_My_Show_Backend.Models.MovieEntity;
import AccioJob.Book_My_Show_Backend.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieRequestDto movieRequestDto){
        //Convert Dto to Entity layer for saving it to database.
        MovieEntity movie = MovieEntity.builder().movieName(movieRequestDto.getName()).duration(movieRequestDto.getDuration()).releaseDate(movieRequestDto.getReleaseDate()).build();
        movieRepository.save(movie);
        return "Movie added successfully";
    }

    public MovieEntity findMovieByName(String movieName) {
        MovieEntity movie = movieRepository.findByMovieName(movieName);
        return movie;
    }
}
