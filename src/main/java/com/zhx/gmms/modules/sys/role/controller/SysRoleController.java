package com.zhx.gmms.modules.sys.role.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhx.gmms.frame.BaseController;
import com.zhx.gmms.modules.sys.role.bean.SysRole;
import com.zhx.gmms.modules.sys.role.service.SysRoleService;

@Controller
@RequestMapping("/role")
public class SysRoleController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(SysRoleController.class);
	
	@Autowired
	private SysRoleService roleService;

	/**
	 * 跳转到角色列表页面
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String roleList(){
		return "web/role/list";
	}
	
	/**
	 * 获取角色列表
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.PATCH)
	@ResponseBody
	public String roleList(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String,String> params){
		logger.info("获取角色列表：params="+params);
//		params.putAll(paramsStr2Map(params.get("params")));
//		Map<String,Object> result = roleService.queryRoleList(params);
//		return returnJson4Custom(result);
		return "";
	}
	
	/**
	 * 跳转到添加角色页面
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addRole(){
		return "web/role/add";
	}
	
	/**
	 * 添加角色
	 * @param role
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public String addRole(HttpServletRequest request,SysRole role) {
		logger.info("添加角色:role="+role);
//		int addRet=0;
//		try {
//			//获取当前登录角色的ID
//			role.setCreator(getUserId(request));
//			addRet = roleService.saveRole(role);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//			return returnJson4Exception(e);
//		}
//		return addRet==1?returnJson4Success():returnJson4Fail();
		return "";
	}
	
	/**
	 * 跳转到添加修改页面
	 */
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public ModelAndView editRole(@PathVariable String id) throws Exception{
		logger.info("跳转到修改角色页面：roleId="+id);
		ModelAndView mv = new ModelAndView();
		SysRole role = roleService.queryById(id);
		mv.setViewName("web/role/edit");
		mv.addObject("role", role);
		return mv;
	}
	
	/**
	 * 修改角色
	 * @param role
	 */
	@RequestMapping(value="/edit",method=RequestMethod.PUT)
	@ResponseBody
	public String editRole(HttpServletRequest request,SysRole role){
		logger.info("修改角色:role="+role);
//		int addRet = 0;
//		try {
//			//获取当前登录角色的ID
//			role.setUpdator(getUserId(request));
//			addRet = roleService.editRole(role);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//			return returnJson4Exception(e);
//		}
//		return addRet==1?returnJson4Success():returnJson4Fail();
		return "";
	}
	
	/**
	 * 跳转到角色授权权限页面
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/grant/{id}",method=RequestMethod.GET)
	public String grantRight(Model model,@PathVariable String id) throws Exception{
		logger.info("跳转到角色授权权限页面：roleId="+id);
		List<Map<String,Object>> rightList = roleService.queryRoleRightByRoleId(id);
		model.addAttribute("roleId", id);
		model.addAttribute("rightList", new ObjectMapper().writeValueAsString(rightList));
		return "web/role/grant";
	}
	
	/**
	 * 角色授权权限
	 * @param request
	 * @param roleId
	 * @param rightIds
	 * @return
	 */
	@RequestMapping(value="/grant",method=RequestMethod.POST)
	@ResponseBody
	public String grantRight(HttpServletRequest request,String roleId,String[] rightIds){
		logger.info("角色授权权限：roleId="+roleId+",rightIds="+rightIds);
//		boolean result = false;
//		try{
//			result = roleService.grantRoleRight(roleId,rightIds);
//			refreshSession4Right(request, "002");
//		}catch(Exception e){
//			logger.error(e.getMessage());
//			return returnJson4Exception(e);
//		}
//		return result?returnJson4Success("角色授权权限成功！"):returnJson4Fail("角色授权权限失败！");
		return "";
	}
	
	/**
	 * 角色名称是否唯一
	 * @param request
	 * @param response
	 * @param roleCode
	 * @return
	 */
	@RequestMapping(value="/roleUnique",method=RequestMethod.POST)
	@ResponseBody
	public String roleUnique(HttpServletRequest request,HttpServletResponse response,String roleName){
		logger.info("角色名称是否唯一：roleName="+roleName);
		SysRole role = roleService.queryByRoleName(roleName);
//		if(null==role){
//			return returnJson4Success();
//		}else {
//			return returnJson4Fail();
//		}
		return "";
	}
	
	/**
	 * 删除角色
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.DELETE)
	@ResponseBody
	public String removeRoles(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String,String> params){
		logger.info("删除角色：params="+params);
		boolean result = false;
		try{
			result = roleService.removeRoles(params);
		}catch(Exception e){
			logger.error(e.getMessage());
//			return returnJson4Exception(e);
		}
//		return result?returnJson4Success("删除角色成功！"):returnJson4Fail("删除角色失败！");
		return "";
	}
	
	/**
	 * 导出角色
	 * @param params
	 * @param response
	 */
	@RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, String> params, HttpServletResponse response) {
        logger.info("导出角色列表EXCEL");
        String workBookName = "角色列表";//文件名
        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
        cellName.put("id", "角色主键");
        cellName.put("role_name", "角色名称");
        cellName.put("role_desc", "角色描述");
        //列值
        List<Map<String, Object>> cellValues = null;
        try {
//        	params = paramsStr2Map(params.get("pramas"));
//            cellValues = roleService.exportRole(params);
//            CommonExcelExport.excelExport(response,cellName,cellValues,workBookName);
        } catch (Exception e) {
            logger.error("导出角色列表EXCEL异常" + e.getMessage());
        }
    }
}
