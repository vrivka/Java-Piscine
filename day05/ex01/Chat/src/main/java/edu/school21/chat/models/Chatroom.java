package edu.school21.chat.models;

import java.util.List;

public class Chatroom {
    private Integer id;
    private String name;
    private User owner;
    private List<Message> massages;

    public Chatroom(Integer id, String name, User owner, List<Message> massages) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.massages = massages;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Chatroom chatroom = (Chatroom) obj;
        return id != null && id.equals(chatroom.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "id=" + id +
                ", name=\"" + name + '"' +
                ", owner=" + owner +
                ", massages=" + massages +
                '}';
    }
}
