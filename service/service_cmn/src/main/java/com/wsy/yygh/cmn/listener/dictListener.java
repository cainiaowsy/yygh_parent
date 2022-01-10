package com.wsy.yygh.cmn.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.yygh.model.cmn.Dict;
import com.atguigu.yygh.vo.cmn.DictEeVo;
import com.wsy.yygh.cmn.mapper.DictMapper;
import org.springframework.beans.BeanUtils;

import java.util.Map;

public class dictListener extends AnalysisEventListener<DictEeVo> {
    private DictMapper dictMapper;
    public dictListener(DictMapper dictMapper1){
        this.dictMapper=dictMapper1;
    }

    //从第二行开始一行一行读
    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictEeVo,dict);
        dictMapper.insert(dict);
    }
    //读取之后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
