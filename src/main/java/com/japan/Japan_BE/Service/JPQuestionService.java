package com.japan.Japan_BE.Service;


import com.japan.Japan_BE.Entity.JPQuestionEntity;
import com.japan.Japan_BE.Entity.JPRoomEntity;
import com.japan.Japan_BE.Repository.JPQuestionRepository;
import com.japan.Japan_BE.Repository.JPRoomReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JPQuestionService {
    @Autowired
    private JPQuestionRepository jpQuestionRepository;
    @Autowired
    private JPRoomReponsitory jpRoomReponsitory;

    public List<JPQuestionEntity> GetAllJPQuestion(){
        return jpQuestionRepository.findAll();
    }


    public List<JPQuestionEntity> GetAllJPQuestionByRoomId(int roomId){
        return jpQuestionRepository.findByJpRoom_Id(roomId);
    }

    public JPQuestionEntity insertQuestion(JPQuestionEntity question, int roomId) {
        JPRoomEntity jpRoomEntity = jpRoomReponsitory.findById(roomId);

        JPQuestionEntity questionEntity = new JPQuestionEntity();
        questionEntity.setContent(question.getContent());
        questionEntity.setSuggest(question.getSuggest());

        questionEntity.setJpRoom(jpRoomEntity);

        try {
            return jpQuestionRepository.save(questionEntity);
        } catch (Exception e) {
            System.out.println("Loi Insert Question trong Question Service: " + e.getLocalizedMessage());
            return null; // Return null if there's an exception
        }
    }




    public String deleteQuestById(int questId){

            jpQuestionRepository.deleteById(questId);
            return "xoa thanh cong";

    }



}
