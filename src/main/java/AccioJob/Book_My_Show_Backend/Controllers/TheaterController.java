package AccioJob.Book_My_Show_Backend.Controllers;

import AccioJob.Book_My_Show_Backend.DTOs.TheaterRequestDto;
import AccioJob.Book_My_Show_Backend.Models.Theater;
import AccioJob.Book_My_Show_Backend.Models.TheaterSeat;
import AccioJob.Book_My_Show_Backend.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    //Add theater
    @PostMapping("/add")
    public String addTheater(@RequestBody TheaterRequestDto theaterRequestDto){
        return theaterService.createTheater(theaterRequestDto);
    }

    //Get theater by theaterId
    @GetMapping("find_by_id")
    public Theater findTheater(@RequestParam Integer theaterId){
        return theaterService.findTheater(theaterId);
    }

    //Get all theaters
    @GetMapping("/find_all_theater")
    public List<Theater> findAllTheaters(){
        return theaterService.findAllTheaters();
    }

    //Get all theater seats
    @GetMapping("/find_all_theater_seats")
    public List<TheaterSeat> findAllSeats(@RequestParam Integer theaterId){
        return theaterService.findAllTheaterSeats(theaterId);
    }
}
