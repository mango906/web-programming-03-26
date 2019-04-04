package kr.hs.dgsw.web_0326.Service;

import kr.hs.dgsw.web_0326.Domain.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    boolean deleteUser(Long id);

    User modifyUser(User user);

    List<User> getUser();

    User getOneUser(Long id);
}
