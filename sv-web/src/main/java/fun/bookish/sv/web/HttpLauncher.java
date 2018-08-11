package fun.bookish.sv.web;


import fun.bookish.sv.core.utils.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class HttpLauncher implements ApplicationListener<ContextRefreshedEvent> {

    private final HttpServer httpServer;

    @Autowired
    public HttpLauncher(HttpServer httpServer) throws Exception {
        this.httpServer = httpServer;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 配置spring容器工具类
        SpringUtil.setApplicationContext(event.getApplicationContext());
        // 启动
        httpServer.start();
    }

}
