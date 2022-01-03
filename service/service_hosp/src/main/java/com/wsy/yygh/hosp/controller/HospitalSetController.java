package com.wsy.yygh.hosp.controller;

import com.atguigu.yygh.model.hosp.HospitalSet;
import com.wsy.yygh.hosp.service.HospitalSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {
    @Autowired
    private HospitalSetService hospitalSetService;

    //1.查询医院设置表的信息
    @GetMapping("findAll")
    public Result findAllHospitalSet(){
        //调用service方法
        List<HospitalSet> list = hospitalSetService.list();
        return list;
    }

    //2.删除医院设置
    @DeleteMapping("{id}")
    public boolean removeHospSet(@PathVariable Long id){
        boolean flag = hospitalSetService.removeById(id);
        return flag;
    }
}
