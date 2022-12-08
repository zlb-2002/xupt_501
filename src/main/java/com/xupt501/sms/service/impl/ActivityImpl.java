package com.xupt501.sms.service.impl;

import com.github.pagehelper.Page;
import com.xupt501.sms.domain.Activity;
import com.xupt501.sms.service.ActivityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ActivityImpl implements ActivityService {


    @Override
    public List<Activity> findAll() {
        // TODO 张乐贝
        return null;
    }

    @Override
    public Page<Activity> search(Map<String, String> searchMap) {
        // TODO 张乐贝
        return null;
    }

    @Override
    public Boolean add(Activity activity) {
        // TODO 张乐贝
        return null;
    }

    @Override
    public Activity findById(Integer id) {
        // TODO 张乐贝
        return null;
    }

    @Override
    public Boolean update(Activity activity) {
        // TODO 张乐贝
        return null;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        // TODO 张乐贝
        return null;
    }
}
