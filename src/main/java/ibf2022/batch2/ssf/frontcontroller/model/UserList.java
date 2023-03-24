package ibf2022.batch2.ssf.frontcontroller.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class UserList implements Serializable{
    private List<User> userList = new LinkedList<>();

    //to count number of attempts for user login
    private int count = 0;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void addUserToList(User u){
        this.userList.add(u);
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public void addCount() { this.count++; }

    
    
}
