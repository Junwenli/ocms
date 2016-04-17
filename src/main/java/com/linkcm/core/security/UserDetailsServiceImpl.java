package com.linkcm.core.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.linkcm.core.entity.sys.SysUser;
import com.linkcm.core.service.sys.SysGivePermissionService;
import com.linkcm.core.service.sys.SysRoleService;
import com.linkcm.core.service.sys.SysUserService;
import com.linkcm.core.util.CoreUtils;
import com.linkcm.core.util.SysConst;

/**
 * 实现SpringSecurity的UserDetailsService接口,实现获取用户Detail信息的回调函数.
 * 
 * @author pengyi
 */
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private SysUserService sysUserMgtService;
	@Autowired
	private SysRoleService sysRoleMgtService;
	@Autowired
	private SysGivePermissionService sysGivePermissionMgtService;

	/**
	 * 获取用户Details信息的回调函数.
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		SysUser user = sysUserMgtService.findByUserCode(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户" + username + " 不存在");
		}
		CoreUtils.context.getServletContext().setAttribute(SysConst.USER_ID, user.getUserCode());
		Set<GrantedAuthority> grantedAuths = new HashSet<GrantedAuthority>();
	    sysRoleMgtService.findGrantedAuthorityByUserId(user.getId(),grantedAuths);
		sysGivePermissionMgtService.findGrantedAuthorityByUserId(user.getId(),grantedAuths);
		
		
		//-- mini-web示例中无以下属性, 暂时全部设为true. --//
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		UserDetails userdetails = new SecurityUser(user.getId(), user.getName(), user.getAuthenticator().toLowerCase(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);

		return userdetails;
	}
}
