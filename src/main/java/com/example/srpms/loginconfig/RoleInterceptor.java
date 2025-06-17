package com.example.srpms.loginconfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class RoleInterceptor implements HandlerInterceptor {
    private static final Map<String, Set<String>> roleAccessMap = new HashMap<>();

    static {
        roleAccessMap.put("ADMIN", Set.of("/project/**", "/keyword-types/**", "/activity-area-types/**",
                "/home/**", "/project-type/**","/lecturer-affiliation/**","/lecturer-title/**",
                "/lecturer-type/**","/machinery-type/**","/personnel-type/**","/project-application/**",
                "/project-continue/**","/project-report-type/**","/technology-readiness-level-type/**","/authentication/**"));
        roleAccessMap.put("LECTURER", Set.of("/project/**", "/home/**","/project-continue/**","/authentication/**"));
        roleAccessMap.put("COMMITTEE", Set.of("/project-continue/**" ,"/project/**", "/home/**","/authentication/**"));
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("role") == null) {
            response.sendRedirect("/authentication/login");
            return false;
        }

        String role = (String) session.getAttribute("role");
        String requestPath = request.getRequestURI();

        Set<String> allowedPaths = roleAccessMap.getOrDefault(role.toUpperCase(), Collections.emptySet());

        boolean authorized = allowedPaths.stream().anyMatch(pattern -> requestPath.matches(convertPatternToRegex(pattern)));

        if (!authorized) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource.");
            return false;
        }

        return true;
    }
    private String convertPatternToRegex(String pattern) {
        return pattern.replace("**", ".*");
    }
}
