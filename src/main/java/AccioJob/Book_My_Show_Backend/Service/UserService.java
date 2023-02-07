package AccioJob.Book_My_Show_Backend.Service;

import AccioJob.Book_My_Show_Backend.DTOs.UserRequestDto;
import AccioJob.Book_My_Show_Backend.Models.User;
import AccioJob.Book_My_Show_Backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String createUser(UserRequestDto userRequestDto){
        //Converted the userRequestDto to userEntity
        User user = User.builder().name(userRequestDto.getName()).mobile(userRequestDto.getMobile()).build();
        try{
            userRepository.save(user);
        }
        catch (Exception e){
            return "User could not be added";
        }
        return "User added successfully";
    }

    public User findByUserName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }
}
