package fun.bookish.sv.web;

import fun.bookish.sv.core.logger.MyLogger;
import fun.bookish.sv.web.config.HttpProperties;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * http服务
 */
@Component
public class HttpServer {

    private final Vertx vertx;
    private final HttpProperties httpProperties;

    private final MyLogger LOGGER = MyLogger.create("sv-web");

    @Autowired
    public HttpServer(Vertx vertx, HttpProperties httpProperties){
        this.vertx = vertx;
        this.httpProperties = httpProperties;
    }

    public void start(){
        LOGGER.info(" --- 开始部署http服务 ---");
        DeploymentOptions options = new DeploymentOptions().setInstances(httpProperties.getInstance() < 1 ? 1 : httpProperties.getInstance());
        vertx.deployVerticle("fun.bookish.sv.web.verticle.HttpVerticle", options, res -> {
            if(res.succeeded()){
                //LOGGER.info("http服务部署成功");
            }else{
                LOGGER.error("http部署失败", res.cause());
            }
        });
    }
}
