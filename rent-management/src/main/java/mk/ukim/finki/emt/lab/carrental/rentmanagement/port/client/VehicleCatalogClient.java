package mk.ukim.finki.emt.lab.carrental.rentmanagement.port.client;


import mk.ukim.finki.emt.lab.carrental.rentmanagement.application.VehicleCatalog;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.Vehicle;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.VehicleId;
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
public class VehicleCatalogClient implements VehicleCatalog {

        private static final Logger LOGGER = LoggerFactory.getLogger(VehicleCatalogClient.class);

        private final RestTemplate restTemplate;
        private final String serverUrl;

        VehicleCatalogClient(@Value("${app.vehicle-catalog.url}") String serverUrl,
                             @Value("${app.vehicle-catalog.connect-timeout-ms}") int connectTimeout,
                             @Value("${app.vehicle-catalog.read-timeout-ms}") int readTimeout) {
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
        public List<Vehicle> findAll() {
            try {
                return restTemplate.exchange(uri().path("/api/vehicles").build().toUri(), HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Vehicle>>() {
                        }).getBody();
            } catch (Exception ex) {
                LOGGER.error("Error retrieving vehicles", ex);
                return Collections.emptyList();
            }
        }

    @Override
    public List<Vehicle> findAllAvailable() {
        try {
            return restTemplate.exchange(uri().path("/api/vehicles/available").build().toUri(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Vehicle>>() {
                    }).getBody();
        } catch (Exception ex) {
            LOGGER.error("Error retrieving vehicles", ex);
            return Collections.emptyList();
        }
    }

    @Override
        public Vehicle findById(VehicleId id) {
            try {
                return restTemplate.exchange(uri().path("/api/vehicles/"+id.getId()).build().toUri(), HttpMethod.GET, null,
                        new ParameterizedTypeReference<Vehicle>() {
                        }).getBody();
            } catch (Exception ex) {
                LOGGER.error("Error retrieving vehicle by id", ex);
                return null;
            }
        }
    }


