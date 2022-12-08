package com.xupt501.sms.controller;

import com.github.pagehelper.Page;
import com.xupt501.sms.common.MessageConstant;
import com.xupt501.sms.common.PageResult;
import com.xupt501.sms.common.Result;
import com.xupt501.sms.common.StatusCode;
import com.xupt501.sms.domain.Activity;
import com.xupt501.sms.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping("/find")
    public Result find(){
        List<Activity> all = activityService.findAll();
        return new Result(true,200,"查询全部数据成功！",all);
    }
    @RequestMapping("/search")
    public PageResult search(@RequestBody Map<String, String> searchMap){
        Page<Activity> page = activityService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.COMMUNITY_SEARCH_SUCCESS,page.getResult(),page.getTotal());
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Activity manager){
        Boolean add = activityService.add(manager);
        return new Result(true,StatusCode.OK,MessageConstant.COMMUNITY_ADD_SUCCESS,add);
    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
        Activity activity = activityService.findById(id);
        return new Result(true,StatusCode.OK,MessageConstant.COMMUNITY_FIND_BY_ID_SUCCESS,activity);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Activity activity){
        Boolean update = activityService.update(activity);
        return new Result(true,StatusCode.OK,MessageConstant.COMMUNITY_UPDATE_SUCCESS, update);
    }

    @RequestMapping("/del")
    public Result del(@RequestBody List<Integer> ids){
        Boolean flag = activityService.del(ids);
        return new Result(true,StatusCode.OK,MessageConstant.COMMUNITY_DELETE_SUCCESS, flag);
    }

}
