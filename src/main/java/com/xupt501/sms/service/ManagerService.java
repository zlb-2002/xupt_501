package com.xupt501.sms.service;

import com.github.pagehelper.Page;
import com.xupt501.sms.domain.Building;
import com.xupt501.sms.domain.Manager;

import java.util.List;
import java.util.Map;

public interface ManagerService {
    public List<Manager> findAll();

    public Page<Manager> search(Map searchMap);

    public Boolean add(Manager manager);

    public Manager findById(Integer id);

    public Boolean update(Manager manager);

    public Boolean del(List<Integer> ids);
}
