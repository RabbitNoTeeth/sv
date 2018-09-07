package fun.bookish.sv;


import fun.bookish.sv.core.logger.MyLogger;
import fun.bookish.sv.core.utils.SpringUtil;
import fun.bookish.sv.web.HttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class MainLauncher implements ApplicationListener<ContextRefreshedEvent> {

    private final MyLogger LOGGER = MyLogger.create("main");
    private final HttpServer httpServer;

    @Autowired
    public MainLauncher(HttpServer httpServer) throws Exception {
        this.httpServer = httpServer;
    }

    /**
     * spring容器所有bean加载完毕后触发该方法
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 配置spring容器工具类
        SpringUtil.setApplicationContext(event.getApplicationContext());
        // 启动
        this.start();

    }

    private void start(){
        LOGGER.info("1. ---- 启动 http web 服务 ---->");
        httpServer.start();
    }

}
