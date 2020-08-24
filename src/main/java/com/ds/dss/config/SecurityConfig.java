package com.ds.dss.config;

import com.ds.dss.componet.JwtAuthenticationTokenFilter;
import com.ds.dss.componet.RestAuthenticationEntryPoint;
import com.ds.dss.componet.RestfulAccessDeniedHandler;
import com.ds.dss.mbg.model.AdminUserDetails;
import com.ds.dss.mbg.model.DsiAdmin;
import com.ds.dss.mbg.model.DsiPermission;
import com.ds.dss.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdminService adminService;


    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;


    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        //csrf disable
        ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) ((httpSecurity.csrf().disable()).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()).authorizeRequests().antMatchers(HttpMethod.GET, new String[]{"/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js", "/swagger-resources/**", "/v2/api-docs/**", "/webjars/springfox-swagger-ui/**"})).permitAll().antMatchers(HttpMethod.OPTIONS)).permitAll().antMatchers(new String[]{"/v1/users/login"})).permitAll().antMatchers(new String[]{"/v1/users/**", "/v1/synch/**"})).permitAll().anyRequest()).authenticated();//,"/v1/omm/**"
        httpSecurity.headers().cacheControl();
        httpSecurity.addFilterBefore(this.jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.exceptionHandling().accessDeniedHandler(this.restfulAccessDeniedHandler).authenticationEntryPoint(this.restAuthenticationEntryPoint);
    }


    /**
     * @param auth
     * @throws Exception
     * @Desc 改造
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        boolean domainSwtich = false;
        if (domainSwtich) {
            //init get dictiondata
            //from db
            String seachBase = "";
            String[] userDnPattern = {};
            String groupSearchBase = "";
            String providerUrl = "";
            String providerPassword = "";
            auth.ldapAuthentication()
                    .userDnPatterns(userDnPattern) //"uid={0},ou=people" 设置用户区分名模式
                    .groupSearchBase(groupSearchBase) //"ou=groups" 设置搜索组织
                    .contextSource()
                    .url(providerUrl) //ldap链接
                    .and()
                    .passwordCompare()
                    .passwordEncoder(new BCryptPasswordEncoder())
                    .passwordAttribute(providerPassword);//设置密码
        } else {
            auth.userDetailsService(this.userDetailsService()).passwordEncoder(this.passwordEncoder());
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            final DsiAdmin admin = this.adminService.getAdminByUsername(username);
            if (admin != null) {
                final List<DsiPermission> permissionList = this.adminService.getPermissionList(admin.getId());
                return (UserDetails) new AdminUserDetails(admin, permissionList);
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
