package fun.bookish.sv.core.logger;

public interface MyLogger {

    static MyLogger create(String name){
        return new MyLoggerImpl(name);
    }

    void info(String msg);

    void info(String msg, Throwable e);

    void warn(String msg);

    void warn(String msg, Throwable e);

    void error(String msg);

    void error(String msg, Throwable e);

}
