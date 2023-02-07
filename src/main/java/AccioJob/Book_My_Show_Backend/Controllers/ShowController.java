package AccioJob.Book_My_Show_Backend.Controllers;

import AccioJob.Book_My_Show_Backend.DTOs.ShowRequestDto;
import AccioJob.Book_My_Show_Backend.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    //Add show
    @PostMapping("/add")
    public String addShow(@RequestBody ShowRequestDto showRequestDto){
        return showService.addShow(showRequestDto);
    }

    //Remove show
    @DeleteMapping("/delete")
    public String deleteShow(@RequestParam Integer showId){
        showService.deleteShow(showId);
        return "Show has been removed successfully";
    }
}
