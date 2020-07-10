package mk.ukim.finki.emt.lab.carrental.rentmanagement.port.client;

import mk.ukim.finki.emt.lab.carrental.rentmanagement.application.UserCatalog;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.User;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class UserCatalogClient implements UserCatalog {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCatalogClient.class);

    private final RestTemplate restTemplate;
    private final String serverUrl;

    UserCatalogClient(@Value("${app.user-management.url}") String serverUrl,
                         @Value("${app.user-management.connect-timeout-ms}") int connectTimeout,
                         @Value("${app.user-management.read-timeout-ms}") int readTimeout) {
        this.serverUrl = serverUrl;
        restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        // Never ever do a remote call without a finite timeout!
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(serverUrl);
    }

    @Override
    public List<User> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/users").build().toUri(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<User>>() {
                    }).getBody();
        } catch (Exception ex) {
            LOGGER.error("Error retrieving users", ex);
            return Collections.emptyList();
        }
    }

    @Override
    public List<User> findAllAvailable() {
        try {
            return restTemplate.exchange(uri().path("/api/users/available").build().toUri(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<User>>() {
                    }).getBody();
        } catch (Exception ex) {
            LOGGER.error("Error retrieving users", ex);
            return Collections.emptyList();
        }
    }

    @Override
    public User findById(UserId id) {
        try {
            return restTemplate.exchange(uri().path("/api/users/"+id.getId()).build().toUri(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<User>() {
                    }).getBody();
        } catch (Exception ex) {
            LOGGER.error("Error retrieving user by id", ex);
            return null;
        }
    }
}
