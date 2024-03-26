package com.japan.Japan_BE.Service;


import com.japan.Japan_BE.Entity.JPLevelEntity;
import com.japan.Japan_BE.Repository.JPLevelReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JPLevelService {
    @Autowired
    private JPLevelReponsitory jpLevelReponsitory;

    public List<JPLevelEntity> GetAllJPLevel(){
        return jpLevelReponsitory.findAll();
    }

    public JPLevelEntity InsertLevel(JPLevelEntity jpLevelEntity){
        JPLevelEntity jpLevel = new JPLevelEntity();
        jpLevel.setImg_path(jpLevelEntity.getImg_path());
        jpLevel.setName(jpLevelEntity.getName());

        return jpLevelReponsitory.save(jpLevel);

    }

    public String DeleteLevelByLevelId(int levelId){

        JPLevelEntity jpLevel = jpLevelReponsitory.findById(levelId);

        if(jpLevel == null){
            return "level ko tồn tại";
        }

        try{
            jpLevelReponsitory.deleteById(levelId);
            return "xoa level thang cong";
        }catch(Exception e){
            return "xoa level that bai" + e.getMessage();
        }

    }

    public JPLevelEntity UpdateLevelByLevelId(int levelId,JPLevelEntity jpLevelEntity){
        JPLevelEntity jpLevel = jpLevelReponsitory.findById(levelId);

        jpLevel.setName(jpLevelEntity.getName());
        jpLevel.setImg_path(jpLevelEntity.getImg_path());
        
        return jpLevelReponsitory.save(jpLevel);
    }

}
