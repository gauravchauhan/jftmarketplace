package com.jft.market.configuration;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component("customLogoutSuccessHandler")
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
		if (authentication != null && authentication.getDetails() != null) {
			try {
				httpServletRequest.getSession().invalidate();
				System.out.println("User Successfully Logout");
				//you can add more codes here when the user successfully logs out,
				//such as updating the database for last active.
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		//redirect to login
		httpServletResponse.sendRedirect("/market/login");
	}
}
