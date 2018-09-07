package fun.bookish.sv.core.logger;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 日志配置类
 */
@Component
@ConfigurationProperties(prefix = "logger")
public class LoggerProperties {

    private List<String> enableList;            // 启用的日志列表

    public List<String> getEnableList() {
        return enableList;
    }

    public void setEnableList(List<String> enableList) {
        this.enableList = enableList;
    }
}
