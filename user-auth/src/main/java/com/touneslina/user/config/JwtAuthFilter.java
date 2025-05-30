package com.touneslina.user.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import org.springframework.security.core.userdetails.UserDetails;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, // intercept the request and extract data from it
            @NonNull HttpServletResponse response, // create a response with new data
            @NonNull FilterChain filterChain // chain of responsibility DP, it contains a list of other filters that we need within the chain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization"); //this header contains the JWT "Bearer" token
        final String jwt;
        final String email;
        // check JWT Token
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            //the Bearer Token starts with the keyword "Bearer "
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7); //len("Bearer ")=7
        //extract the mail from the JWT token, we need a class that can manipulate the JWT Token, the JwtService
        email = jwtService.extractMail(jwt);
        // check if email is null or the user is already authenticated
        if (email!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            // we need to get the user from the DB
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
            if (jwtService.validateToken(jwt, userDetails)) {
                // if the token is valid, we have to update the security context
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null, // we don't have credentials, that's why it's null
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response); // calling the next filter
    }
}
