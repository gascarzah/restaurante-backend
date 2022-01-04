package com.gafahtec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

//Segunda Clase
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Autowired
    private ResourceServerTokenServices tokenServices;
	
	
    @Value("${security.jwt.resource-ids}")
    private String resourceIds;
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceIds).tokenServices(tokenServices);
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	  http
          .exceptionHandling().authenticationEntryPoint(new AuthException())
          .and()
          .requestMatchers()
          .and()
          .authorizeRequests()                  
          .antMatchers("**/v2/api-docs/**" ).permitAll()
          .antMatchers("**/v3/api-docs/**" ).permitAll()
          .antMatchers("**/cajas/**").authenticated()
          .antMatchers("**/categoria-insumos/**" ).authenticated()                
          .antMatchers("**/categoria-productos/**" ).authenticated()
          .antMatchers("**/clientes/**" ).authenticated()
          .antMatchers("**/compras/**" ).authenticated()
          .antMatchers("**/compras-detalle/**" ).authenticated()
          .antMatchers("**/destinos/**" ).authenticated()
          .antMatchers("**/empleados/**" ).authenticated()
          .antMatchers("**/estados/**" ).authenticated()
          .antMatchers("**/insumos/**" ).authenticated()
          .antMatchers("**/medidas/**" ).authenticated()
          .antMatchers("**/mesas/**" ).authenticated()
          .antMatchers("**/pedidos/**" ).authenticated()
          .antMatchers("**/pedidos-detalle/**" ).authenticated()
          .antMatchers("**/productos/**" ).authenticated()
          .antMatchers("**/productos-detalle/**" ).authenticated()
          .antMatchers("**/proveedores/**" ).authenticated()
          .antMatchers("**/tipoRecibos/**" ).authenticated()
          .antMatchers("**/unidades/**" ).authenticated()
          .antMatchers("**/ventas/**" ).authenticated()
          .antMatchers("**/menus/**" ).authenticated()
          .antMatchers("**/tokens/anular/**" ).permitAll()
          .antMatchers("**/tokens/**" ).authenticated()    
          ;
                
    }    

}
