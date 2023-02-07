package AccioJob.Book_My_Show_Backend.Models;

import AccioJob.Book_My_Show_Backend.Enums.SeatType;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="theater_seats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private int rate;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Theater theater;

    public TheaterSeat(String seatNo, SeatType seatType, int rate) {
        this.seatNo = seatNo;
        this.seatType = seatType;
        this.rate = rate;
    }
}
