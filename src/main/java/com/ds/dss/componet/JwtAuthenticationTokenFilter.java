package com.ds.dss.componet;

import com.ds.dss.common.utils.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.*;
import org.springframework.web.filter.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{
    private static final Logger LOGGER;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    
    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws ServletException, IOException {
        final String authHeader = request.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
            final String authToken = authHeader.substring(this.tokenHead.length());
            final String username = this.jwtTokenUtil.getUserNameFromToken(authToken);
            JwtAuthenticationTokenFilter.LOGGER.info("checking username:{}", (Object)username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (this.jwtTokenUtil.validateToken(authToken, userDetails)) {
                    final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken((Object)userDetails, (Object)null, userDetails.getAuthorities());
                    authentication.setDetails((Object)new WebAuthenticationDetailsSource().buildDetails(request));
                    JwtAuthenticationTokenFilter.LOGGER.info("authenticated user:{}", (Object)username);
                    SecurityContextHolder.getContext().setAuthentication((Authentication)authentication);
                }
            }
        }
        chain.doFilter((ServletRequest)request, (ServletResponse)response);
    }
    
    static {
        LOGGER = LoggerFactory.getLogger((Class)JwtAuthenticationTokenFilter.class);
    }
}
