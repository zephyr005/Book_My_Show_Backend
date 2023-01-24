package AccioJob.Book_My_Show_Backend.Service;

import AccioJob.Book_My_Show_Backend.DTOs.ShowRequestDto;
import AccioJob.Book_My_Show_Backend.Models.*;
import AccioJob.Book_My_Show_Backend.Repository.MovieRepository;
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

    public String addShow(ShowRequestDto showRequestDto){
        //Show Entity
        ShowEntity show = ShowEntity.builder().showTime(showRequestDto.getShowTime()).showDate(showRequestDto.getShowDate()).multiplier(showRequestDto.getMultiplier()).build();

        //You need to get the movieRepository
        MovieEntity movie = movieRepository.findByMovieName(showRequestDto.getMovieName());

        //You need to get the theaterRepository
        TheaterEntity theater = theaterRepository.findById(showRequestDto.getTheaterId()).get();

        //Need to save into child(showEntity)
        show.setTheater(theater);
        show.setMovie(movie);

        /* Because we are doing a bidirectional mapping
        Optional:
            List<ShowEntity> currentListOfShow = movieEntity.getListOfShows();
            currentListOfShow.add(showEntity);
            movieEntity.setListOfShows(currentListOfShow);
        * */
        movieEntity.getListOfShows().add(showEntity);
        theaterEntity.getListOfShows().add(showEntity);

        List<ShowSeatEntity> seatEntityList = createShowSeats(theaterEntity.getTheaterSeatEntityList());

        showEntity.setListOfSeats(seatEntityList);

        //For each showSeat : we need to mark that to which show it belongs (foreign key will be filled)
        for(ShowSeatEntity showSeat : seatEntityList){
            showSeat.setShow(showEntity);
        }

        movieRepository.save(movieEntity);
        theaterRepository.save(theaterEntity);
        //showRepository.save(showEntity); This doesn't need to call bcz parent save function is being called.

        return "Show added successfully";
    }

    public List<ShowSeatEntity> createShowSeats(List<TheaterSeatEntity> theaterSeatEntityList){
        List<ShowSeatEntity> seats = new ArrayList<>();
        for(TheaterSeatEntity theaterSeat : theaterSeatEntityList){
            ShowSeatEntity showSeat = ShowSeatEntity.builder().seatNo(theaterSeat.getSeatNo()).seatType(theaterSeat.getSeatType()).build();
            seats.add(showSeat);
        }

        showSeatRepository.saveAll(seats);

        return seats;
    }
}
