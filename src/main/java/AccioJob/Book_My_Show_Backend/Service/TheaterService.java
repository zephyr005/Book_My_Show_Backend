package AccioJob.Book_My_Show_Backend.Service;

import AccioJob.Book_My_Show_Backend.DTOs.TheaterRequestDto;
import AccioJob.Book_My_Show_Backend.Enums.SeatType;
import AccioJob.Book_My_Show_Backend.Models.Theater;
import AccioJob.Book_My_Show_Backend.Models.TheaterSeat;
import AccioJob.Book_My_Show_Backend.Repository.TheaterRepository;
import AccioJob.Book_My_Show_Backend.Repository.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    @Autowired
    TheaterRepository theaterRepository;

    public String createTheater(TheaterRequestDto theaterRequestDto){
        Theater theater = Theater.builder().city(theaterRequestDto.getCity()).name(theaterRequestDto.getName()).address(theaterRequestDto.getAddress()).build();

        List<TheaterSeat> theaterSeats = createTheaterSeats();
        theater.setTheaterSeatList(theaterSeats);

        //For each theater Seat : we need to set the theaterEntity
        for(TheaterSeat theaterSeat : theaterSeats){
            theaterSeat.setTheater(theater);
        }

        theaterRepository.save(theater);

        return "Theater added successfully";
    }

    private List<TheaterSeat> createTheaterSeats() {
        List<TheaterSeat> seats = new ArrayList<>();
        /*
        TheaterSeatEntity theaterSeat1 = new TheaterSeatEntity("1A", SeatType.CLASSIC, 100);
        TheaterSeatEntity theaterSeat2 = new TheaterSeatEntity("1B", SeatType.CLASSIC, 100);
        TheaterSeatEntity theaterSeat3 = new TheaterSeatEntity("1C", SeatType.CLASSIC, 100);
        TheaterSeatEntity theaterSeat4 = new TheaterSeatEntity("1D", SeatType.CLASSIC, 100);
        TheaterSeatEntity theaterSeat5 = new TheaterSeatEntity("1E", SeatType.CLASSIC, 100);

        seats.add(theaterSeat1);
        seats.add(theaterSeat2);
        seats.add(theaterSeat3);
        seats.add(theaterSeat4);
        seats.add(theaterSeat5);
        * */

        //Optimize by adding loop
        for(int i=0; i<5; i++){
            char ch = (char)('A'+i);
            String seatNo = "1"+ch;
            TheaterSeat theaterSeat = new TheaterSeat(seatNo, SeatType.CLASSIC, 100);
            seats.add(theaterSeat);
        }

        for(int i=0; i<5; i++){
            char ch = (char)('A'+i);
            String seatNo = "2"+ch;
            TheaterSeat theaterSeat = new TheaterSeat(seatNo, SeatType.PLATINUM, 200);
            seats.add(theaterSeat);
        }

        theaterSeatRepository.saveAll(seats);

        return seats;
    }

    public Theater findTheater(Integer theaterId) {
        Theater theater = theaterRepository.findById(theaterId).get();
        return theater;
    }

    public List<Theater> findAllTheaters() {
        List<Theater> theaterEntityList = theaterRepository.findAll();
        return theaterEntityList;
    }

    public List<TheaterSeat> findAllTheaterSeats(Integer theaterId) {
        Theater theater = theaterRepository.findById(theaterId).get();
        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();
        return theaterSeatList;
    }
}
