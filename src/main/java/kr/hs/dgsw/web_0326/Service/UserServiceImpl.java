package kr.hs.dgsw.web_0326.Service;

import kr.hs.dgsw.web_0326.Domain.User;
import kr.hs.dgsw.web_0326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User addUser(User user) {
        Optional<User> found = this.userRepository.findByEmail(user.getEmail());
        if (found.isPresent()) return null;
        return this.userRepository.save(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        Optional<User> found = this.userRepository.findById(id);
        if (found.isPresent()) {
            this.userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User modifyUser(User user) {
        Optional<User> found = this.userRepository.findById(user.getId());
        if (found.isPresent()) {
            found.get().setUsername(user.getUsername());
            found.get().setEmail(user.getEmail());
            found.get().setStoredPath(user.getStoredPath());
            found.get().setOriginalName(user.getOriginalName());
            userRepository.save(found.get());
        }
        return found.get();
    }

    @Override
    public List<User> getUser() {
        return this.userRepository.findAll();
    }

    @Override
    public User getOneUser(Long id) {
        Optional<User> found = this.userRepository.findById(id);
        if (found.isPresent()) return found.get();
        else return null;
    }
}
