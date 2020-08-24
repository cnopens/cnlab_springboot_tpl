package com.ds.dss.componet;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.ds.dss.common.api.Result;
import com.ds.dss.common.api.ResultCode;
import com.ds.dss.mbg.model.DsiSysLog;
import com.ds.dss.service.SysLogService;
import com.ds.dss.common.utils.IpAdrressUtil;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;


@Aspect
@Component
@Order(1)
public class LogAopAspect {
    Logger log = LoggerFactory.getLogger((Class) LogAopAspect.class);
    @Autowired
    private SysLogService sysLogService;


    @Pointcut("@annotation(com.ds.iaas.rsmgr.common.annotation.LogAnno)")
    public void logPoint() {
        System.out.println("方法调用进入切入点....");
    }

    @Before("logPoint()")
    public void doBefore(final JoinPoint joinPoint) throws Throwable {
    }

    @After("logPoint()")
    public void doAfter() {
    }

//    @AfterReturning(value = "logPoint()", returning = "ret")
//    public void doAfterReturning(final Object ret) throws Throwable {
//    }

    @Around("logPoint()")
    public Object aroundAdvice(final ProceedingJoinPoint joinPoint) {
        final DsiSysLog sysLog = new DsiSysLog();
        final long startTime = System.currentTimeMillis();

        final ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        final HttpServletRequest request = attributes.getRequest();

        final Signature signature = joinPoint.getSignature();
        final MethodSignature methodSignature = (MethodSignature) signature;
        final Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(ApiOperation.class)) {
            final ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            sysLog.setPeration(apiOperation.value());
        }
        final long endTime = System.currentTimeMillis();
        final String urlStr = request.getRequestURL().toString();

        sysLog.setUri(urlStr);//
        sysLog.setUsername(request.getRemoteUser());
        sysLog.setMethod(request.getMethod());
        Object reqParams = this.getParameter(method, joinPoint.getArgs());

        sysLog.setParams(JSONObject.toJSONString(reqParams));

        sysLog.setSpendtime((int) (endTime - startTime));
        sysLog.setCreateDate(new Date());
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            sysLog.setUsername(authentication.getName());
        }
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
            String resultStr = (proceed == null) ? "" : JSONObject.toJSONString(proceed);
            sysLog.setOperateResult(resultStr);
            sysLog.setOperateResult("请求正常");
        } catch (Throwable e) {
            Result.failed("请求失败");
            sysLog.setOperateResult("请求失败");
            sysLog.setAbnormity(e.toString());
        }
        sysLog.setIp(IpAdrressUtil.getIpAdrress(request));
        this.log.info("{}", (Object) JSONUtil.parse((Object) sysLog));

        boolean switchLog = true;
        if (switchLog) {
            int count = this.sysLogService.inertSyslogSelective(sysLog);
            log.info("log.....====================" + count);
        }
        if (proceed == null) {
            return ResultCode.FAILED;
        }
        return proceed;
    }

    private Object getParameter(final Method method, final Object[] args) {
        final List<Object> argList = new ArrayList<Object>();
        final Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; ++i) {
            final RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            final RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                final Map<String, Object> map = new HashMap<String, Object>();
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty((Object) requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.size() == 0) {
            return null;
        }
        if (argList.size() == 1) {
            return argList.get(0);
        }
        return argList;
    }
}
