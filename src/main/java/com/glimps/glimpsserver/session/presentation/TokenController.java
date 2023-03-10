package com.glimps.glimpsserver.session.presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glimps.glimpsserver.session.application.AuthenticationService;
import com.glimps.glimpsserver.session.dto.AccessTokenDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class TokenController {

	private final AuthenticationService authenticationService;

	@PostMapping("/access-token/issue")
	public AccessTokenDto logout(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");

		AccessTokenDto accessTokenDto = authenticationService.issueAccessToken(authorizationHeader);

		return accessTokenDto;
	}
}
