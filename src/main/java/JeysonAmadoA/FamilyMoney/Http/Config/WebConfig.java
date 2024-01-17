package JeysonAmadoA.FamilyMoney.Http.Config;

import JeysonAmadoA.FamilyMoney.Utilities.Security.EnvironmentValues;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        EnvironmentValues environmentValues = EnvironmentValues.getInstance();

        registry.addMapping("/**")
                .allowedOrigins(environmentValues.CLIENT_HOST())
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("Authorization", "authorization" ,"Content-Type")
                .allowCredentials(true)
                .exposedHeaders("Authorization");
    }
}
