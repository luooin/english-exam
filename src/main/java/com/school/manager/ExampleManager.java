package com.school.manager;

import com.school.entity.Example;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ExampleManager {
    private Map<Integer, Example> exampleMap = new ConcurrentHashMap<>();

    public Example getExample(int userId){
        return exampleMap.get(userId);
    }
    public void putExampleMap(int userId, Example example) {
        this.exampleMap.put(userId, example);
    }
}
