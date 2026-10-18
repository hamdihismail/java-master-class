package com.hamdi.user;

import java.util.UUID;

public class UserService {
   UserDao userDao = new UserDao();

   public User lookUp(UUID id) {
       try {
           return userDao.findUserById(id);
       } catch (Exception e){
           System.out.println("User not found!\n");
           return null;
       }
   }

   public User[] getAll(){
       return userDao.getUsers();
   }

}
