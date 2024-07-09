package edu.school21.chat.models;

import java.util.List;
import java.util.stream.Collectors;

public class User {
    private Integer id;
    private String login;
    private String password;
    List<Chatroom> createdChats;
    List<Chatroom> activeChats;

    public User(Integer id, String login, String password, List<Chatroom> createdChats, List<Chatroom> activeChats) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdChats = createdChats;
        this.activeChats = activeChats;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chatroom> getCreatedChats() {
        return createdChats;
    }

    public void setCreatedChats(List<Chatroom> createdChats) {
        this.createdChats = createdChats;
    }

    public List<Chatroom> getActiveChats() {
        return activeChats;
    }

    public void setActiveChats(List<Chatroom> activeChats) {
        this.activeChats = activeChats;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return id != null && id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdChatsIds=" + createdChats.stream()
                .map(Chatroom::getId)
                .collect(Collectors.toList()) +
                ", activeChatsIds=" + activeChats.stream()
                .map(Chatroom::getId)
                .collect(Collectors.toList()) +
                '}';
    }
}
