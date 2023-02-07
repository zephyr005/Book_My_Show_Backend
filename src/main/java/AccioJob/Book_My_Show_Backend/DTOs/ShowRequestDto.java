package AccioJob.Book_My_Show_Backend.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
public class ShowRequestDto {
    private LocalDate showDate;
    private LocalTime showTime;
    private String movieName;
    private int theaterId;
    private double multiplier;
}
