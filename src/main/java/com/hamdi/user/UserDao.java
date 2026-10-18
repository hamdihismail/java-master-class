package com.hamdi.user;

import com.hamdi.utility.ArrayHelper;

import java.util.UUID;

public class UserDao {
    public static User[] users;
    public static int lastIndex;
    private static ArrayHelper arrayHelper = new ArrayHelper();
    static {
        users = new User[1];
        lastIndex = 0;
        users = arrayHelper.add(users,new User(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),"James"),lastIndex++);
        users = arrayHelper.add(users,new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"),"Anna"),lastIndex++);
    }
    public boolean addUser(User user){
        if(!userExists(user)){//if user doesn't exist, add to array and increment User.lastIndex
            users = arrayHelper.add(users,user,lastIndex++);
            return true;
        }
        return false;
    }

    public User findUserById(UUID id){
        for (User user : getUsers()) {
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    public User[] getUsers(){
        User[] result = new User[lastIndex];
        for (int i = 0; i < lastIndex; i++) {
            result[i] = users[i];
        }
        return result;
    }
    private boolean userExists(User user){
        for (User x : getUsers()) {
            if(x.equals(user)){
                return true;
            }
        }
        return false;
    }
}
