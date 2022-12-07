package com.xupt501.sms.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xupt501.sms.dao.CommunityDAO;
import com.xupt501.sms.domain.Community;
import com.xupt501.sms.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.*;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityDAO communityDAO;

    @Override
    public List<Community> findAll() {
        List<Community> communities = communityDAO.selectAll();
        return communities;
    }

    @Override
    public Page<Community> search(Map searchMap) {
        //通用Mapper多条件搜索，标准写法
        Example example = new Example(Community.class);//指定查询的表tb_community
        //1.初始化分页条件
        int pageNum = 1;
        int pageSize = 2;
        if(searchMap != null){
            Example.Criteria criteria = example.createCriteria();//创建查询条件
            //时间区间
            if(StringUtil.isNotEmpty((String) searchMap.get("startTime"))){
                criteria.andGreaterThanOrEqualTo("createTime",searchMap.get("startTime"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("endTime"))){
                criteria.andLessThanOrEqualTo("createTime",searchMap.get("endTime"));
                criteria.andLessThanOrEqualTo("createTime",searchMap.get("endTime"));
            }
            //名称模糊搜索
            if(StringUtil.isNotEmpty((String) searchMap.get("name"))){
                criteria.andLike("name", "%"+(String) searchMap.get("name")+"%");
            }

            if((Integer) searchMap.get("pageNum") !=null){
                pageNum = (Integer) searchMap.get("pageNum");
            }
            if((Integer) searchMap.get("pageSize") !=null){
                pageSize = (Integer) searchMap.get("pageSize");
            }
        }
        PageHelper.startPage(pageNum,pageSize);//使用PageHelper插件完成分页
        Page<Community> communities = (Page<Community>) communityDAO.selectByExample(example);
        return communities;
    }


    public Boolean add(Community community) {
        int row = communityDAO.insert(community);
        if(row>0){
            return true;
        }else{
            return false;
        }
    }


    public Community findById(Integer id)  {
        return communityDAO.selectByPrimaryKey(id);
    }


    public Boolean update(Community community) {
        int row = communityDAO.updateByPrimaryKeySelective(community);
        if(row>0){
            return true;
        }else{
            return false;
        }
    }


    public Boolean updateStatus(String status, Integer id) {
        Community community = new Community();
        community.setId(id);
        community.setStatus(status);
        int row = communityDAO.updateByPrimaryKeySelective(community);
        if(row>0){
            return true;
        }else{
            return false;
        }
    }


    public Boolean del(List<Integer> ids) {
        for (Integer id:ids) {
            communityDAO.deleteByPrimaryKey(id);
        }
        return true;
    }

}
