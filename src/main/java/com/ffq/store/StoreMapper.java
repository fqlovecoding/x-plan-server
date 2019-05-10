package com.ffq.store;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StoreMapper {
	@Select("select num from store where id=#{id}")
	Long getGoodsNumById(String id);
	
	@Update("update store set num=#{num} where id=#{id}")
	void reduce(String id,int num);
}
