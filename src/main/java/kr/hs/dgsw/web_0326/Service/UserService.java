package kr.hs.dgsw.web_0326.Service;

import kr.hs.dgsw.web_0326.Domain.User;
import kr.hs.dgsw.web_0326.Protocol.AttachmentProtocol;

import java.util.List;

public interface UserService {
    User addUser(User user);

    boolean deleteUser(Long id);

    User modifyUser(User user);

    List<User> getUser();

    User getOneUser(Long id);

    AttachmentProtocol getImage(Long id);

    User login(String email, String password);
}
