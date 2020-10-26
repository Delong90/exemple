package orderingProducts.service;

import orderingProducts.dto.UserDto;
import orderingProducts.exceptions.ItemNotFoundException;
import orderingProducts.model.User;
import orderingProducts.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;


import java.sql.SQLException;

public class UserService {

    private UserRepository userRepository = new UserRepository();

    public UserDto getByUser(String in) throws SQLException, ItemNotFoundException {

        User user = userRepository.getByUser(in);

        return UserDto.builder()
                .id(user.getId())
                .user(user.getUser())
                .password(user.getPassword())
                .build();
    }

    public UserDto newUser(String inName,String inPassword) throws SQLException, ItemNotFoundException {

        User user = userRepository.newUser(inName,inPassword);

        return UserDto.builder()
                .id(user.getId())
                .user(user.getUser())
                .password(user.getPassword())
                .build();
    }


    public boolean checkPassword(String in, UserDto user) {
        return DigestUtils.sha256Hex(in).equals(user.getPassword());
    }


}




