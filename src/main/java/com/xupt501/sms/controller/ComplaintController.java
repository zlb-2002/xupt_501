package com.xupt501.sms.controller;

import com.github.pagehelper.Page;
import com.xupt501.sms.common.MessageConstant;
import com.xupt501.sms.common.PageResult;
import com.xupt501.sms.common.Result;
import com.xupt501.sms.common.StatusCode;
import com.xupt501.sms.domain.Complaint;
import com.xupt501.sms.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @RequestMapping("/find")
    public Result find(){
        List<Complaint> all = complaintService.findAll();
        return new Result(true,200,"查询全部数据成功！",all);
    }
    @RequestMapping("/search")
    public PageResult search(@RequestBody Map<String, String> searchMap){
        Page<Complaint> page = complaintService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.COMMUNITY_SEARCH_SUCCESS,page.getResult(),page.getTotal());
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Complaint complaint){
        Boolean add = complaintService.add(complaint);
        return new Result(true,StatusCode.OK,MessageConstant.COMMUNITY_ADD_SUCCESS,add);
    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
        Complaint complaint = complaintService.findById(id);
        return new Result(true,StatusCode.OK,MessageConstant.COMMUNITY_FIND_BY_ID_SUCCESS,complaint);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Complaint complaint){
        Boolean update = complaintService.update(complaint);
        return new Result(true,StatusCode.OK,MessageConstant.COMMUNITY_UPDATE_SUCCESS, update);
    }

    @RequestMapping("/del")
    public Result del(@RequestBody List<Integer> ids){
        Boolean flag = complaintService.del(ids);
        return new Result(true,StatusCode.OK,MessageConstant.COMMUNITY_DELETE_SUCCESS, flag);
    }

    @RequestMapping("/updateStatus/{status}/{id}")
    public Result updateStatus(@PathVariable("status") String status, @PathVariable("id") Integer id){
        Boolean flag = complaintService.updateStatus(status,id);
        return new Result(true,StatusCode.OK,MessageConstant.COMMUNITY_UPDATE_STATUS_SUCCESS, flag);
    }


}
