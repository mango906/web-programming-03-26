package kr.hs.dgsw.web_0326.Controller;

import kr.hs.dgsw.web_0326.Domain.Comment;
import kr.hs.dgsw.web_0326.Domain.User;
import kr.hs.dgsw.web_0326.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web_0326.Repository.UserRepository;
import kr.hs.dgsw.web_0326.Service.CommentService;
import kr.hs.dgsw.web_0326.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
public class AttachmentController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/write")
    public Comment write(@RequestBody Comment comment){
        return commentService.addCoomment(comment);
    }

    @PostMapping("/attachment/{id}")
    public AttachmentProtocol upload(@RequestPart MultipartFile srcFile, @PathVariable Long id) {
        String destFilename
                = "D:/Web/web_0326/"
                + LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE.ofPattern("yyyy/MM/dd"))
                + UUID.randomUUID().toString() + "_"
                + srcFile.getOriginalFilename();

        try {
            File destFile = new File(destFilename);
            destFile.getParentFile().mkdirs();
            srcFile.transferTo(destFile);

            Comment comment = new Comment();
            comment.setOriginalName(srcFile.getOriginalFilename());
            comment.setStoredPath(destFilename);

//            userService.modifyUser(user);

            return new AttachmentProtocol(destFilename, srcFile.getOriginalFilename());

        } catch (Exception e) {
            return null;
        }

    }

    @GetMapping("/attachment/{id}")
    public void download(@PathVariable Long id, HttpServletRequest req, HttpServletResponse resp){
        try {
            AttachmentProtocol attachmentProtocol = commentService.getImage(id);

            String filePath = attachmentProtocol.getStoredPath();
            String fileName = attachmentProtocol.getOriginalName();
            File file = new File(filePath);
            if (file.exists() == false) return;

            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) mimeType = "application/octet-stream";

            resp.setContentType(mimeType);
            resp.setHeader("Content-Disposition", "inline; filename=" + fileName + "\"");
            resp.setContentLength((int) file.length());

            InputStream is = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(is, resp.getOutputStream());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
