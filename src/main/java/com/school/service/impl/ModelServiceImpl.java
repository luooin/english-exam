package com.school.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.dao.ModelMapper;
import com.school.dao.TypeMapper;
import com.school.entity.Model;
import com.school.entity.ModelVo;
import com.school.entity.Type;
import com.school.service.ModelService;
import com.school.utils.DifficultyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public Integer insert(Model model) {
        return modelMapper.insert(model);
    }

    @Override
    public List<ModelVo> selectAll() {
        List<Model> list = modelMapper.selectAll();
        return modelToModelVo(list);
    }

    @Override
    public List<ModelVo> selectByType(Integer type) {
        List<Model> list = modelMapper.selectByType(type);
        return modelToModelVo(list);
    }

    @Override
    public ModelVo getModelVo(Integer modelId) {
        Model model = modelMapper.selectByPrimaryKey(modelId);
        return modelToModelVo(model);
    }

    @Override
    public Integer updateModel(Model model) {
        return modelMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public Integer deleteModel(Integer modelId) {
        return modelMapper.deleteByPrimaryKey(modelId);
    }

    @Override
    public List<ModelVo> selectByTitle(String title) {
        List<Model> list = modelMapper.selectByTitle(title);
        return modelToModelVo(list);
    }

    @Override
    public Long getModelCount() {
        return modelMapper.getModelCount();
    }

    @Override
    public Model getModelById(Integer id) {
        return modelMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<ModelVo> selectByTypeAndTitle(Integer currentPage, Integer pageSize,Integer type, String title) {
        PageHelper.startPage(currentPage, pageSize);
        if ((type==null||type.equals(0)||type.equals(""))&&(title == null|| title.equals(""))){
            List<Model> list = modelMapper.selectAll();
            PageInfo<Model> p = new PageInfo<>(list);
            return changePageInfo(p);
        }
        if (type != null&&(title == null||title.equals(""))){

            List<Model> list = modelMapper.selectByType(type);
            PageInfo<Model> p = new PageInfo<>(list);
            return changePageInfo(p);
        }
        if ((type==null||type.equals(0)||type.equals(""))&&(title != null)){
            List<Model> list = modelMapper.selectByTitle(title);
            PageInfo<Model> p = new PageInfo<>(list);
            return changePageInfo(p);
        }else {
            List<Model> list = modelMapper.selectByTypeAndTitle(type, title);
            PageInfo<Model> p = new PageInfo<>(list);
            return changePageInfo(p);
        }
    }

    /**
     * //转化为包装类ModelVo
     * @param model
     * @return
     */
    public ModelVo modelToModelVo(Model model){
        ModelVo modelVo = new ModelVo();
        Type type = typeMapper.selectByPrimaryKey(model.getType());
        String difficulty = DifficultyUtil.getDifficulty(model.getDifficulty());
        modelVo.setModel(model);
        modelVo.setType(type.getType());
        modelVo.setDifficulty(difficulty);
        return modelVo;
    }
    /**
     * 转化为包装类ModelVo的list
     * @param list
     * @return
     */
    public List<ModelVo> modelToModelVo(List<Model> list){
        List<ModelVo> modelVoList = new ArrayList<>();
        for (Model model:list){
            modelVoList.add(modelToModelVo(model));
        }
        return modelVoList;
    }
    /**
     * 转化为包装类ModelVo的PageInfo
     * @param p
     * @return
     */
    public PageInfo<ModelVo> changePageInfo(PageInfo<Model> p){
        PageInfo<ModelVo> pageInfo = new PageInfo<>();
        //当前页
//        private int pageNum;
        pageInfo.setPageNum(p.getPageNum());
        //每页的数量
//        private int pageSize;
        pageInfo.setPageSize(p.getPageSize());
        //当前页的数量
//        private int size;
        pageInfo.setSize(p.getSize());
//        private int startRow;
        pageInfo.setStartRow(p.getStartRow());
        //当前页面最后一个元素在数据库中的行号
//        private int endRow;
        pageInfo.setEndRow(p.getEndRow());
        //总页数
//        private int pages;
        pageInfo.setPages(p.getPages());
        //前一页
//        private int prePage;
        pageInfo.setPrePage(p.getPrePage());
        //下一页
//        private int nextPage;
        pageInfo.setNextPage(p.getNextPage());
        //是否为第一页
//        private boolean isFirstPage = false;
        pageInfo.setIsFirstPage(p.isIsFirstPage());
        //是否为最后一页
//        private boolean isLastPage = false;
        pageInfo.setIsLastPage(p.isIsLastPage());
        //是否有前一页
//        private boolean hasPreviousPage = false;
        pageInfo.setHasPreviousPage(p.isHasPreviousPage());
        //是否有下一页
//        private boolean hasNextPage = false;
        pageInfo.setHasNextPage(p.isHasNextPage());
        //导航页码数
//        private int navigatePages;
        pageInfo.setNavigatePages(p.getNavigatePages());
        //所有导航页号
//        private int[] navigatepageNums;
        pageInfo.setNavigatepageNums(p.getNavigatepageNums());
        //导航条上的第一页
//        private int navigateFirstPage;
        pageInfo.setNavigateFirstPage(p.getNavigateFirstPage());
        //导航条上的最后一页
//        private int navigateLastPage;
        pageInfo.setNavigateLastPage(p.getNavigateLastPage());

        //设置total
        pageInfo.setTotal(p.getTotal());
        //设置list
        pageInfo.setList(modelToModelVo(p.getList()));
        return pageInfo;
    }
}
