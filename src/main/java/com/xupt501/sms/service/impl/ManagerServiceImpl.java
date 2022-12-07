package com.xupt501.sms.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xupt501.sms.dao.BuildingDAO;
import com.xupt501.sms.dao.ManagerDAO;
import com.xupt501.sms.domain.Building;
import com.xupt501.sms.domain.Manager;
import com.xupt501.sms.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
import java.util.Map;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDAO  managerDAO;


    public List<Manager> findAll() {
        List<Manager> managers = managerDAO.selectAll();
        return managers;
    }


    public Page<Manager> search(Map searchMap) {
        //通用Mapper多条件搜索，标准写法
        Example example = new Example(Manager.class);//指定查询的表
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
        Page<Manager> managers = (Page<Manager>) managerDAO.selectByExample(example);
        return managers;
    }




    public Boolean add(Manager manager) {
        int row = managerDAO.insert(manager);
        if(row>0){
            return true;
        }else{
            return false;
        }
    }


    public Manager findById(Integer id) {
        return managerDAO.selectByPrimaryKey(id);
    }


    public Boolean update(Manager manager) {
        int row = managerDAO.updateByPrimaryKeySelective(manager);
        if(row>0){
            return true;
        }else{
            return false;
        }
    }


    public Boolean del(List<Integer> ids) {
        for (Integer id:ids) {
            managerDAO.deleteByPrimaryKey(id);
        }
        return true;
    }

}
