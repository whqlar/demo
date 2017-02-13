package com.dianping.study.web.servlet;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by wu on 17/2/13.
 */
public class MyServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyServlet.class);


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("myServlet start");
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + " = " + cookie.getValue());
        }

        Cookie cookie = buildCookie();
        resp.addCookie(cookie);

        HttpSession session = req.getSession();
        session.setAttribute("sessionKey", "sessionValue");
        System.out.println(session.getId()); //JSESSIONID
        System.out.println(session.getAttribute("sessionKey"));

    }

    private Cookie buildCookie() {
        Cookie cookie = new Cookie("myCookie", "xxxxCookie");
        cookie.setComment("cookie测试");
        cookie.setDomain("localhost");
        cookie.setMaxAge(20);
        return cookie;
    }
}
