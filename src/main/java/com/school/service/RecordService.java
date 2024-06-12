package com.school.service;

import com.school.entity.Record;

import java.util.List;

public interface RecordService {

    Integer insertRecord(Record record);

    List<Record> getRecordList(String username,Integer examId);
}
