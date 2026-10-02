package com.hamdi.user;

import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    public static User[] users;
    public static int lastIndex;
    static {
        users = new User[10];
        lastIndex = 0;
        users[lastIndex++] = new User(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),"James");
        users[lastIndex++] = new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"),"Anna");
    }

    public User(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name) {
        id = UUID.randomUUID();
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
