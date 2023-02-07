package AccioJob.Book_My_Show_Backend.Repository;

import AccioJob.Book_My_Show_Backend.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
