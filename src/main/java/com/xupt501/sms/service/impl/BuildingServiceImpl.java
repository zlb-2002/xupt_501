package com.xupt501.sms.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xupt501.sms.dao.BuildingDAO;
import com.xupt501.sms.dao.CommunityDAO;
import com.xupt501.sms.domain.Building;
import com.xupt501.sms.domain.Community;
import com.xupt501.sms.service.BuildingService;
import com.xupt501.sms.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
import java.util.Map;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingDAO buildingDAO;

    @Override
    public List<Building> findAll() {
        List<Building> buildings = buildingDAO.selectAll();
        return buildings;
    }

    @Override
    public Page<Building> search(Map searchMap) {
        //通用Mapper多条件搜索，标准写法
        Example example = new Example(Building.class);//指定查询的表tb_community
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
            //分页
            /*if(StringUtil.isNotEmpty((String) searchMap.get("pageNum"))){
                pageNum = Integer.parseInt((String) searchMap.get("pageNum"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("pageSize"))){
                pageSize = Integer.parseInt((String) searchMap.get("pageSize"));
            }*/
            if((Integer) searchMap.get("pageNum") !=null){
                pageNum = (Integer) searchMap.get("pageNum");
            }
            if((Integer) searchMap.get("pageSize") !=null){
                pageSize = (Integer) searchMap.get("pageSize");
            }
        }
        PageHelper.startPage(pageNum,pageSize);//使用PageHelper插件完成分页
        Page<Building> buildings = (Page<Building>) buildingDAO.selectByExample(example);
        return buildings;
    }

    @Override
    public Boolean add(Building community) {
        int row = buildingDAO.insert(community);
        if(row>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Building findById(Integer id) {
        return buildingDAO.selectByPrimaryKey(id);
    }

    @Override
    public Boolean update(Building building) {
        int row = buildingDAO.updateByPrimaryKeySelective(building);
        if(row>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id:ids) {
            buildingDAO.deleteByPrimaryKey(id);
        }
        return true;
    }

}
