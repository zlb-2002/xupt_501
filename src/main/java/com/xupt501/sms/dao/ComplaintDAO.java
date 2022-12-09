package com.xupt501.sms.dao;

import com.xupt501.sms.domain.Complaint;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ComplaintDAO extends Mapper<Complaint> {
}
