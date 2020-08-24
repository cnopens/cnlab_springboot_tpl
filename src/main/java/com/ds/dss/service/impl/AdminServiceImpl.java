package com.ds.dss.service.impl;

import com.ds.dss.common.api.Result;
import com.ds.dss.common.api.ResultPage;
import com.ds.dss.common.utils.JwtTokenUtil;
import com.ds.dss.mbg.mapper.DsiAdminMapper;
import com.ds.dss.mbg.mapper.DsiAdminRoleRelationDao;
import com.ds.dss.mbg.model.DsiAdmin;
import com.ds.dss.mbg.model.DsiAdminExample;
import com.ds.dss.mbg.model.DsiPermission;
import com.ds.dss.service.AdminService;

import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private static final Logger LOGGER;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private DsiAdminMapper adminMapper;
    @Autowired
    private DsiAdminRoleRelationDao adminRoleRelationDao;

    static {
        LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);
    }

    @Override
    public DsiAdmin getAdminByUsername(final String username) {
        final DsiAdminExample example = new DsiAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        final List<DsiAdmin> adminList = this.adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public DsiAdmin register(final DsiAdmin dsiAdminParam) {
        final DsiAdmin umsAdmin = new DsiAdmin();
        BeanUtils.copyProperties((Object) dsiAdminParam, (Object) umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1L);
        final DsiAdminExample example = new DsiAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        final List<DsiAdmin> umsAdminList = this.adminMapper.selectByExample(example);
        if (umsAdminList.size() > 0) {
            return null;
        }
        final String encodePassword = this.passwordEncoder.encode((CharSequence) umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        this.adminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    /***
     * 配置验证
     * @param username
     * @param password
     * @return
     */
    @Override
    public String login(final String username, final String password) {
        String token = null;
        try {
            final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (!this.passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken((Object) userDetails, (Object) null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = this.jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            AdminServiceImpl.LOGGER.warn("登录异常:{}", (Object) e.getMessage());
        }
        return token;
    }

    /**
     * 用户基于老密码修改自己的密码
     *
     * @param userID
     * @param oriPassword
     * @param newPassword
     * @return
     */
    @Override
    public Result modifyPassword(BigDecimal userID, String oriPassword, String newPassword) {
        DsiAdmin dsiAdmin = this.adminMapper.selectByPrimaryKey(userID);
        if (!this.passwordEncoder.matches((CharSequence) oriPassword, dsiAdmin.getPassword())) {
            // 原密码不正确
            return Result.failed("原密码不正确");
        }

        int result = this.adminMapper.resetPasswordByPrimaryKey(userID, this.passwordEncoder.encode((CharSequence) newPassword));
        if (result > 0) {
            return Result.success("ok", "更改密码成功");
        } else {
            return Result.failed("修改密码失败,请稍后再试");
        }
    }

    @Override
    public List<DsiPermission> getPermissionList(final BigDecimal adminId) {
        return this.adminRoleRelationDao.getPermissionList(adminId);
    }

    @Override
    public ResultPage<DsiAdmin> list(final Integer page, final Integer pageSize, final String name) {
        final DsiAdminExample query = new DsiAdminExample();
        if (StringUtils.isNotEmpty(name)) {
            query.createCriteria().andUsernameLike("%" + name + "%");
        }
        final List<DsiAdmin> list = this.adminMapper.selectByExample(query);
        final long count = this.adminMapper.countByExample(query);
        final ResultPage apiPage = new ResultPage();
        apiPage.setList(list);
        apiPage.setPageNum(page);
        apiPage.setPageSize(pageSize);
        apiPage.setTotal(count);
        return (ResultPage<DsiAdmin>) apiPage;
    }

    @Override
    public List<DsiAdmin> getAllAdminUser() {
        return this.adminMapper.getAllAdminUser();
    }

    /**
     * 更改管理员用户的邮件通知接收状态
     */
    @Override
    public void updateAdminUserMailNotice(final BigDecimal userID, final byte status) {
        this.adminMapper.updateAdminUserMailNotice(userID, status);
    }

    @Override
    public int update(final DsiAdmin dsiAdminParam) {
        return this.adminMapper.updateByPrimaryKey(dsiAdminParam);
    }

    @Override
    public int delete(final BigDecimal id) {
        return this.adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int changePasss(final DsiAdmin dsiAdminParam, final DsiAdminExample dsiAdminExample) {
        return this.adminMapper.updateByExampleSelective(dsiAdminParam, dsiAdminExample);
    }


}
