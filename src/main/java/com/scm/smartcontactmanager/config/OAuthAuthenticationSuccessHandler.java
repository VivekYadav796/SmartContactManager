package com.scm.smartcontactmanager.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.smartcontactmanager.Entities.Providers;
import com.scm.smartcontactmanager.Entities.User;
import com.scm.smartcontactmanager.helper.AppConstants;
import com.scm.smartcontactmanager.repository.UserRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Autowired
    private UserRepo userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();        

        String email = user.getAttribute("email").toString();
        String name = user.getAttribute("name").toString();

        // save googlelogin data into database
        User user1 = new User();
        user1.setEmail(email);
        user1.setName(name);
        user1.setPassword("password");
        user1.setUserId(UUID.randomUUID().toString());
        user1.setProvider(Providers.GOOGLE);
        user1.setEnabled(true);
        user1.setEmailVerified(true);
        user1.setRoleList(List.of(AppConstants.ROLE_USER));

        User user2 = userRepo.findByEmail(email).orElse(null);
        if (user2 == null) {
            userRepo.save(user1);
        }

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
    }

}
