package com.japan.Japan_BE.Service;


import com.japan.Japan_BE.Entity.JPAnswerEntity;
import com.japan.Japan_BE.Entity.JPQuestionEntity;
import com.japan.Japan_BE.Entity.JPRoomEntity;
import com.japan.Japan_BE.Repository.JPAnswerRepository;
import com.japan.Japan_BE.Repository.JPQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JPAnswerService {
    @Autowired
    private JPAnswerRepository jpAnswerRepository;

    @Autowired
    private JPQuestionRepository jpQuestionRepository;

    public List<JPAnswerEntity> GetAllJPAnswer(){
        return jpAnswerRepository.findAll();
    }

    public List<JPAnswerEntity> GetAllJPAnswerByQuestionId(int questionId){
        return jpAnswerRepository.findByJpQuestion_Id(questionId);
    }



    public boolean DeleteAllAnswerByQuestId(int questId){
        try{
            List<JPAnswerEntity> jpAnswerEntities = jpAnswerRepository.findByJpQuestion_Id(questId);
            jpAnswerRepository.deleteAll(jpAnswerEntities);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public List<JPAnswerEntity> insertAnswers(List<JPAnswerEntity> answers, int questionId) {
        JPQuestionEntity jpQuestion = jpQuestionRepository.findQuestionById(questionId);
        List<JPAnswerEntity> insertedAnswers = new ArrayList<>();

        for (JPAnswerEntity answer : answers) {
            JPAnswerEntity jpAnswerEntity = new JPAnswerEntity();
            jpAnswerEntity.setContent(answer.getContent());
            jpAnswerEntity.setCorrect(answer.getCorrect());
            jpAnswerEntity.setJpQuestion(jpQuestion);

            insertedAnswers.add(jpAnswerRepository.save(jpAnswerEntity));

        }
        return insertedAnswers;
    }

}
