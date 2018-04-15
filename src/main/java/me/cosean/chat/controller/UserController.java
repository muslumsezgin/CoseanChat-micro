package me.cosean.chat.controller;

import me.cosean.chat.db.OnlineSet;
import me.cosean.chat.db.UserMap;
import me.cosean.chat.model.User;
import me.cosean.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("v1/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    @ResponseBody
    public ResponseEntity add(HttpServletRequest request, @RequestBody User aUser) {
        String ip = request.getRemoteAddr();
        if (Objects.nonNull(aUser) &&  Objects.nonNull(aUser.getNickName()))
            return userService.add(ip, aUser);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/user/request")
    @ResponseBody
    public ResponseEntity addRequest(HttpServletRequest request, @RequestBody Map <String ,String> ipMap) {
        String ip = request.getRemoteAddr();
        User otherUser =UserMap.getInstance().get(ipMap.get("ip"));
        if (OnlineSet.getInstance().contains(ipMap.get("ip"))&& !otherUser.getRequestSet().contains(ip))
            return userService.request(ip,ipMap.get("ip"));
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/user/confirm")
    @ResponseBody
    public ResponseEntity addConfirm(HttpServletRequest request, @RequestBody Map <String ,String> ipMap) {
        String ip = request.getRemoteAddr();
        if (UserMap.getInstance().get(ip).getPendingSet().contains(ipMap.get("ip")))
            return userService.confirm(ip,ipMap.get("ip"));
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/user")
    @ResponseBody
    public ResponseEntity delete(HttpServletRequest  request) {
        return userService.delete(request.getRemoteAddr());
    }

    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity get() {
        return userService.get();
    }

    @GetMapping("/user/{ip}")
    @ResponseBody
    public ResponseEntity get(@PathVariable("ip") String ip) {
        return userService.get(ip);
    }

    @GetMapping("/user/online")
    @ResponseBody
    public ResponseEntity getOnline() {
        return userService.getOnline();
    }

}
