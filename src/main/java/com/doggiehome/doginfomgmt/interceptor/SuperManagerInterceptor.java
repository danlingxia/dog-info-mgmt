package com.doggiehome.doginfomgmt.interceptor;

import com.doggiehome.doginfomgmt.common.Const;
import com.doggiehome.doginfomgmt.common.ResponseCode;
import com.doggiehome.doginfomgmt.common.ServerResponse;
import com.doggiehome.doginfomgmt.pojo.vo.ManagerVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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
public class SuperManagerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("SuperManagerInterceptor");
//        if (request.getMethod().equals("OPTIONS")){
//            return true;
//        }

        ServerResponse serverResponse;
        //获得cookie中jsessionid
        //jsessionid查看服务器中该session存不存在，存在则取出里面的用户数据放入请求
        HttpSession session = request.getSession(false);

        if (null != session){
            ManagerVo managerVo = (ManagerVo) session.getAttribute(Const.CURRENT_USER);

            if (null != managerVo ){
                if (managerVo.getRole() == Const.SUPER_MANAGER){
                    return true;
                }else {
                    serverResponse = ServerResponse.errorResponse(ResponseCode.NO_PERMISSION.getCode(), ResponseCode.NO_PERMISSION.getDesc());

                }


            }else {
                serverResponse = ServerResponse.errorResponse(ResponseCode.NO_LOGIN.getCode(), ResponseCode.NO_LOGIN.getDesc());

            }
        }else {
            serverResponse = ServerResponse.errorResponse(ResponseCode.NO_LOGIN.getCode(), ResponseCode.NO_LOGIN.getDesc());

        }



//        String jsessionid = CookieUtil.getToken(request);
//        ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();




//        Manager manager = (Manager) Const.sessionMap.get(jsessionid);


        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter out = response.getWriter();

//        if (null == managerVo){
//
//        }else {
//
//        }

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(serverResponse);
//        Gson gson = new Gson();
        out.print(json);

        out.flush();
        out.close();

        return false;
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
