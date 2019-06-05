package com.glqdlt.ex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @see <a href='https://www.baeldung.com/spring-security-5-oauth2-login'>https://www.baeldung.com/spring-security-5-oauth2-login</a>
 * @see <a href='https://gs.saro.me/dev?tn=520'>https://gs.saro.me/dev?tn=520</a>
 * @see <a href='http://blog.naver.com/PostView.nhn?blogId=sthwin&logNo=221268265713&parentCategoryNo=&categoryNo=49&viewDate=&isShowPopularPosts=true&from=search'>http://blog.naver.com/PostView.nhn?blogId=sthwin&logNo=221268265713&parentCategoryNo=&categoryNo=49&viewDate=&isShowPopularPosts=true&from=search</a>
 */
@EnableWebSecurity
@Configuration
@PropertySource("com.glqdlt.ex.oauth2-client.properties")
public class Oauth2ClientConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private Environment env;
    private static String CLIENT_PROPERTY_KEY
            = "spring.security.oauth2.client.registration.";

    private static String GOOGLE = "google";
    private static String FACEBOOK = "facebook";

    private ClientRegistration getRegistration(String client) {
        String clientId = env.getProperty(
                CLIENT_PROPERTY_KEY + client + ".client-id");

        if (clientId == null) {
            return null;
        }

        String clientSecret = env.getProperty(
                CLIENT_PROPERTY_KEY + client + ".client-secret");

        if (client.equals(GOOGLE)) {
            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
                    .clientId(clientId).clientSecret(clientSecret).build();
        }
        if (client.equals(FACEBOOK)) {
            return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
                    .clientId(clientId).clientSecret(clientSecret).build();
        }
        return null;
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        final List<String> clients = Arrays.asList(GOOGLE, FACEBOOK);
        List<ClientRegistration> registrations = clients.stream()
                .map(this::getRegistration)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new InMemoryClientRegistrationRepository(registrations);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login();
    }
}
