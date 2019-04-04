package kr.hs.dgsw.web_0326.Controller;

import kr.hs.dgsw.web_0326.Domain.User;
import kr.hs.dgsw.web_0326.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web_0326.Repository.UserRepository;
import kr.hs.dgsw.web_0326.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
public class AttachmentController {

    @Autowired
    private UserService userService;

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

            User user = userService.getOneUser(id);
            user.setOriginalName(srcFile.getOriginalFilename());
            user.setStoredPath(destFilename);

            userService.modifyUser(user);

            return new AttachmentProtocol(destFilename, srcFile.getOriginalFilename());

        } catch (Exception e) {
            return null;
        }

    }
}
