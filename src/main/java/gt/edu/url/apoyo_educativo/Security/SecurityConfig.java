
package gt.edu.url.apoyo_educativo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpSessionSecurityContextRepository contextoAsistencias() {
        HttpSessionSecurityContextRepository repositorio =
                new HttpSessionSecurityContextRepository();
        repositorio.setSpringSecurityContextKey("CONTEXTO_ASISTENCIAS");
        return repositorio;
    }

    @Bean
    public HttpSessionSecurityContextRepository contextoActividades() {
        HttpSessionSecurityContextRepository repositorio =
                new HttpSessionSecurityContextRepository();
        repositorio.setSpringSecurityContextKey("CONTEXTO_ACTIVIDADES");
        return repositorio;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain seguridadActividades(
            HttpSecurity http,
            HttpSessionSecurityContextRepository contextoActividades
    ) throws Exception {

        http
            .securityMatcher(
                "/actividades",
                "/actividades/**",
                "/login-actividades"
            )
            .securityContext(contexto ->
                contexto.securityContextRepository(contextoActividades)
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login-actividades").permitAll()
                .requestMatchers(HttpMethod.GET, "/actividades/nuevo")
                    .hasAnyRole("ADMIN", "DOCENTE")
                .requestMatchers(HttpMethod.POST, "/actividades")
                    .hasAnyRole("ADMIN", "DOCENTE")
                .requestMatchers("/actividades/editar/**")
                    .hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/actividades/**")
                    .hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/login-actividades")
                .loginProcessingUrl("/login-actividades")
                .defaultSuccessUrl("/actividades", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/actividades/logout")
                .invalidateHttpSession(false)
                .logoutSuccessUrl("/login-actividades?salio")
                .permitAll()
            )
            .exceptionHandling(errores ->
                errores.accessDeniedPage("/acceso-denegado")
            );

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain seguridadAsistencias(
            HttpSecurity http,
            HttpSessionSecurityContextRepository contextoAsistencias
    ) throws Exception {

        http
            .securityContext(contexto ->
                contexto.securityContextRepository(contextoAsistencias)
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/login",
                    "/acceso-denegado",
                    "/error",
                    "/estilos.css"
                ).permitAll()
                .requestMatchers(HttpMethod.GET, "/asistencias/nuevo")
                    .hasAnyRole("ADMIN", "DOCENTE")
                .requestMatchers(HttpMethod.POST, "/asistencias")
                    .hasAnyRole("ADMIN", "DOCENTE")
                .requestMatchers("/asistencias/editar/**")
                    .hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/asistencias/**")
                    .hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/asistencias", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .invalidateHttpSession(false)
                .logoutSuccessUrl("/login?salio")
                .permitAll()
            )
            .exceptionHandling(errores ->
                errores.accessDeniedPage("/acceso-denegado")
            );

        return http.build();
    }
}
