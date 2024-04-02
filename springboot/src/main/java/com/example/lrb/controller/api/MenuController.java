package com.example.lrb.controller.api;

import com.example.lrb.pojo.Menu;
import com.example.lrb.service.impl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
@Controller
@RequestMapping("/authority")
public class MenuController {
	@Autowired
	private MenuServiceImpl menuService;
	
	/**
     * @param
     * @return
     */
    @RequestMapping("/menus")
    @ResponseBody
    public Map<String,Object> getMenus(HttpServletRequest request) {
        Integer role_id = Integer.parseInt(request.getParameter("role_id"));
        Map<String,Object> menusMap = new HashMap();
        List<Menu> menus = menuService.getMenusByRoleId(role_id);
        menusMap.put("data",menus);

        Map<String,Object> metaMap = new HashMap();
        metaMap.put("msg","成功");
        metaMap.put("status",200);

        menusMap.put("meta",metaMap);
        return menusMap;
    }
}
