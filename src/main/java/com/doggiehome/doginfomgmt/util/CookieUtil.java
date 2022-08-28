package com.doggiehome.doginfomgmt.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class CookieUtil {

    @Value(value = "${cookie.name}")
    private String name;

    @Value(value = "${cookie.domain}")
    private String domain;

    private static String static_name;

    private static String static_domain;

    @Value(value = "${cookie.name}")
    private void setNameStatic(){
        CookieUtil.static_name = name;
    }

    @Value(value = "${cookie.domain}")
    public void setDomainStatic(){
        CookieUtil.static_domain = domain;
    }




    public static void setToken(HttpServletResponse response, String token) {
//        String s = CookieUtil.static_name;
        Cookie ck = new Cookie(CookieUtil.static_name, token);
        ck.setDomain(CookieUtil.static_domain);
        ck.setPath("/");
        ck.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(ck);

    }

    public static String getToken(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
//        cookies[0].getName();
//        Cookie cookies1 = cookies[0];
//        Stream<Cookie> cookie2 = Arrays.stream(cookies).filter(c -> c.getName() == CookieUtil.static_name);

        if (null != cookies){
            System.out.println(cookies[0].getName());
            System.out.println(cookies[0].getValue());
            System.out.println(cookies[0].getDomain());
            System.out.println(cookies[0].getMaxAge());
            List<Cookie> cookie = Arrays.stream(cookies).filter(c -> c.getName().equals(CookieUtil.static_name)).collect(Collectors.toList());


            System.out.println("/");
        }
//        Cookie cookie = Arrays.stream(cookies).filter(c -> c.getName() == CookieUtil.static_name);
//        return cookie.getValue();
        return "";
    }
}
