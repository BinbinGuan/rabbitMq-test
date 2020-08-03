package utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author: GuanBin
 * @date: Created in 下午3:31 2020/3/23
 */
public class TimeUtils {

    public static void main(String[] args) {
        System.out.println(todayFirstDate()+"---"+todayFirstDate().getTime());
        System.out.println(yesterdayLastDate()+"---"+yesterdayLastDate().getTime());
    }


    public static Date todayFirstDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date yesterdayLastDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
}
