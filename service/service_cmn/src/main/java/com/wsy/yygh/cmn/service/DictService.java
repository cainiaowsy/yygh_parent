package com.wsy.yygh.cmn.service;

import com.atguigu.yygh.model.cmn.Dict;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DictService extends IService<Dict> {
    List<Dict> findChild(Long id);

    void exportDictData(HttpServletResponse httpServletResponse);

    void inportData(MultipartFile file);
}
