package AccioJob.Book_My_Show_Backend.DTOs;

import lombok.Data;
import java.util.Date;

@Data
public class MovieRequestDto {
    private String name;
    private int duration;
    private Date releaseDate;
}
