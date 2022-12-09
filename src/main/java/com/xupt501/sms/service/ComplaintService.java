package com.xupt501.sms.service;

import com.github.pagehelper.Page;
import com.xupt501.sms.domain.Complaint;
import java.util.List;
import java.util.Map;

public interface ComplaintService {

    public List<Complaint> findAll();

    public Page<Complaint> search(Map<String, String> searchMap);

    public Boolean add(Complaint complaint);

    public Complaint findById(Integer id);

    public Boolean update(Complaint complaint);

    public Boolean del(List<Integer> ids);

    Boolean updateStatus(String status, Integer id);
}
