package com.fei.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fei.springboot.domain.User;

@Mapper
public interface UserMapper {

	@Insert("insert sys_user(id,user_name) values(#{id},#{userName})")
	void insert(User u);
	
	@Select("select id,user_name from sys_user where id=#{id} ")
	User findById(@Param("id")String id);
	
	//注：方法名和要UserMapper.xml中的id一致
	List<User> query(@Param("userName")String userName);
	
}
