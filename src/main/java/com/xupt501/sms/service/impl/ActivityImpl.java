package com.xupt501.sms.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xupt501.sms.dao.ActivityDAO;
import com.xupt501.sms.domain.Activity;
import com.xupt501.sms.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
import java.util.Map;

@Service
public class ActivityImpl implements ActivityService {

    @Autowired
    private ActivityDAO activityMapper;

    @Override
    public List<Activity> findAll() {
        return activityMapper.selectAll();
    }

    @Override
    public Page<Activity> search(Map<String, String> searchMap) {
        Example example = new Example(Activity.class);
        int pageNum = 1;
        int pageSize = 2;
        if(searchMap != null){
            Example.Criteria criteria = example.createCriteria();

            if(StringUtil.isNotEmpty(searchMap.get("startTime"))){
                criteria.andGreaterThanOrEqualTo("startTime",searchMap.get("startTime"));
            }
            if(StringUtil.isNotEmpty(searchMap.get("endTime"))){
                criteria.andLessThanOrEqualTo("startTime",searchMap.get("endTime"));
                criteria.andLessThanOrEqualTo("startTime",searchMap.get("endTime"));
            }

            if(StringUtil.isNotEmpty(searchMap.get("title"))){
                criteria.andLike("title", "%"+ searchMap.get("title") +"%");
            }
            if(StringUtil.isNotEmpty(searchMap.get("pageNum"))){
                pageNum =  Integer.parseInt(searchMap.get("pageNum"));
            }
            if(StringUtil.isNotEmpty(searchMap.get("pageSize"))){
                pageSize = Integer.parseInt(searchMap.get("pageSize"));
            }
        }
        PageHelper.startPage(pageNum,pageSize);
        return (Page<Activity>) activityMapper.selectByExample(example);
    }

    @Override
    public Boolean add(Activity activity) {
        return activityMapper.insert(activity) > 0;
    }

    @Override
    public Activity findById(Integer id) {
        return activityMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean update(Activity activity) {
        return activityMapper.updateByPrimaryKeySelective(activity) > 0;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            activityMapper.deleteByPrimaryKey(id);
        }
        return true;
    }

    @Override
    public Boolean updateStatus(String status, Integer id) {
        Activity activity = new Activity();
        activity.setId(id);
        activity.setStatus(status);
        return activityMapper.updateByPrimaryKeySelective(activity) > 0;
    }
}
