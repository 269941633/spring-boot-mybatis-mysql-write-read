package com.fei.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fei.springboot.domain.User;
import com.fei.springboot.service.UserService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello(){
		return "hello";
	}
	/**
	 * 测试插入
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String add(String id,String userName){
		User u = new User();
		u.setId(id);
		u.setUserName(userName);
		this.userService.insertUser(u);
		return u.getId()+"    " + u.getUserName();
	}
	/**
	 * 测试读
	 * @param id
	 * @return
	 */
	@RequestMapping("/get/{id}")
	@ResponseBody
	public String findById(@PathVariable("id") String id){
		User u = this.userService.findById(id);
		return u.getId()+"    " + u.getUserName();
	}
	/**
	 * 测试写然后读
	 * @param id
	 * @param userName
	 * @return
	 */
	@RequestMapping("/addAndRead")
	@ResponseBody
	public String addAndRead(String id,String userName){
		User u = new User();
		u.setId(id);
		u.setUserName(userName);
		this.userService.wirteAndRead(u);
		return u.getId()+"    " + u.getUserName();
	}
	
	/**
	 * 测试读然后写
	 * @param id
	 * @param userName
	 * @return
	 */
	@RequestMapping("/readAndAdd")
	@ResponseBody
	public String readAndWrite(String id,String userName){
		User u = new User();
		u.setId(id);
		u.setUserName(userName);
		this.userService.readAndWirte(u);
		return u.getId()+"    " + u.getUserName();
	}
	
	/**
	 * 测试分页插件
	 * @return
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	public String queryPage(){
		PageInfo<User> page = this.userService.queryPage("tes", 1, 2);
		StringBuilder sb = new StringBuilder();
		sb.append("<br/>总页数=" + page.getPages());
		sb.append("<br/>总记录数=" + page.getTotal()) ;
		for(User u : page.getList()){
			sb.append("<br/>" + u.getId() + "      " + u.getUserName());
		}
		System.out.println("分页查询....\n" + sb.toString());
		return sb.toString();
	}
	
}
