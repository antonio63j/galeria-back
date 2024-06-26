package com.afl.galeria.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		
                .antMatchers(HttpMethod.GET, "/images/**").permitAll()
                .antMatchers(HttpMethod.GET, "/mail/editablehtml/images/**").permitAll()
                .antMatchers(HttpMethod.GET, "/css/**").permitAll()
                .antMatchers(HttpMethod.GET, "/webjars/**").permitAll()

				.antMatchers(HttpMethod.POST, "/api/usuario/registro").permitAll()
				.antMatchers(HttpMethod.GET, "/api/usuario/registro/confirmacion").permitAll()
				.antMatchers(HttpMethod.GET, "/api/usuarios").permitAll()
				.antMatchers(HttpMethod.GET, "/api/cuentaactivada").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/usuario/resetpwd").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/usuario/changepwd").permitAll()
				.antMatchers(HttpMethod.POST, "/api/usuario/emailCliente").permitAll()

				.antMatchers(HttpMethod.GET, "/api/politica-cookies").permitAll()

				// .antMatchers(HttpMethod.GET, "/api/adminindex").hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/api/adminindex").permitAll()
				
				.antMatchers(HttpMethod.GET, "/api/empresa/uploads/img/**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/empresa/uploads/img/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/empresa", "/api/empresa/sliders").permitAll()
				
//				.antMatchers(HttpMethod.DELETE,  "/api/tipo/{id}").permitAll()
//				.antMatchers(HttpMethod.GET,  "/api/tipoobra/uploads/img/**").permitAll()
//				.antMatchers(HttpMethod.POST, "/api/tipoobra").permitAll()
//				.antMatchers(HttpMethod.PUT,  "/api/tipoobra/update").permitAll()
//				.antMatchers(HttpMethod.GET,  "/api/tipoobra", "/api/tipoobra/list").permitAll()
//				.antMatchers(HttpMethod.PUT,  "/api/tipoobra/**").permitAll()
				// .antMatchers(HttpMethod.DELETE,  "/api/tipoobra/{id}").permitAll()
				.antMatchers("/api/tipoobra/**").permitAll()
				
				.antMatchers("/api/lote/**").permitAll()
				.antMatchers("/api/sugerencia/**").permitAll()
				.antMatchers("/api/lotesugerencia/**").permitAll()
				.antMatchers("/api/carrito/**").permitAll()
				.antMatchers("/api/pedido/**").permitAll()

				.antMatchers(HttpMethod.GET, "/topic/**", "/wsocket1/**").permitAll()

				
//				.antMatchers(HttpMethod.GET, "/api/empresa", "/api/empresa/uploads/img/**", "/images/**").permitAll()
//				.antMatchers(HttpMethod.GET, "/api/empresa/uploads/img/admin/**").permitAll()
//				.antMatchers(HttpMethod.GET, "/api/empresa/uploads/img/sliders/**").permitAll()
//				.antMatchers(HttpMethod.POST, "/api/empresa/uploads/img/sliders", "/api/empresa/uploads/img/sliders/**").permitAll()
//				.antMatchers(HttpMethod.GET, "/api/empresa/sliders").permitAll()
//				.antMatchers(HttpMethod.POST, "/api/empresa/slider", "/api/empresa/img/**").permitAll()
//				.antMatchers(HttpMethod.PUT, "/api/empresa/slider").permitAll()
//				.antMatchers(HttpMethod.PUT, "/api/empresa").permitAll()
//				.antMatchers(HttpMethod.POST, "/api/empresa").permitAll()
				
				/*
				 * .antMatchers(HttpMethod.GET, "/api/herramientas",
				 * "/api/herramientas/page/tipo","/api/herramientas/filtro").permitAll()
				 * .antMatchers(HttpMethod.PUT, "/api/herramienta/update").permitAll()
				 * .antMatchers(HttpMethod.POST, "/api/herramientas").permitAll()
				 * .antMatchers(HttpMethod.DELETE, "/api/herramienta/{id}").permitAll()
				 * 
				 * .antMatchers(HttpMethod.GET, "/api/proyectos/page", "/api/proyectos/cliente",
				 * "/api/proyectos/filtro").permitAll() .antMatchers(HttpMethod.PUT,
				 * "/api/proyecto/{id}").permitAll() .antMatchers(HttpMethod.POST,
				 * "/api/proyectos").permitAll() .antMatchers(HttpMethod.DELETE,
				 * "/api/proyecto/{id}").permitAll()
				 */
				
				/*
				 * .antMatchers(HttpMethod.GET, "/api/cursos").hasAnyRole("USER", "ADMIN")
				 * .antMatchers(HttpMethod.GET, "/api/cursos/page").hasRole("USER")
				 * .antMatchers(HttpMethod.GET, "/api/cursos/filtro").hasRole("ADMIN")
				 */
				
				/*
				 * .antMatchers(HttpMethod.GET, "/api/cursos").permitAll()
				 * .antMatchers(HttpMethod.GET, "/api/cursos/page").permitAll()
				 * .antMatchers(HttpMethod.GET, "/api/cursos/filtro").permitAll()
				 * .antMatchers(HttpMethod.POST, "/api/cursos").permitAll()
				 * .antMatchers(HttpMethod.DELETE, "/api/curso/{id}").permitAll()
				 * .antMatchers(HttpMethod.PUT, "/api/curso/update").permitAll()
				 * 
				 */				

				/*
				 * .antMatchers(HttpMethod.GET, "/api/clientes/page").permitAll()
				 * .antMatchers(HttpMethod.GET, "/api/clientes/filtro").permitAll()
				 * .antMatchers(HttpMethod.PUT, "/api/cliente/{id}").permitAll()
				 * .antMatchers(HttpMethod.PUT, "/api/cliente/update").permitAll()
				 * .antMatchers(HttpMethod.DELETE, "/api/cliente/{id}").permitAll()
				 * .antMatchers(HttpMethod.GET, "/api/cliente/uploads/img/**").permitAll()
				 * 
				 * .antMatchers(HttpMethod.GET, "/api/download/aflcv-pdf").permitAll()
				 */
				
				/*
				 * Pasamos la seguridad a SpringSecurityConfig incorporando la
				 * anotacion @EnableGlobalMethodSecurity y en el controlador, una anotación por
				 * cada url que se necesite controlar, esta segunda opción parece más sencilla e
				 * intuitiva
				 *
				 * .antMatchers(HttpMethod.GET, "/api/clientes/{id}").hasAnyRole("USER",
				 * "ADMIN") .antMatchers(HttpMethod.POST,
				 * "/api/clientes/upload").hasAnyRole("USER", "ADMIN")
				 * .antMatchers(HttpMethod.POST, "/api/clientes").hasRole("ADMIN")
				 * .antMatchers("/api/clientes/**").hasRole("ADMIN")
				 */
				.anyRequest().authenticated().and().cors().configurationSource(corsConfigurationSource());
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:4321", "*"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return source;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(
				new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

}
