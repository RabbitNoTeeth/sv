package fun.bookish.sv.core.configuration;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * java config 将vertx/web client等注入到容器中
 */
@Configuration
public class CoreConfiguration {

    private final VertxProperties vertxProperties;
    private static final int CORE_NUM = Runtime.getRuntime().availableProcessors();

    @Autowired
    public CoreConfiguration(VertxProperties vertxProperties) {
        this.vertxProperties = vertxProperties;
    }

    @Bean
    public Vertx vertx(){
        VertxOptions options = new VertxOptions();
        options.setEventLoopPoolSize(vertxProperties.getEventLoopPoolSize() < CORE_NUM * 2 ? CORE_NUM * 2 : vertxProperties.getEventLoopPoolSize());
        options.setWorkerPoolSize(vertxProperties.getWorkerPoolSize() < CORE_NUM * 2 ? CORE_NUM * 2 : vertxProperties.getWorkerPoolSize());
        return Vertx.vertx();
    }

    @Bean
    public WebClient webClient(Vertx vertx){
        WebClientOptions options = new WebClientOptions();
        return WebClient.create(vertx, options);
    }

}
