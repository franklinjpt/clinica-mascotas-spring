package edu.unimagdalena.demo.jwt.security;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import edu.unimagdalena.demo.jwt.services.UserDetailsServiceImpl;

import org.springframework.security.core.userdetails.UserDetails;



import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	@Autowired
    private UserDetailsServiceImpl userDetailsService;
	
	
	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(Constants.HEADER_AUTHORIZACION_KEY);
		if (header == null || !header.startsWith(Constants.TOKEN_BEARER_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(Constants.HEADER_AUTHORIZACION_KEY);
		if (token != null) {
			// Se procesa el token y se recupera el usuario.
			String username = Jwts.parser()
						.setSigningKey(Constants.SUPER_SECRET_KEY)
						.parseClaimsJws(token.replace(Constants.TOKEN_BEARER_PREFIX, ""))
						.getBody()
						.getSubject();
			
			/*System.out.println("username: "+ username);
			
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			if (userDetails != null) {
				return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			}
			return null;*/
			if (username != null) {
				return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
			}
			return null;
		}
		return null;
	}
}
