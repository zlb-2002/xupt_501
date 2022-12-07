package com.xupt501.sms.controller;

import com.github.pagehelper.Page;
import com.xupt501.sms.common.MessageConstant;
import com.xupt501.sms.common.PageResult;
import com.xupt501.sms.common.Result;
import com.xupt501.sms.common.StatusCode;
import com.xupt501.sms.domain.Building;
import com.xupt501.sms.domain.Community;
import com.xupt501.sms.domain.Manager;
import com.xupt501.sms.service.BuildingService;
import com.xupt501.sms.service.CommunityService;
import com.xupt501.sms.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Auth: zhuan
 * @Desc:
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @RequestMapping("/find")
    public Result find(){
        List<Manager> all = managerService.findAll();
        return new Result(false,200,"请求成功adasdasdas",all);
    }
    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap){
        Page<Manager> page = managerService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.COMMUNITY_SEARCH_SUCCESS,page.getResult(),page.getTotal());
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Manager manager){
        Boolean add = managerService.add(manager);
        return new Result(true,StatusCode.OK,MessageConstant.COMMUNITY_ADD_SUCCESS);
    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
        Manager manager = managerService.findById(id);
        return new Result(true,StatusCode.OK,MessageConstant.COMMUNITY_FIND_BY_ID_SUCCESS,manager);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Manager manager){
        Boolean add = managerService.update(manager);
        return new Result(true,StatusCode.OK,MessageConstant.COMMUNITY_UPDATE_SUCCESS);
    }

    @RequestMapping("/del")
    public Result del(@RequestBody List<Integer> ids){
        Boolean flag = managerService.del(ids);
        return new Result(true,StatusCode.OK,MessageConstant.COMMUNITY_DELETE_SUCCESS);
    }
}
