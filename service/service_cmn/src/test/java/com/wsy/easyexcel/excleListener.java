package com.wsy.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class excleListener extends AnalysisEventListener<userData> {
    //读第一行
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头："+headMap);
    }
    //从第二行开始一行一行读
    @Override
    public void invoke(userData userData, AnalysisContext analysisContext) {
        System.out.println(userData);
    }

    //读取之后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
