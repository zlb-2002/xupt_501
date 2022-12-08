package com.xupt501.sms.service;

import com.github.pagehelper.Page;
import com.xupt501.sms.domain.Activity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public interface ActivityService {

    public List<Activity> findAll();

    public Page<Activity> search(Map<String, String> searchMap);

    public Boolean add(Activity activity);

    public Activity findById(Integer id);

    public Boolean update(Activity activity);

    public Boolean del(List<Integer> ids);

}
