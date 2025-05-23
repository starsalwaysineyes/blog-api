package com.jt.service.impl;

import com.jt.entity.Log;
import com.jt.repository.LogRepository;
import com.jt.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Override
    public Integer saveLog(Log log) {
        return logRepository.save(log).getId();
    }
}
