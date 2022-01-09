package com.wsy.yygh.cmn.service.impl;

import com.atguigu.yygh.model.cmn.Dict;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsy.yygh.cmn.mapper.DictMapper;
import com.wsy.yygh.cmn.service.DictService;
import org.springframework.stereotype.Service;

@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
}
