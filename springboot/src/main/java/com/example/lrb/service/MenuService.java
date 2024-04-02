package com.example.lrb.service;

import com.example.lrb.pojo.Menu;

import java.util.List;

public interface MenuService {
	public List<Menu> getMenusByRoleId(Integer role_id);
}
