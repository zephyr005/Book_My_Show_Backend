package AccioJob.Book_My_Show_Backend.Service;

import AccioJob.Book_My_Show_Backend.DTOs.TheaterRequestDto;
import AccioJob.Book_My_Show_Backend.Enums.SeatType;
import AccioJob.Book_My_Show_Backend.Models.TheaterEntity;
import AccioJob.Book_My_Show_Backend.Models.TheaterSeatEntity;
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
        TheaterEntity theater = TheaterEntity.builder().city(theaterRequestDto.getCity()).name(theaterRequestDto.getName()).address(theaterRequestDto.getAddress()).build();

        List<TheaterSeatEntity> theaterSeats = createTheaterSeats();
        theater.setTheaterSeatEntityList(theaterSeats);

        //For each theater Seat : we need to set the theaterEntity
        for(TheaterSeatEntity theaterSeat : theaterSeats){
            theaterSeat.setTheater(theater);
        }

        theaterRepository.save(theater);

        return "Theater added successfully";
    }

    private List<TheaterSeatEntity> createTheaterSeats() {
        List<TheaterSeatEntity> seats = new ArrayList<>();
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
            TheaterSeatEntity theaterSeat = new TheaterSeatEntity(seatNo, SeatType.CLASSIC, 100);
            seats.add(theaterSeat);
        }

        for(int i=0; i<5; i++){
            char ch = (char)('A'+i);
            String seatNo = "2"+ch;
            TheaterSeatEntity theaterSeat = new TheaterSeatEntity(seatNo, SeatType.PLATINUM, 200);
            seats.add(theaterSeat);
        }

        theaterSeatRepository.saveAll(seats);

        return seats;
    }

    public TheaterEntity findTheater(Integer theaterId) {
        TheaterEntity theater = theaterRepository.findById(theaterId).get();
        return theater;
    }

    public List<TheaterEntity> findAllTheaters() {
        List<TheaterEntity> theaterEntityList = theaterRepository.findAll();
        return theaterEntityList;
    }

    public List<TheaterSeatEntity> findAllTheaterSeats() {
        List<TheaterSeatEntity> theaterSeatEntityList = theaterSeatRepository.findAll();
        return theaterSeatEntityList;
    }
}
