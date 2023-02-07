package AccioJob.Book_My_Show_Backend.Repository;

import AccioJob.Book_My_Show_Backend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByName(String name);
}
