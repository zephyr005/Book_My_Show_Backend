package AccioJob.Book_My_Show_Backend.Models;

import javax.persistence.*;

import AccioJob.Book_My_Show_Backend.Enums.SeatType;
import AccioJob.Book_My_Show_Backend.Enums.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="tickets")
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String alloted_seats;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    private int amount;

    private Date booked_at;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Show show;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ShowSeat> bookedSeats;
}
