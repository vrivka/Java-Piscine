package edu.school21.chat.models;

import java.util.Date;

public class Message {
    private Integer id;
    private User author;
    private Chatroom room;
    private String text;
    private Date date;

    public Message(Integer id, User author, Chatroom room, String text, Date date) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chatroom getRoom() {
        return room;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Message massage = (Message) obj;
        return id != null && id.equals(massage.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Massage{" +
                "id=" + id +
                ", authorId=" + author.getId() +
                ", roomId=" + room.getId() +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
