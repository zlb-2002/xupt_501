package com.xupt501.sms.service;

import com.github.pagehelper.Page;
import com.xupt501.sms.domain.Community;

import java.util.List;
import java.util.Map;

public interface CommunityService {

    public List<Community> findAll();

    public Page<Community> search(Map searchMap);


    public  Boolean add(Community community);

    public Boolean del(List<Integer> ids);


    public  Boolean update(Community community);

    public  Community findById(Integer id);

   public Boolean updateStatus(String status, Integer id);
}
