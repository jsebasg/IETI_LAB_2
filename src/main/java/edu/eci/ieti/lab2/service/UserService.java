package edu.eci.ieti.lab2.service;

import edu.eci.ieti.lab2.data.User;
import java.util.List;

public interface UserService {
    User create( User user );

    User findById( String id );

    List<User> getAll();

    void deleteById( String id ) throws Exception;

    User update(User user, String userId );
}
