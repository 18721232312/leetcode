package com.jazz.nio.mms;

/**
 * @Description:
 * @Team: 新金融业务研发团队
 * @Author BK
 * @Date 2017/8/17 20:32
 * @Version V2.0
 */

        import java.io.IOException;
        import java.nio.channels.SelectionKey;
        import java.nio.channels.SocketChannel;
        import java.util.concurrent.ThreadPoolExecutor;

public interface HandlerState {

    public void changeState(TCPHandler h);

    public void handle(TCPHandler h, SelectionKey sk, SocketChannel sc,
                       ThreadPoolExecutor pool) throws IOException ;
}
