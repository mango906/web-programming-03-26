package kr.hs.dgsw.web_0326.Service;

import kr.hs.dgsw.web_0326.Domain.Comment;
import kr.hs.dgsw.web_0326.Domain.User;
import kr.hs.dgsw.web_0326.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.web_0326.Repository.CommentRepository;
import kr.hs.dgsw.web_0326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init() {
        User u = this.userRepository.save(new User("aaa", "aaa@dgsw"));

    }

    @Override
    public List<CommentUsernameProtocol> listAllComments() {
        List<Comment> commentList = this.commentRepository.findAll();
        List<CommentUsernameProtocol> cupList = new ArrayList<>();
        commentList.forEach(comment -> {
            Optional<User> found = this.userRepository.findById(comment.getUserId());
            String username = (found.isPresent()) ? found.get().getUsername() : null;
            cupList.add(new CommentUsernameProtocol(comment, username));
        });
        return cupList;
    }

    @Override
    public boolean deleteComment(Long id) {
        Optional<Comment> found = this.commentRepository.findById(id);
        if (found.isPresent()) {
            this.commentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public CommentUsernameProtocol addCoomment(Comment comment) {
            Comment added =  commentRepository.save(comment);
            String username = this.userRepository.findById(added.getUserId())
                    .map(found -> found.getUsername())
                    .orElse(null);
        return new CommentUsernameProtocol(added, username);
    }

    @Override
    public Comment updateComment(Comment comment, Long id) {
        Optional<Comment> found = this.commentRepository.findById(id);
        if (found.isPresent()) {
            found.get().setContent(comment.getContent());
            return this.commentRepository.save(found.get());
        }
        return null;
    }

    @Override
    public List<CommentUsernameProtocol> listComments(Long id) {
        Optional<User> found = this.userRepository.findById(id);
        if(found.isPresent()){
            List<CommentUsernameProtocol> comments = new ArrayList<>();
            commentRepository.findAll().forEach(comment -> {
                if(comment.getUserId() == id){
                    CommentUsernameProtocol c = new CommentUsernameProtocol(comment, found.get().getUsername());
                    comments.add(c);
                }
            });
            return comments;
        }
        return null;
    }
}
