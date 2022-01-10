package com.wsy.yygh.cmn.controller;

import com.atguigu.yygh.model.cmn.Dict;
import com.wsy.yygh.cmn.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wsy.yygh.common.result.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/admin/cmn/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    //导入数据字典
    @PostMapping("inportData")
    public Result inportData(MultipartFile file) {
        dictService.inportData(file);
        return Result.ok();
    }

    //导出数据字典
    @GetMapping("exportData")
    public Result exexportData(HttpServletResponse httpServletResponse) {
        dictService.exportDictData(httpServletResponse);
        return Result.ok();
    }
//缓存

    //根据id查子数据列表
    @GetMapping("findChild/{id}")
    public Result findChild(@PathVariable Long id) {
        List<Dict> list = dictService.findChild(id);
        return Result.ok(list);
    }
}
