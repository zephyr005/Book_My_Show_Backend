package AccioJob.Book_My_Show_Backend.Repository;

import AccioJob.Book_My_Show_Backend.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
    public Movie findByMovieName(String movieName);

}
