package com.doggiehome.doginfomgmt.controller.manage;

import com.doggiehome.doginfomgmt.common.Const;
import com.doggiehome.doginfomgmt.common.ResponseCode;
import com.doggiehome.doginfomgmt.common.ServerResponse;
import com.doggiehome.doginfomgmt.service.ManagerService;
import com.doggiehome.doginfomgmt.util.CookieUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Api(value = "管理员相关", tags = {"管理员相关的api接口"})
@RequestMapping("manager")
@RestController
public class ManagerController {


    @Autowired
    private ManagerService managerService;



    /**
     * 管理员登陆
     */
    @ApiOperation(value = "管理员登陆", notes = "管理员登陆", httpMethod = "POST")
    @PostMapping("/managerLogin")
    public ServerResponse managerLogin(@ApiParam(value = "管理员用户名", required = true) @RequestParam String username,
                                       @ApiParam(value = "管理员密码", required = true) @RequestParam String password,
                                       @ApiIgnore HttpSession session
//            ,
//                                       @ApiIgnore HttpServletRequest request,
//                                       @ApiIgnore HttpServletResponse response
    ){
        ServerResponse serverResponse = managerService.login(username, password);
//        System.out.println("serverResponse.isSuccess: " + serverResponse.isSuccess());
        if (serverResponse.isSuccess()){

//            System.out.println("isSuccess");
//            request.getSession(false).setAttribute(Const.CURRENT_USER, serverResponse.getData());

//            System.out.println("request Attribute CURRENT_USER" + request.getSession(false).getAttribute(Const.CURRENT_USER));

//            System.out.println("request  Interval : "+ request.getSession(false).getMaxInactiveInterval());


            session.setAttribute(Const.CURRENT_USER, serverResponse.getData());

//            System.out.println("session Attribute CURRENT_USER" + session.getAttribute(Const.CURRENT_USER));

//            System.out.println("session Interval : "+session.getMaxInactiveInterval());

//            CookieUtil.setToken(response, session.getId());
//            return serverResponse;
//            ServletContext servletContext = this.get
//            ServletConfig config = super
//            ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
//            ServletContext servletContext = this.getServletContext();
//            ServletContext servletContext = super.getServletContext();

//            servletContext.setAttribute(session.getId(), serverResponse.getData());
//            Const.sessionMap.put(session.getId(), serverResponse.getData());
//            Boolean result = dataCacheRepository.add("session", session.getId(), serverResponse.getData());
        }
//        else {
//            System.out.println("noSuccess");
////            return serverResponse;
//        }

        return serverResponse;
    }

    /**
     * 管理员登出
     */
    @ApiOperation(value = "管理员登出", notes = "管理员登出", httpMethod = "GET")
    @GetMapping("/managerLogout")
    public ServerResponse managerLogout(@ApiIgnore HttpSession session
//            ,
//                                        @ApiIgnore HttpServletRequest request,
//                                        @ApiIgnore  HttpServletResponse response
    ){
//        ServerResponse serverResponse = managerService.login(username, password);
//        if (serverResponse.isSuccess()){

//        request.getSession(false).removeAttribute(Const.CURRENT_USER);
            session.removeAttribute(Const.CURRENT_USER);

//            CookieUtil.setToken(response, session.getId());
//            ServletContext servletContext = this.get
//            ServletConfig config = super
//            ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
//            ServletContext servletContext = this.getServletContext();
//            ServletContext servletContext = super.getServletContext();

//            servletContext.setAttribute(session.getId(), serverResponse.getData());
//            Const.sessionMap.put(session.getId(), serverResponse.getData());
//            Boolean result = dataCacheRepository.add("session", session.getId(), serverResponse.getData());
//        }
        return ServerResponse.successResponse(ResponseCode.SUCCESS.getCode(), "登出成功");
    }

    /**
     * 新建管理员账户
     */
    @ApiOperation(value = "新建管理员", notes = "新建管理员", httpMethod = "GET")
    @GetMapping("/newManager")
    public ServerResponse newManager(@ApiParam(value = "管理员用户名", required = true) @RequestParam String username,
                                     @ApiParam(value = "管理员密码", required = true) @RequestParam String password,
                                     @ApiParam(value = "角色（1：超级管理员，2：普通管理员）", allowableValues="1, 2", required = true) @RequestParam int role
//                                     @ApiIgnore HttpSession session,
//                                     @ApiIgnore HttpServletRequest request

    ){
//        System.out.println("session.getAttribute: " + session.getAttribute(Const.CURRENT_USER));
//        session.getAttribute(Const.CURRENT_USER);

//        System.out.println(""+session.getMaxInactiveInterval());
//        System.out.println("request session.getAttribute: " + request.getSession(false).getAttribute(Const.CURRENT_USER));

//        System.out.println(""+ request.getSession(false).getMaxInactiveInterval());
//        request.getSession(false).getAttribute(Const.CURRENT_USER);

        try {
            ServerResponse serverResponse = managerService.newManager(username, password, role);

            return serverResponse;
        } catch (DataIntegrityViolationException e){

            ServerResponse serverResponse = ServerResponse.errorResponse(ResponseCode.MANAGER_EXIST.getCode(), ResponseCode.MANAGER_EXIST.getDesc());
            return serverResponse;
        }
//        return serverResponse;
    }
}
