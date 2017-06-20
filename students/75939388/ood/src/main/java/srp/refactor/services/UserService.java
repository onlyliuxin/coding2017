package srp.refactor.services;

import srp.refactor.domain.User;
import srp.refactor.util.DBUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tee on 2017/6/16.
 */
public class UserService {

    public User setUser(String userName, String email){
        return new User(userName, email);
    }

    public List<User> getUserList(String sql){
        List<HashMap> userMapList = DBUtil.query(sql);
        List<User> userList = new ArrayList<>();
        for(HashMap map : userMapList){
            userList.add(setUser(
                    (String)map.get("NAME"),
                    (String)map.get("EMAIL"))
            );
        }

        return userList;
    }
}
