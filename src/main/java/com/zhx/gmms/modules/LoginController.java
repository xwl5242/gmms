package com.zhx.gmms.modules;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zhx.gmms.frame.BaseController;
import com.zhx.gmms.frame.Const;
import com.zhx.gmms.frame.captcha.Captcha;
import com.zhx.gmms.frame.captcha.GifCaptcha;
import com.zhx.gmms.frame.captcha.SpecCaptcha;
import com.zhx.gmms.modules.sys.right.RightUtils;
import com.zhx.gmms.modules.sys.right.bean.SysRight;
import com.zhx.gmms.modules.sys.theme.bean.SysTheme;
import com.zhx.gmms.modules.sys.user.bean.SysUser;
import com.zhx.gmms.modules.sys.user.service.SysUserService;
import com.zhx.gmms.utils.DESUtils;
import com.zhx.gmms.utils.DateUtils;

@Controller
public class LoginController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private SysUserService userService;

	/**
	 * 系统入口，跳转到登录页
	 * @param user
	 * @return
	 */
	@GetMapping("/index")
	public String index(){
		return "login";
	}
	
	/**
	 * 登录成功进入首页
	 * @return
	 */
	@GetMapping(value="/home")
	public String home(){
		return "index";
	}
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @param user 用户信息，用户名和密码
	 * @param validCode 验证吗
	 * @param needCaptcha 需不需要验证码
	 * @param model
	 * @return
	 */
	@PostMapping(value="/loginIn")
	public String loginIn(HttpServletRequest request,HttpServletResponse response,
			SysUser user,String validCode,boolean needCaptcha,RedirectAttributes model) {
		logger.info("user login,user info:"+user);
		String result = "";
		try {
			HttpSession session = request.getSession();
			//判断是否是锁屏后再次登录，needCaptcha：false为锁屏后再登录
			boolean validFlag = true;
			if(needCaptcha){
				String code = session.getAttribute(Const.SESSION_CAPTCHA_CODE).toString();//获取session中的验证码
				validFlag = code.toLowerCase().equals(validCode.toLowerCase());
			}
			//判断验证码是否正确
			if(validFlag){
				//根据输入的用户名查询用户是否存在
				SysUser loginUser = userService.queryByUserCode(user.getUserCode());
				if(null!=loginUser){//用户存在
					//用户是否启用
					if("0".equals(loginUser.getUseStatus())){
						result = "该用户被禁用，请联系管理员！";
					}else{
						String pwd = DESUtils.decrypt(loginUser.getPassword());//获取数据库中该用户的密码
						if(pwd.equals(user.getPassword())){//如果密码一致，登录成功
							//登录成功后更新上次登录时间和登录次数
							String lastLoginTime = DateUtils.date2yyyyMMddHHmmssStr(new Date());
							//登录次数
							int loginTotal = StringUtils.isEmpty(loginUser
									.getLoginTotal()) ? 0 : Integer
									.parseInt(loginUser.getLoginTotal());
							//设置上次登录时间和登录次数
							SysUser editUser = new SysUser();
							editUser.setId(loginUser.getId());
							editUser.setLastLoginTime(lastLoginTime);
							editUser.setLoginTotal(loginTotal+1+"");
							//更新当前登录用户的信息
							userService.editUser(editUser);
							//查询该登录用户的权限信息
							List<SysRight> rights = userService.queryRights(loginUser.getId());
							session.setAttribute(Const.SESSION_RIGHT, RightUtils.menuTreeList(rights, Const.RIGHT_ROOT));//用户的权限
							session.setAttribute(Const.SESSION_USER, loginUser);//用户信息
							session.setAttribute(Const.SESSION_USER_ID, loginUser.getId());//用户信息
							session.setAttribute(Const.SESSION_USER_NAME, loginUser.getUserName());//用户名称
							if(StringUtils.isNotBlank(loginUser.getThemeId())){
								SysTheme theme = userService.getCurUserTheme(loginUser.getThemeId());
								session.setAttribute(Const.SESSION_THEME,theme);
								session.setAttribute(Const.SESSION_THEME_JSON,objectMapper.writeValueAsString(theme));
							}
//							session.setAttribute(Const.SESSION_RIGHT_CHANGED, false);//用户名称
//							session.setAttribute(Const.SESSION_RIGHT_CHANGED_MENU, "");//用户名称
							logger.info("login success,user info:"+loginUser);
							//登录成功跳转到首页
							return "redirect:/home";
						}else{
							//密码错误
							result = "密码错误！";
						}
					}
				}else{//用户不存在
					result = "该用户不存在！";
				}
			}else{
				result = "您输入的验证码不正确！";
			}
		} catch (Exception e) {
			result = e.getMessage();
			logger.error(e.getMessage());
		}
		model.addFlashAttribute("user",user);
		model.addFlashAttribute("message",result);
		return "redirect:/index";
	}
	
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping(value="/loginOut")
	public String loginOut(HttpServletRequest request,HttpServletResponse response){
		clearSession(request);
		return "redirect:/index";
	}
	
	/**
	 * 获取验证码
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@GetMapping("/captcha")
	public void captcha(HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.info("create captcha for login start...");
		Captcha captcha = null;
		try{
			//获取要生产的验证码的类型:gif和png
			String captchaType=env.getProperty(Const.CAPTCHA_TYPE);
			//要生成的验证码中字母的个数
			int captchaCharNum = Integer.valueOf(env.getProperty(Const.CAPTCHA_CHAR_NUM));
			if(Const.CAPTCHA_TYPE_GIF.equals(captchaType)){
				//gif格式动画验证码：宽，高，字母个数
		        captcha = new GifCaptcha(120,40,captchaCharNum).makeCode();
			}else if(Const.CAPTCHA_TYPE_PNG.equals(captchaType)){
				//png格式验证码：宽，高，字母个数
				captcha = new SpecCaptcha(120,40,captchaCharNum).makeCode();
			}
			String code = captcha.text();//具体字母
			request.getSession().setAttribute(Const.SESSION_CAPTCHA_CODE, code);
			logger.info("captcha-------------->"+code);
			response.setContentType("image/png"); 
			captcha.out(response.getOutputStream());
		}catch(Exception e){
			logger.error(e.getMessage());
			throw e;
		}
		logger.info("create captcha for login end...");
	}
}
