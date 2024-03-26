package com.japan.Japan_BE.Service;

import com.japan.Japan_BE.Entity.JPAnswerEntity;
import com.japan.Japan_BE.Entity.JPLevelEntity;
import com.japan.Japan_BE.Entity.JPQuestionEntity;
import com.japan.Japan_BE.Entity.JPRoomEntity;
import com.japan.Japan_BE.Repository.JPAnswerRepository;
import com.japan.Japan_BE.Repository.JPLevelReponsitory;
import com.japan.Japan_BE.Repository.JPQuestionRepository;
import com.japan.Japan_BE.Repository.JPRoomReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.events.Event;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class JPRoomService {
    @Autowired
    private JPRoomReponsitory jpRoomReponsitory;

    @Autowired
    private JPLevelReponsitory jpLevelReponsitory;

    public List<JPRoomEntity> GetAllJPRoomByLevelId(int levelId){
        return jpRoomReponsitory.findByJpLevel_Id(levelId);
    }

//    private static final String UPLOAD_DIR = "src/main/resources/static/ImageJP/";

    public JPRoomEntity InsertRoom(JPRoomEntity jpRoom, int levelId)  {

        JPRoomEntity jpRoomEntity = new JPRoomEntity();
        jpRoomEntity.setImg_path(jpRoom.getImg_path());
        jpRoomEntity.setName(jpRoom.getName());

        JPLevelEntity jpLevelEntity = jpLevelReponsitory.findById(levelId);
        jpRoomEntity.setJpLevel(jpLevelEntity);

        return jpRoomReponsitory.save(jpRoomEntity);


    }

    public JPRoomEntity UpdateRoom(JPRoomEntity jpRoom,int roomId)  {

        JPRoomEntity jpRoomEntity = jpRoomReponsitory.findById(roomId);

        if(jpRoomEntity == null){
            return null;
        }

        jpRoomEntity.setImg_path(jpRoom.getImg_path());
        jpRoomEntity.setName(jpRoom.getName());


        return jpRoomReponsitory.save(jpRoomEntity);


    }

    @Autowired
    private JPQuestionRepository jpQuestionRepository;

    @Autowired
    private JPAnswerRepository jpAnswerRepository;
    public String DeleteRoom(int roomId)  {

        // Kiểm tra xem phòng có tồn tại hay không
        JPRoomEntity jpRoomEntity = jpRoomReponsitory.findById(roomId);
        if(jpRoomEntity == null){
            return "Không có phòng";
        }

        // Tìm các câu hỏi liên quan đến phòng
        List<JPQuestionEntity> jpQuestionEntityList =jpQuestionRepository.findQuestionByjpRoom_Id(roomId);
        // Xóa tất cả các câu trả lời liên quan đến các câu hỏi trong phòng
        for (JPQuestionEntity jpQuestionEntity : jpQuestionEntityList) {
            List<JPAnswerEntity> jpAnswerEntityList = jpAnswerRepository.findByJpQuestion_Id(jpQuestionEntity.getId());
            jpAnswerRepository.deleteAll(jpAnswerEntityList);
        }

        // Xóa tất cả các câu hỏi trong phòng
        jpQuestionRepository.deleteAll(jpQuestionEntityList);

        // Xóa phòng
        jpRoomReponsitory.deleteById(roomId);
        return "Xóa thành công";


    }




}
