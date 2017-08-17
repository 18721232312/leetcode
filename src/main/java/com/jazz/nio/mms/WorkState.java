package com.jazz.nio.mms;

/**
 * @Description:
 * @Team: 新金融业务研发团队
 * @Author BK
 * @Date 2017/8/17 20:33
 * @Version V2.0
 */

        import java.io.IOException;
        import java.nio.channels.SelectionKey;
        import java.nio.channels.SocketChannel;
        import java.util.concurrent.ThreadPoolExecutor;

public class WorkState implements HandlerState {

    public WorkState() {
    }

    @Override
    public void changeState(TCPHandler h) {
        // TODO Auto-generated method stub
        h.setState(new WriteState());
    }

    @Override
    public void handle(TCPHandler h, SelectionKey sk, SocketChannel sc,
                       ThreadPoolExecutor pool) throws IOException {
        // TODO Auto-generated method stub

    }

}
