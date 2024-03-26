package com.japan.Japan_BE.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity(name = "jp_room")
public class JPRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="name")
    private String name;

    @Column(name ="img_path")
    private String img_path;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "jplevel_id")
    private JPLevelEntity jpLevel;


    public JPLevelEntity getJpLevel() {
        return jpLevel;
    }

    public void setJpLevel(JPLevelEntity jpLevel) {
        this.jpLevel = jpLevel;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
