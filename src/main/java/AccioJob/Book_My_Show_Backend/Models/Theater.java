package AccioJob.Book_My_Show_Backend.Models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="theater")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String city;

    private String address;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Show> listOfShows;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<TheaterSeat> theaterSeatList;
}
