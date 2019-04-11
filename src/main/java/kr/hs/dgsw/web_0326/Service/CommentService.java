package kr.hs.dgsw.web_0326.Service;

import kr.hs.dgsw.web_0326.Domain.Comment;
import kr.hs.dgsw.web_0326.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web_0326.Protocol.CommentUsernameProtocol;

import java.util.List;

public interface CommentService {
    List<CommentUsernameProtocol> listAllComments();

    boolean deleteComment(Long id);

    CommentUsernameProtocol addCoomment(Comment comment);

    Comment updateComment(Comment comment, Long id);

    List<CommentUsernameProtocol> listComments(Long id);

    AttachmentProtocol getImage(Long id);
}
