package AccioJob.Book_My_Show_Backend.DTOs;

import lombok.Data;
import java.util.List;

@Data
public class BookTicketRequestDto {
    private List<String> requestSeats;
    private int showId;
    private int userId;
}
