package com.xupt501.sms.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xupt501.sms.dao.ComplaintDAO;
import com.xupt501.sms.domain.Complaint;
import com.xupt501.sms.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
import java.util.Map;

@Service
public class ComplaintImpl implements ComplaintService {

    @Autowired
    private ComplaintDAO complaintMapper;

    @Override
    public List<Complaint> findAll() {
        return complaintMapper.selectAll();
    }

    @Override
    public Page<Complaint> search(Map<String, String> searchMap) {
        Example example = new Example(Complaint.class);
        int pageNum = 1;
        int pageSize = 2;
        if(searchMap != null){
            Example.Criteria criteria = example.createCriteria();

            if(StringUtil.isNotEmpty(searchMap.get("startTime"))){
                criteria.andGreaterThanOrEqualTo("createTime",searchMap.get("startTime"));
            }
            if(StringUtil.isNotEmpty(searchMap.get("endTime"))){
                criteria.andLessThanOrEqualTo("createTime",searchMap.get("endTime"));
            }

            if(StringUtil.isNotEmpty(searchMap.get("title"))){
                criteria.andLike("ownerName", "%"+ searchMap.get("title") +"%");
            }
            if(StringUtil.isNotEmpty(searchMap.get("pageNum"))){
                pageNum =  Integer.parseInt(searchMap.get("pageNum"));
            }
            if(StringUtil.isNotEmpty(searchMap.get("pageSize"))){
                pageSize = Integer.parseInt(searchMap.get("pageSize"));
            }
        }
        PageHelper.startPage(pageNum,pageSize);
        return (Page<Complaint>) complaintMapper.selectByExample(example);

    }

    @Override
    public Boolean add(Complaint complaint) {
        return complaintMapper.insert(complaint) > 0;
    }

    @Override
    public Complaint findById(Integer id) {
        return complaintMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean update(Complaint complaint) {
        return complaintMapper.updateByPrimaryKeySelective(complaint) > 0;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            complaintMapper.deleteByPrimaryKey(id);
        }
        return true;
    }

    @Override
    public Boolean updateStatus(String status, Integer id) {
        Complaint complaint = new Complaint();
        complaint.setId(id);
        complaint.setStatus(status);
        return complaintMapper.updateByPrimaryKeySelective(complaint) > 0;
    }
}
