package com.xupt501.sms.service;

import com.github.pagehelper.Page;
import com.xupt501.sms.domain.Building;
import com.xupt501.sms.domain.Community;

import java.util.List;
import java.util.Map;

public interface BuildingService {

    public List<Building> findAll();

    public Page<Building> search(Map searchMap);

    public Boolean add(Building building);

    public Building findById(Integer id);

    public Boolean update(Building building);

    public Boolean del(List<Integer> ids);
}
