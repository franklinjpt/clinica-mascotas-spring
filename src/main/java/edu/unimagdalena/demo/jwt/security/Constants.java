package edu.unimagdalena.demo.jwt.security;

public class Constants {

	// Spring Security

	public static final String LOGIN_URL = "/login";
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer";

	// JWT

	public static final String ISSUER_INFO = "https://isis.unimagdalena.edu.co/";
	public static final String SUPER_SECRET_KEY = "DZfn448LjP8LuDE3#GwQm=+*q7pqk&EET+xs&W&=B94r4g!PXpD3NH@@=69!VT-p8?GU@hFrk!K6^Vhe2%pqA$$&^$A$";
	public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day

}
