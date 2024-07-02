//package isgandarli.zumrud.as2springbootloggingandsecurity.security;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
///**
// * Custom authentication entry point for JWT authentication.
// */
//@Component
//public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
//
//    /**
//     * Commences an authentication scheme, in this case, sends an unauthorized response.
//     *
//     * @param request       HTTP servlet request
//     * @param response      HTTP servlet response
//     * @param authException Authentication exception
//     * @throws IOException      If an I/O error occurs
//     * @throws ServletException If a servlet-specific error occurs
//     */
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
//    }
//}