package com.japan.Japan_BE.Entity;

import jakarta.persistence.*;

@Entity(name = "jp_answer")
public class JPAnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "correct")
    private Boolean correct;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private JPQuestionEntity jpQuestion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public JPQuestionEntity getJpQuestion() {
        return jpQuestion;
    }

    public void setJpQuestion(JPQuestionEntity jpQuestion) {
        this.jpQuestion = jpQuestion;
    }
}
