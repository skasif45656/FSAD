package com.example.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@LoadBalancerClient(name = "STUDENT-SERVICE", configuration = ApiGatewayApplication.CustomLoadBalancerConfiguration.class)
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Configuration
	class CustomLoadBalancerConfiguration {
		@Bean
		@Primary
		ServiceInstanceListSupplier serviceInstanceListSupplier() {
			return new ServiceInstanceListSupplier() {
				@Override
				public String getServiceId() {
					return "STUDENT-SERVICE";
				}

				@Override
				public Flux<List<ServiceInstance>> get() {
					return Flux.just(Arrays.asList(
							new DefaultServiceInstance("instance1", "STUDENT-SERVICE", "localhost", 8081, false),
							new DefaultServiceInstance("instance2", "STUDENT-SERVICE", "localhost", 8082, false)
					));
				}
			};
		}
	}
}
