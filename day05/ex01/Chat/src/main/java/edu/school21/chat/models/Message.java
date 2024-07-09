package edu.school21.chat.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Message {
    private Integer id;
    private User author;
    private Chatroom room;
    private String text;
    private LocalDateTime date;

    public Message(Integer id, User author, Chatroom room, String text, LocalDateTime date) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
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
        return "Massage: {\n" +
                "id=" + id +
                ",\nauthor=" + author +
                ",\nroomId=" + room +
                ",\ntext=\"" + text + '"' +
                ",\ndate=" + date +
                '}';
    }
}
