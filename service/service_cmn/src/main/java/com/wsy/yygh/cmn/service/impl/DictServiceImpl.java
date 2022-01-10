package com.wsy.yygh.cmn.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.yygh.model.cmn.Dict;
import com.atguigu.yygh.vo.cmn.DictEeVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsy.yygh.cmn.listener.dictListener;
import com.wsy.yygh.cmn.mapper.DictMapper;
import com.wsy.yygh.cmn.service.DictService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    public List<Dict> findChild(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        List<Dict> dictList = baseMapper.selectList(wrapper);
        for (Dict dict : dictList) {
            Long dictId = dict.getId();
            boolean child = this.isChild(dictId);
            dict.setHasChildren(child);
        }
        return dictList;
    }

    //导出数据字典
    @Override
    public void exportDictData(HttpServletResponse httpServletResponse) {
      //设置下载信息
        httpServletResponse.setContentType("application/vnd.ms-excel");
        httpServletResponse.setCharacterEncoding("utf-8");
        String fileName ="dict";
        httpServletResponse.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
       //查询数据库
        List<Dict> dictList = baseMapper.selectList(null);
        //dictList转成DictVo
        List<DictEeVo> dictVoList = new ArrayList<>(dictList.size());
        for(Dict dict:dictList){
            DictEeVo dictEeVo = new DictEeVo();
            BeanUtils.copyProperties(dict,dictEeVo);
            dictVoList.add(dictEeVo);
        }
        //调用方法写
        try {
            EasyExcel.write(httpServletResponse.getOutputStream(), DictEeVo.class).sheet("dict")
                    .doWrite(dictVoList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //导入数据字典
    @Override
    public void inportData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),DictEeVo.class,new dictListener(baseMapper))
                    .sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //判断id下面是否有子节点
    private boolean isChild(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(wrapper);
        return count > 0;
    }
}
