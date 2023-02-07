package AccioJob.Book_My_Show_Backend.Models;

import AccioJob.Book_My_Show_Backend.Enums.SeatType;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name="show_seats")
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNo;

    private boolean booked;

    private Date bookedAt;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Show show;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Ticket ticket;
}
