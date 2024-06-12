package com.school.service.impl;

import com.school.dao.TypeMapper;
import com.school.entity.Type;
import com.school.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;
    @Override
    public Type selectById(Integer typeId) {
        return typeMapper.selectByPrimaryKey(typeId);
    }
}
