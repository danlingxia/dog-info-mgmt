package com.doggiehome.doginfomgmt.interceptor;

import com.doggiehome.doginfomgmt.common.Const;
import com.doggiehome.doginfomgmt.common.ResponseCode;
import com.doggiehome.doginfomgmt.common.ServerResponse;
import com.doggiehome.doginfomgmt.pojo.Manager;
import com.doggiehome.doginfomgmt.pojo.vo.ManagerVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Component
@Order(2)
public class ManagerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        System.out.println("ManagerInterceptor");
//        if (request.getMethod().equals("OPTIONS")){
//            System.out.println("OPTIONS" + request.getMethod());
//            return true;
//        }

        ServerResponse serverResponse;

        HttpSession session = request.getSession(false);
//        if (null == session){
//            serverResponse = ServerResponse.errorResponse(ResponseCode.NO_LOGIN.getCode(), ResponseCode.NO_LOGIN.getDesc());
//
//        }

//        System.out.println("session : " + session);
//        System.out.println("session.getAttribute CURRENT_USER: " + session.getAttribute(Const.CURRENT_USER));
        if (null != session ) {
            ManagerVo managerVo = (ManagerVo) session.getAttribute(Const.CURRENT_USER);
            if (null != managerVo) {
                return true;
            }else {
                serverResponse = ServerResponse.errorResponse(ResponseCode.NO_LOGIN.getCode(), ResponseCode.NO_LOGIN.getDesc());
            }
        }else {
            serverResponse = ServerResponse.errorResponse(ResponseCode.NO_LOGIN.getCode(), ResponseCode.NO_LOGIN.getDesc());
        }


//        if (null == managerVo){


//        System.out.println("response.getWriter()");
            response.reset();
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Methods",
//                "ACL, CANCELUPLOAD, CHECKIN, CHECKOUT, COPY, DELETE, GET, HEAD, LOCK, MKCALENDAR, MKCOL, MOVE, OPTIONS, POST, PROPFIND, PROPPATCH, PUT, REPORT, SEARCH, UNCHECKOUT, UNLOCK, UPDATE, VERSION-CONTROL");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers",
//                "Origin, X-Requested-With, Content-Type, Accept, Key, Authorization");

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");



//        filterChain.doFilter(request, response);
//        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
////            System.out.println("SC_OK");
//            System.out.println(" response.setHeader SC_OK" + request.getMethod());
//            response.setStatus(HttpServletResponse.SC_OK);
//        }

            PrintWriter out = response.getWriter();

//            serverResponse = ServerResponse.errorResponse(ResponseCode.ERROR.getCode(), "拦截器拦截,用户未登录或无权限");

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(serverResponse);
//        Gson gson = new Gson();
            out.print(json);

            out.flush();
            out.close();

            return false;
//            System.out.println("Manager is null");
//        }else {
//
//        }

//        if (null != managerVo && (managerVo.getRole() == Const.SUPER_MANAGER || managerVo.getRole() == Const.NORMAL_MANAGER)){
//
////            System.out.println("Manager" + managerVo.getRole());
//            return true;
//        }
//        String jsessionid = CookieUtil.getToken(request);
//        ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();



//        Manager manager = (Manager) Const.sessionMap.get(jsessionid);


//        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
