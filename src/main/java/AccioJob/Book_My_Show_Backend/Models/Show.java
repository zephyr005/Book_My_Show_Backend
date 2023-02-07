package AccioJob.Book_My_Show_Backend.Models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Table(name="shows")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate showDate;


    private LocalTime showTime;

    private double multiplier;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Movie movie;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Theater theater;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ShowSeat> listOfSeats;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Ticket> listOfTickets;
}
