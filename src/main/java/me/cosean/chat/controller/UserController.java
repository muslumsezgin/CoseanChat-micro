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

    @PostMapping("/user/request/{ip2}")
    @ResponseBody
    public ResponseEntity addRequest(HttpServletRequest request, @PathVariable("ip2") String ip2) {
        String ip = request.getRemoteAddr();
        if (OnlineSet.getInstance().contains(ip2))
            return userService.request(ip,ip2);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/user/confirm/{ip2}")
    @ResponseBody
    public ResponseEntity addConfirm(HttpServletRequest request, @PathVariable("ip2") String ip2) {
        String ip = request.getRemoteAddr();
        if (UserMap.getInstance().get(ip).getPendingSet().contains(ip2))
            return userService.confirm(ip,ip2);
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
