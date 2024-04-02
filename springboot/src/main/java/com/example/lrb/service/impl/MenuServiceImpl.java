package com.example.lrb.service.impl;

import com.example.lrb.dao.MenuDao;
import com.example.lrb.pojo.Menu;
import com.example.lrb.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao menuDao;

	@Override
	public List<Menu> getMenusByRoleId(Integer role_id) {
		return menuDao.getMenusByRoleId(role_id);
	}

}
