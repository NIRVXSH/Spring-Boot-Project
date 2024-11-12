package com.example.demo.config.security.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import com.example.demo.config.security.service.UserDetailsServiceImpl;
import com.example.demo.entity.StpUser;
import com.example.demo.repository.StpUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenTokenFilter extends OncePerRequestFilter {

    @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

//   @Autowired
//   private RolePermissionService rolePermissionService;

//   @Autowired
//   private EmployeeRepository employeeRepository;

  @Autowired
  private StpUserRepository stpUserRepository;

  private static final Logger logger = LoggerFactory.getLogger(AuthenTokenFilter.class);


  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
        HttpServletRequest cachedRequest = new ContentCachingRequestWrapper(request);
    try {
      String jwt = parseJwt(cachedRequest);
      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        UsernamePasswordAuthenticationToken authentication = null;
        // CachedBodyHttpServletRequest wrappedRequest = new CachedBodyHttpServletRequest(request);
        // String body = new String(wrappedRequest.getInputStream()); // Here you can use the body as needed
        // String clone = StreamUtils.copyToString(httpRequest.getInputStream(), StandardCharsets.UTF_8);

        // ServletInputStream body = cachedRequest.getInputStream(); // Get cached request body
        // JsonNode node = new ObjectMapper().readTree(body);
        // HashMap jsonBody = objectMapper.readValue(cachedRequest.getInputStream(), HashMap.class);
        // if (request.getHeader("companyid") != null) {
        //   StpUser userinfo = jwtUtils.getUserDetail(jwt);
        //   Optional<StpEmployee> empInfo = employeeRepository.findByUserAndCompany(userinfo.getId(), request.getHeader("companyId"));
          
        //   List<String> roleAdd = rolePermissionService.getRolePermissionByEmpId(BaseRequest.builder().employeeId(empInfo.get().getId()).build());
          
          List<GrantedAuthority> roles = new ArrayList<>();
        //   for (String grantedAuthority : roleAdd) {
        //       roles.add(new SimpleGrantedAuthority(grantedAuthority));
        //   }
          // userDetails.getAuthorities().add(role);
          authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
              roles);
        // } else {
        //   authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
        //       userDetails.getAuthorities());
        // }

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (Exception e) {
      logger.error("Cannot set user authentication: {}", e);
    }

    filterChain.doFilter(cachedRequest, response);
  }

  private String parseJwt(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");

    return parseJwt(bearerToken);
  }

  public static String parseJwt(String bearerToken) {

    if (StringUtils.hasText(bearerToken)) {
      if (bearerToken.startsWith("Bearer ")) {
        return bearerToken.substring(7);
      } else {
        return bearerToken;
      }

    }
    return null;
  }

  

}
