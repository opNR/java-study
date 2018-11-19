package mi.feng.point.serializable;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: MiFeng
 * @Date: 2018/11/13 14:48
 * @Description:
 */
public class PersistentTime implements Serializable {

    private Date time;

    public PersistentTime(){
        time = Calendar.getInstance().getTime();
    }

    public Date getTime() {
        return time;
    }
}
