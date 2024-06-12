package com.school.service.impl;

import com.school.dao.RecordMapper;
import com.school.entity.Record;
import com.school.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;
    @Override
    public Integer insertRecord(Record record) {
        return recordMapper.insert(record);
    }

    @Override
    public List<Record> getRecordList(String username, Integer examId) {
        return recordMapper.getRecordList(username, examId);
    }
}
