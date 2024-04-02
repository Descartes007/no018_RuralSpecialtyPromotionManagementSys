package com.example.lrb.dao;

import com.example.lrb.pojo.Menu;

import java.util.List;

public interface MenuDao {
	public List<Menu> getMenusByRoleId(Integer role_id);
}
