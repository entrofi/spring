

package net.entrofi.commons.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public final class RequestHelper {

    public static final String ORIGINAL_IP_HEADER = "X-Forwarded-For";

    public static final String ANONYMOUS_USER = "anonymousUser";

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestHelper.class);

    private RequestHelper() { }

    public static UserDetails getCurrentUserDetails() {
        UserDetails userDetails = null;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null
                    || !authentication.isAuthenticated()
                    ||  ANONYMOUS_USER.equals(authentication.getName())) {
                return null;
            }

            userDetails = (UserDetails) authentication.getPrincipal();
        } catch (Exception e) {
            LOGGER.warn("Error getting current user details");
        }
        return userDetails;
    }

    public static Long getCurrentUserId() {
        Long userId = null;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            userId = (Long) PropertyUtils.getProperty(authentication.getPrincipal(), "user.id");
        } catch (Exception e) {
            LOGGER.warn("Error getting current user id");
        }
        return userId;
    }

    public static String getCurrentUserIpAddress() {
        try {
            RequestAttributes attributes = RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();
            // Use this when the server is behind a load balancer
            String forwardedIp = request.getHeader(ORIGINAL_IP_HEADER);
            return forwardedIp != null ? forwardedIp : request.getRemoteAddr();
        } catch (Exception e) {
            LOGGER.warn("Error getting current user ip");
            return null;
        }
    }
}
