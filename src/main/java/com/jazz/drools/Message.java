package com.jazz.drools;

/**
 * @Description:
 * @Team: 新金融业务研发团队
 * @Author BK
 * @Date 2017/6/6 17:36
 * @Version V2.0
 */

import java.io.Serializable;


public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private String            status;
    private String type;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }
}