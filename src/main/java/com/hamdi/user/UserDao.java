package com.hamdi.user;

import java.util.UUID;

public class UserDao {
    public boolean addUser(User user){
        if(!userExists(user)){//if user doesn't exist, add to array and increment User.lastIndex
            User.users[User.lastIndex] = user;
            ++User.lastIndex;
            return true;
        }
        return false;
    }

    public User getUser(UUID id){
        for (User user : User.users) {
            if(user.getId().equals(id)){
                return user;
            }
        }
//        throw new Exception("User not found");
        return null;
    }

    public User[] getAllUsers(){
        return User.users;
    }
    private boolean userExists(User user){
        for (User x : User.users) {
            if(x.equals(user)){
                return true;
            }
        }
        return false;
    }
}
