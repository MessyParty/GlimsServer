package com.glimps.glimpsserver.session.application;

import com.glimps.glimpsserver.common.jwt.JwtDto;
import com.glimps.glimpsserver.common.jwt.JwtUtil;
import com.glimps.glimpsserver.user.application.UserService;
import com.glimps.glimpsserver.user.domain.RoleType;
import com.glimps.glimpsserver.user.domain.User;
import com.glimps.glimpsserver.user.domain.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
	private static final Long ID = 3L;
	private static final String EMAIL = "user12345@naver.com";
	private static final String NAME = "장광남";
	private static final UserType KAKAO = UserType.KAKAO;
	private static final RoleType ROLE = RoleType.USER;
	private static final User USER = User.builder()
		.id(ID)
		.email(EMAIL)
		.nickname(NAME)
		.role(ROLE)
		.build();

	private static final JwtDto JWT_DTO = JwtDto.builder()
		.grantType("Bearer")
		.accessToken("eyJ0eXAiOiJqd3...")
		.refreshToken("eyJ0eXAiOiJd1r...")
		.accessTokenExpireTime(new Date())
		.refreshTokenExpireTime(new Date())
		.build();
	private OAuth2User oAuth2User = new DefaultOAuth2User(null, new HashMap<>() {{
		put("name", NAME);
		put("email", EMAIL);
		put("userType", KAKAO.toString().toLowerCase());
	}}, "email");
	@Mock
	private UserService userService;

	@Mock
	private JwtUtil jwtUtil;

	@InjectMocks
	private AuthenticationService authenticationService;

	@Test
	@DisplayName("신규 유저 OAuthLogin")
	void whenNotSignedUserLogin() throws Exception {
		//given
		given(jwtUtil.createJwtDto(EMAIL, ROLE)).willReturn(JWT_DTO);
		given(userService.getOptionalUserByEmail(EMAIL)).willReturn(Optional.empty());
		given(userService.registerUser(any())).willReturn(ID);
		given(userService.findById(ID)).willReturn(USER);

		//when
		JwtDto jwtDto = authenticationService.oauthLogin(oAuth2User);

		//then
		assertThat(jwtDto).isEqualTo(JWT_DTO);
		verify(userService, times(1)).registerUser(any());
		verify(jwtUtil, times(1)).createJwtDto(EMAIL, ROLE);
		verify(userService, times(1)).updateRefreshToken(ID, jwtDto.getRefreshToken(),
			jwtDto.getRefreshTokenExpireTime());
	}

	@Test
	@DisplayName("기존 유저 OAuthLogin")
	void whenSignedUserLogin() throws Exception {
		//given
		given(jwtUtil.createJwtDto(EMAIL, ROLE)).willReturn(JWT_DTO);
		given(userService.getOptionalUserByEmail(EMAIL)).willReturn(Optional.of(USER));

		//when
		JwtDto jwtDto = authenticationService.oauthLogin(oAuth2User);

		//then
		assertThat(jwtDto).isEqualTo(JWT_DTO);
		verify(userService, times(0)).registerUser(any());
		verify(jwtUtil, times(1)).createJwtDto(EMAIL, ROLE);
		verify(userService, times(1)).updateRefreshToken(ID, jwtDto.getRefreshToken(),
			jwtDto.getRefreshTokenExpireTime());
	}

}
