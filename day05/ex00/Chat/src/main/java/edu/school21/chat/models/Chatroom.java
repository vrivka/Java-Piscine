package edu.school21.chat.models;

import java.util.List;
import java.util.stream.Collectors;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Message> getMassages() {
        return massages;
    }

    public void setMassages(List<Message> massages) {
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
                ", name='" + name + '\'' +
                ", owner=" + owner.getId() +
                ", massages=" + massages.stream()
                .map(Message::getId)
                .collect(Collectors.toList()) +
                '}';
    }
}
