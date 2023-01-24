package AccioJob.Book_My_Show_Backend.Controllers;

import AccioJob.Book_My_Show_Backend.DTOs.TheaterRequestDto;
import AccioJob.Book_My_Show_Backend.Models.TheaterEntity;
import AccioJob.Book_My_Show_Backend.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/add")
    public String addTheater(@RequestBody TheaterRequestDto theaterRequestDto){
        return theaterService.createTheater(theaterRequestDto);
    }

    //Get theater by theaterId
    @GetMapping("find_by_id")
    public TheaterEntity findTheater(@RequestParam Integer theaterId){
        return theaterService.findTheater(theaterId);
    }

    //Get all theaters
    @GetMapping("/find_all_theater")
    public List<TheaterEntity> findAllTheaters(){
        return theaterService.findAllTheaters();
    }
}
