package net.lycee.web.enquete.api.controller.user;

import net.lycee.web.enquete.interceptor.LyceeAnonymous;
import net.lycee.web.enquete.interceptor.LyceeAuthorized;
import net.lycee.web.enquete.api.service.user.UserService;
import net.lycee.web.enquete.interceptor.RequestUser;
import net.lycee.web.enquete.api.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@LyceeAuthorized
public class UserController {

    private final RequestUser user;

    private final UserService userService;


    @Autowired
    public UserController(
            RequestUser user,
            UserService userService) {
        this.user = user;
        this.userService = userService;
    }


    /**
     * ユーザ情報取得API
     * @return ユーザ情報
     */
    @GetMapping("")
    public ResponseEntity<UserGetResponse>
    handleRead() {
        UserInfo userInfo = userService.readUserInfo(user.getUserId());

        return ResponseEntity.ok(new UserGetResponse(userInfo.userId()));
    }

    /**
     * ユーザID発行API
     * @return ユーザID
     */
    @PostMapping("")
    @LyceeAnonymous
    public ResponseEntity<UserRegisterResponse>
    handleRegister() {
        UserId userId = userService.registerUser();
        return ResponseEntity.ok(new UserRegisterResponse(userId));
    }



}
