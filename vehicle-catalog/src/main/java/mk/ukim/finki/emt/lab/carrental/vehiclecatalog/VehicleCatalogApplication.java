package mk.ukim.finki.emt.lab.carrental.vehiclecatalog;

import mk.ukim.finki.emt.lab.carrental.sharedkernel.SharedConfiguration;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.infra.eventlog.RemoteEventLogService;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.port.rest.client.RemoteEventLogServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@Import(SharedConfiguration.class)
public class VehicleCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleCatalogApplication.class, args);
    }

      @Bean
      public RemoteEventLogService rentEvents(@Value("${app.rents.url}") String serverUrl,
                                               @Value("${app.rents.connect-timeout-ms}") int connectTimeout,
                                               @Value("${app.rents.read-timeout-ms}") int readTimeout) {
            return new RemoteEventLogServiceClient(serverUrl, connectTimeout, readTimeout);
      }
}