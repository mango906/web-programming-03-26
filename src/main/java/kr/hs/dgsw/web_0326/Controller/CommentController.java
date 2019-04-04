package kr.hs.dgsw.web_0326.Controller;

import kr.hs.dgsw.web_0326.Domain.Comment;
import kr.hs.dgsw.web_0326.Domain.User;
import kr.hs.dgsw.web_0326.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.web_0326.Service.CommentService;
import kr.hs.dgsw.web_0326.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/list")
    public List<CommentUsernameProtocol> listComments() {
        return this.commentService.listAllComments();
    }

    @DeleteMapping("/deleteComment/{id}")
    public boolean deleteComment(@PathVariable Long id) {
        return this.commentService.deleteComment(id);
    }

    @PostMapping("/addComment")
    public CommentUsernameProtocol addComment(@RequestBody Comment comment) {
        return this.commentService.addCoomment(comment);
    }

    @PutMapping("/updateComment/{id}")
    public Comment updateComment(@RequestBody Comment comment, @PathVariable Long id) {
        return this.commentService.updateComment(comment, id);
    }

    @GetMapping("/getComment/{id}")
    public List<CommentUsernameProtocol> getComment(@PathVariable Long id) {
        return this.commentService.listComments(id);
    }

}
