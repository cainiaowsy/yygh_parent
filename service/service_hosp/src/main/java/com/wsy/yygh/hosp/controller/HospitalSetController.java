package com.wsy.yygh.hosp.controller;

import com.atguigu.yygh.model.hosp.HospitalSet;
import com.atguigu.yygh.vo.hosp.HospitalSetQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsy.yygh.common.result.Result;
import com.wsy.yygh.hosp.service.HospitalSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
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
        return Result.ok(list);
    }

    //2.删除医院设置
    @DeleteMapping("{id}")
    public Result removeHospSet(@PathVariable Long id){
        boolean flag = hospitalSetService.removeById(id);
        if (flag){
            return Result.ok();
        }
        return Result.fail();
    }
    //3.条件查询
    @PostMapping ("findPage/{current}/{limit}")
    public Result findPageHospSet(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo){

        Page<HospitalSet> page = new Page<>(current,limit);
        QueryWrapper<HospitalSet> Wrapper = new QueryWrapper<>();

        String hosname = hospitalSetQueryVo.getHosname();
        String hoscode = hospitalSetQueryVo.getHoscode();

        if (!StringUtils.isEmpty(hosname)){
            Wrapper.like("hosname",hospitalSetQueryVo.getHosname());
        }
        if (!StringUtils.isEmpty(hoscode)){
            Wrapper.eq("hoscode",hospitalSetQueryVo.getHoscode());
        }

        Page<HospitalSet> page1 = hospitalSetService.page(page, Wrapper);
        return Result.ok(page1);
    }
}
