package com.japan.Japan_BE.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity(name = "jp_question")
public class JPQuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "suggest")
    private String suggest;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "jproom_id")
    private JPRoomEntity jpRoom;

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public JPRoomEntity getJpRoom() {
        return jpRoom;
    }

    public void setJpRoom(JPRoomEntity jpRoom) {
        this.jpRoom = jpRoom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
