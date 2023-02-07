package AccioJob.Book_My_Show_Backend.Service;

import AccioJob.Book_My_Show_Backend.DTOs.ShowRequestDto;
import AccioJob.Book_My_Show_Backend.Models.*;
import AccioJob.Book_My_Show_Backend.Repository.MovieRepository;
import AccioJob.Book_My_Show_Backend.Repository.ShowRepository;
import AccioJob.Book_My_Show_Backend.Repository.ShowSeatRepository;
import AccioJob.Book_My_Show_Backend.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;
    @Autowired
    private ShowRepository showRepository;

    public String addShow(ShowRequestDto showRequestDto){
        //Show Entity
        Show show = Show.builder().showTime(showRequestDto.getShowTime()).showDate(showRequestDto.getShowDate()).multiplier(showRequestDto.getMultiplier()).build();

        //You need to get the movieRepository
        Movie movie = movieRepository.findByMovieName(showRequestDto.getMovieName());

        //You need to get the theaterRepository
        Theater theater = theaterRepository.findById(showRequestDto.getTheaterId()).get();

        //Need to save into child(showEntity)
        show.setTheater(theater);
        show.setMovie(movie);

        /* Because we are doing a bidirectional mapping
        Optional:
            List<ShowEntity> currentListOfShow = movieEntity.getListOfShows();
            currentListOfShow.add(showEntity);
            movieEntity.setListOfShows(currentListOfShow);
        * */
        movie.getListOfShows().add(show);
        theater.getListOfShows().add(show);

        List<ShowSeat> seatEntityList = createShowSeats(theater.getTheaterSeatList());

        show.setListOfSeats(seatEntityList);

        //For each showSeat : we need to mark that to which show it belongs (foreign key will be filled)
        for(ShowSeat showSeat : seatEntityList){
            showSeat.setShow(show);
        }

        movieRepository.save(movie);
        theaterRepository.save(theater);
        //showRepository.save(showEntity); This doesn't need to call bcz parent save function is being called.

        return "Show added successfully";
    }

    public List<ShowSeat> createShowSeats(List<TheaterSeat> theaterSeatEntityList){
        List<ShowSeat> seats = new ArrayList<>();
        for(TheaterSeat theaterSeat : theaterSeatEntityList){
            ShowSeat showSeat = ShowSeat.builder().seatNo(theaterSeat.getSeatNo()).seatType(theaterSeat.getSeatType()).build();
            seats.add(showSeat);
        }

        showSeatRepository.saveAll(seats);

        return seats;
    }

    public void deleteShow(Integer showId) {
        showRepository.deleteById(showId);
    }
}
