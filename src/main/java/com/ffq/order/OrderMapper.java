package com.ffq.order;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
	@Insert({ "insert into orders(oid, status) values('oid:1','下单')" })
	void save();
}
