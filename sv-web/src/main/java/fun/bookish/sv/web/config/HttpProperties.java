package fun.bookish.sv.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "http")
public class HttpProperties {

    private int port;                                               // 端口号
    private boolean auth;                                           // 是否开启权限校验
    private int instance = 1;                                       // verticle实例数
    private String indexPage = "index.html";                        // 首页
    private String webRoot = "dist";                                // 静态资源根目录
    private List<String> annoPermissions = new ArrayList<>();       // 允许匿名访问的路径

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public List<String> getAnnoPermissions() {
        return annoPermissions;
    }

    public void setAnnoPermissions(List<String> annoPermissions) {
        this.annoPermissions = annoPermissions;
    }

    public int getInstance() {
        return instance;
    }

    public void setInstance(int instance) {
        this.instance = instance;
    }

    public String getIndexPage() {
        return indexPage;
    }

    public void setIndexPage(String indexPage) {
        this.indexPage = indexPage;
    }

    public String getWebRoot() {
        return webRoot;
    }

    public void setWebRoot(String webRoot) {
        this.webRoot = webRoot;
    }
}
