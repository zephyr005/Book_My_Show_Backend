package AccioJob.Book_My_Show_Backend.Service;

import AccioJob.Book_My_Show_Backend.DTOs.BookTicketRequestDto;
import AccioJob.Book_My_Show_Backend.Enums.Status;
import AccioJob.Book_My_Show_Backend.Models.Show;
import AccioJob.Book_My_Show_Backend.Models.ShowSeat;
import AccioJob.Book_My_Show_Backend.Models.Ticket;
import AccioJob.Book_My_Show_Backend.Models.User;
import AccioJob.Book_My_Show_Backend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;

    public String bookTicket(BookTicketRequestDto bookTicketRequestDto)throws Exception {

        //Get the request
        List<String> requestedSeats = bookTicketRequestDto.getRequestSeats();

        Show showEntity = showRepository.findById(bookTicketRequestDto.getShowId()).get();

        User userEntity = userRepository.findById(bookTicketRequestDto.getUserId()).get();

        //Get the showSeats from showEntity
        List<ShowSeat> showSeats = showEntity.getListOfSeats();

        //Validate if I can allocate these seats to the requested by user.

        List<ShowSeat> unBookedSeats = new ArrayList<>();

        for (ShowSeat showSeat : showSeats) {

            String seatNo = showSeat.getSeatNo();

            if (showSeat.isBooked() == false && requestedSeats.contains(seatNo)) {
                unBookedSeats.add(showSeat);
            }
        }


        //FAILURE
        if (unBookedSeats.size() != requestedSeats.size()) {
            //Some the seats the useRequested are not available
            throw new Exception("Requested seats are not available");
        }


        //SUCCESS
        //This means the bookedSeats actually contains the booked Seats.
        Ticket ticketEntity = new Ticket();

        double totalAmout = 0;
        double multiplier = showEntity.getMultiplier();


        String allotedSeats = "";

        int rate = 0;
        //Calculating amount,setting bookedStatus, setting

        for (ShowSeat unBookedSeat : unBookedSeats) {

            unBookedSeat.setBooked(true);
            unBookedSeat.setBookedAt(new Date());
            unBookedSeat.setTicket(ticketEntity);
            unBookedSeat.setShow(showEntity);

            String seatNo = unBookedSeat.getSeatNo();

            allotedSeats = allotedSeats + seatNo + ",";

            if (seatNo.charAt(0) == '1')
                rate = 100;
            else
                rate = 200;

            totalAmout = totalAmout + multiplier * rate;
        }

        List<ShowSeat> bookedSeats = unBookedSeats;

        ticketEntity.setBooked_at(new Date());
        ticketEntity.setAmount((int) totalAmout);
        ticketEntity.setShow(showEntity);
        ticketEntity.setUser(userEntity);
        ticketEntity.setBookedSeats(bookedSeats);
        ticketEntity.setAlloted_seats(allotedSeats);
        ticketEntity.setStatus(Status.ACTIVE);

        //Bidirectional mapping
        userEntity.getListOfTickets().add(ticketEntity);

        userRepository.save(userEntity);
        showRepository.save(showEntity);

        return "Ticket has been created successfully";
    }

    public String cancelTicket(Integer ticketId) throws Exception{
        Ticket ticket = ticketRepository.findById(ticketId).get();
        List<ShowSeat> bookedSeats = ticket.getBookedSeats();

        for(ShowSeat showSeat : bookedSeats){
            showSeat.setBooked(false);
        }

        showSeatRepository.saveAll(bookedSeats);

        Show show = ticket.getShow();

        List<Ticket> ticketList = show.getListOfTickets();
        ticketList.remove(ticketId);
        show.setListOfTickets(ticketList);

        ticket.setShow(show);
        ticket.setBookedSeats(bookedSeats);

        showRepository.save(show);
        ticket.setStatus(Status.CANCELLED);

        ticketRepository.save(ticket);

        return "Ticket has been cancelled successfully";
    }
}
