package com.ds.dss.componet;

import cn.hutool.json.*;
import com.ds.dss.common.api.Result;
import com.ds.dss.common.api.*;
import org.springframework.security.access.*;
import org.springframework.security.web.access.*;
import org.springframework.stereotype.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    public void handle(final HttpServletRequest request, final HttpServletResponse response, final AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse((Object) Result.forbidden(e.getMessage())));
        response.getWriter().flush();
    }
}
