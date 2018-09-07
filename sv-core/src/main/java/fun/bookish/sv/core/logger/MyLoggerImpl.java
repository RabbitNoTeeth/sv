package fun.bookish.sv.core.logger;

import fun.bookish.sv.core.utils.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

public class MyLoggerImpl implements MyLogger {

    private final String name;
    private final Logger logger;
    private final AtomicBoolean initRef = new AtomicBoolean(false);
    private final AtomicBoolean enableRef = new AtomicBoolean(false);

    MyLoggerImpl(String name) {
        this.name = name;
        this.logger = LoggerFactory.getLogger(name);
    }

    private void checkInit(LoggerFunction loggerFunction){
        if(!initRef.get()){
            LoggerProperties loggerProperties = SpringUtil.getBeanByClass(LoggerProperties.class);
            enableRef.set(loggerProperties.getEnableList().contains(name));
            initRef.set(true);
        }
        if(enableRef.get()){
            loggerFunction.doLog();
        }
    }

    @Override
    public void info(String msg) {
        this.checkInit(() -> {
            logger.info(msg);
        });
    }

    @Override
    public void info(String msg, Throwable e) {
        this.checkInit(() -> {
            logger.info(msg, e);
        });
    }

    @Override
    public void warn(String msg) {
        this.checkInit(() -> {
            logger.warn(msg);
        });
    }

    @Override
    public void warn(String msg, Throwable e) {
        this.checkInit(() -> {
            logger.warn(msg, e);
        });
    }

    @Override
    public void error(String msg) {
        this.checkInit(() -> {
            logger.error(msg);
        });
    }

    @Override
    public void error(String msg, Throwable e) {
        this.checkInit(() -> {
            logger.error(msg, e);
        });
    }
}
