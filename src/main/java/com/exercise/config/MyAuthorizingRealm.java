package com.exercise.config;

import com.exercise.user.domain.Permission;
import com.exercise.user.domain.Role;
import com.exercise.user.domain.User;
import com.exercise.user.service.IpermissionService;
import com.exercise.user.service.IroleService;
import com.exercise.user.service.IuserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyAuthorizingRealm extends AuthorizingRealm {
    private final static Logger logger = LoggerFactory.getLogger(MyAuthorizingRealm.class);

    @Lazy
    @Autowired
    private IuserService userService;
    @Autowired
    @Lazy
    private IroleService roleService;
    @Autowired
    @Lazy
    private IpermissionService permissionService;
    //shiro的权限配置方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("权限配置-->doGetAuthorizationInfo");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        logger.info("----------------------------->"+principals.getPrimaryPrincipal());

        Object principal = principals.getPrimaryPrincipal();
        User user = userService.findUserByname((String) principal);

        Role role = roleService.findObjectById(user.getRoleId());
        authorizationInfo.addRole(role.getName());
        List<Permission> permissions = permissionService.findPermissionByRoleId(role.getId());
        for (Permission permission : permissions) {
            authorizationInfo.addStringPermission(permission.getPermission());
        }

        logger.info("用户"+user.getUsername()+"具有的角色:"+authorizationInfo.getRoles());
        logger.info("用户"+user.getUsername()+"具有的权限："+authorizationInfo.getStringPermissions());
        return authorizationInfo;
    }

    //shiro的身份验证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("正在验证身份...");
        SimpleAuthenticationInfo info=null;
        //将token转换成UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        //从转换后的token中获取用户名
        String username= upToken.getUsername();
        logger.info("----->"+username);
        //查询数据库，得到用户

        User user = userService.findUserByname(username);

        //得到加密密码的盐值
        ByteSource salt = ByteSource.Util.bytes(user.getUsername());
        info = new SimpleAuthenticationInfo(
                user.getUsername(), //用户名
                user.getPassword(), //密码
                salt, //加密的盐值
                getName()  //realm name
        );
        return info;
    }


}
