package AccioJob.Book_My_Show_Backend.DTOs;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowRequestDto {
    private LocalDate showDate;
    private LocalTime showTime;
    private String movieName;
    private int theaterId;
    private double multiplier;
}
