package com.school.service;

import com.github.pagehelper.PageInfo;
import com.school.entity.Model;
import com.school.entity.ModelVo;

import java.util.List;

public interface ModelService {

    Integer insert(Model model);

    //查询所有
    List<ModelVo> selectAll();

    //根据type查询
    List<ModelVo> selectByType(Integer type);

    ModelVo getModelVo(Integer modelId);

    Integer updateModel(Model model);

    Integer deleteModel(Integer modelId);

    List<ModelVo> selectByTitle(String title);

    //获取所有记录
    Long getModelCount();

    Model getModelById(Integer id);

    PageInfo<ModelVo> selectByTypeAndTitle(Integer currentPage, Integer pageSize,Integer type, String title);
}
