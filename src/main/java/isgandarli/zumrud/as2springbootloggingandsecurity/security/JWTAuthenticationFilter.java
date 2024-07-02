//package isgandarli.zumrud.as2springbootloggingandsecurity.security;
//
//import isgandarli.zumrud.as2springbootloggingandsecurity.repo.UserRepository;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//
///**
// * Filter responsible for JWT authentication.
// */
//public class JWTAuthenticationFilter extends OncePerRequestFilter {
//
//    /**
//     * The JWT token generator.
//     */
//    @Autowired
//    private JWTGenerator tokenGenerator;
//
//    /**
//     * The custom user details service.
//     */
//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    /**
//     * Filters incoming requests for JWT authentication.
//     *
//     * @param request     HTTP servlet request
//     * @param response    HTTP servlet response
//     * @param filterChain Filter chain
//     * @throws ServletException If a servlet-specific error occurs
//     * @throws IOException      If an I/O error occurs
//     */
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        String token = getJWTFromRequest(request);
//        if(StringUtils.hasText(token) && tokenGenerator.validateToken(token)) {
//            //String username = tokenGenerator.getUsernameFromJWT(token);
//            UserDetails userDetails = (UserDetails) customUserDetailsService.userDetailsService(userRepository);
//            UsernamePasswordAuthenticationToken authenticationToken
//                    = new UsernamePasswordAuthenticationToken(userDetails, null,
//                    userDetails.getAuthorities());
//            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    /**
//     * Extracts JWT token from the request.
//     *
//     * @param request HTTP servlet request
//     * @return JWT token
//     */
//    private String getJWTFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7, bearerToken.length());
//        }
//        return null;
//    }
//}