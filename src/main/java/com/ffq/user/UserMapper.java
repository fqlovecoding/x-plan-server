package com.ffq.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
	@Select("select * from user")
	List<User> list();
	
	@Select("select * from user where uid=#{uid}")
	List<User> listByUid(String uid);

	@Select("select count(*) from user where uid=#{uid} and url=#{url}")
	Integer boolByUidAndUrl(String uid, String url);
}
