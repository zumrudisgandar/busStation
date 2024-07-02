//package isgandarli.zumrud.as2springbootloggingandsecurity.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.SignatureAlgorithm;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//import java.security.Key;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import java.util.Date;
//
///**
// * Utility class for generating and validating JWT tokens.
// */
//@Component
//@Slf4j
//public class JWTGenerator {
//
//    // Secret key used for signing JWT tokens
//    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//
//    /**
//     * Generates a JWT token based on the provided authentication details.
//     *
//     * @param authentication The authentication details
//     * @return The generated JWT token
//     */
//    public String generateToken(Authentication authentication) {
//        log.info("-----> INFO: Generating token for authentication: {}", authentication.getName());
//        String username = authentication.getName();
//        Date currentDate = new Date();
//        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);
//
//        String token = Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt( new Date())
//                .setExpiration(expireDate)
//                .signWith(key,SignatureAlgorithm.HS512)
//                .compact();
//        log.info("----->>> New token : {}", token);
//        System.out.println("----->>> New token :");
//        System.out.println(token);
//        return token;
//    }
//
//    /**
//     * Extracts the username from a JWT token.
//     *
//     * @param token The JWT token
//     * @return The username extracted from the token
//     */
//    public String getUsernameFromJWT(String token){
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//        return claims.getSubject();
//    }
//
//    /**
//     * Validates the provided JWT token.
//     *
//     * @param token The JWT token to validate
//     * @return True if the token is valid, false otherwise
//     * @throws AuthenticationCredentialsNotFoundException If the token is expired or incorrect
//     */
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder()
//                    .setSigningKey(key)
//                    .build()
//                    .parseClaimsJws(token);
//            return true;
//        } catch (Exception ex) {
//            throw new AuthenticationCredentialsNotFoundException("JWT was exprired or incorrect",ex.fillInStackTrace());
//        }
//    }
//}