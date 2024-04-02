package com.example.lrb.pojo;

import java.util.List;

/**
 * @author yk.ddm
 * @createDate 2021-05-31 11:29
 *
 *      菜单实体类    管理员菜单     普通用户菜单
 */

public class Menu {
    private int id;

    private int parent_id;  // 父 id
    
    private String name; // 菜单名称

    private String path; // 跳转地址

    private String icon; // 菜单icon图标
    
    private List<Menu> children;  //  子菜单

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}
}
