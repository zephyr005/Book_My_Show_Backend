package AccioJob.Book_My_Show_Backend.Controllers;

import AccioJob.Book_My_Show_Backend.DTOs.BookTicketRequestDto;
import AccioJob.Book_My_Show_Backend.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    //Book ticket
    @PostMapping("/book")
    public String bookTicket(@RequestBody BookTicketRequestDto bookTicketRequestDto){
        try{
            return ticketService.bookTicket(bookTicketRequestDto);
        }
        catch (Exception e){
            return "Requested Seats not available";
        }
    }

    //Cancel ticket
    @DeleteMapping("/cancel")
    public String cancelTicket(@RequestParam Integer ticketId){
        try {
            return ticketService.cancelTicket(ticketId);
        }
        catch (Exception e){
            return "Ticket can not be cancelled";
        }
    }
}
