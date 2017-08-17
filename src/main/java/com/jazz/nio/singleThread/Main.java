package com.jazz.nio.singleThread;

/**
 * http://blog.csdn.net/yehjordan/article/details/51012833
 * @Description:
 * @Team: 新金融业务研发团队
 * @Author BK
 * @Date 2017/8/17 16:54
 * @Version V2.0
 */

import java.io.IOException;

public class Main {


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            TCPReactor reactor = new TCPReactor(1333);
            reactor.run();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
