package me.cosean.chat.service;

import me.cosean.chat.db.OnlineSet;
import me.cosean.chat.db.UserMap;
import me.cosean.chat.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public ResponseEntity add(String ip, User aUser) {
        User user = UserMap.getInstance().get(ip);
        if(user != null){
            aUser.setConfirmSet(user.getConfirmSet());
            aUser.setRequestSet(user.getRequestSet());
            aUser.setPendingSet(user.getPendingSet());
        }
        UserMap.getInstance().put(ip, aUser);
        OnlineSet.getInstance().add(ip);
        return new ResponseEntity<>(ip,HttpStatus.OK);
    }

    public ResponseEntity delete(String ip) {
        UserMap.getInstance().remove(ip);
        OnlineSet.getInstance().remove(ip);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity get() {
        return new ResponseEntity<>(UserMap.getInstance(), HttpStatus.OK);
    }

    public ResponseEntity get(String ip) {
        return new ResponseEntity<>(UserMap.getInstance().get(ip), HttpStatus.OK);
    }

    public ResponseEntity getOnline() {
        return new ResponseEntity<>(OnlineSet.getInstance(), HttpStatus.OK);
    }

    public ResponseEntity request(String ip, String ip2){
        boolean add = UserMap.getInstance().get(ip).addRequestSet(ip2);
        boolean addOwn = UserMap.getInstance().get(ip2).addPendingSet(ip);
        if(add && addOwn && OnlineSet.getInstance().contains(ip2)) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity confirm(String ip, String ip2){
        boolean addOwn = UserMap.getInstance().get(ip).addConfirmSet(ip2);
        boolean add = UserMap.getInstance().get(ip2).addConfirmSet(ip);
        UserMap.getInstance().get(ip).getPendingSet().remove(ip2);
        UserMap.getInstance().get(ip2).getRequestSet().remove(ip);
        if(add&& addOwn && OnlineSet.getInstance().contains(ip2)) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
