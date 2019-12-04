package pacs.util;

import java.sql.Timestamp;

/**
 * @program: PACS
 * @Date: 2019/11/22 16:22
 * @Author: Mr.Liu
 * @Description:
 */
public class DateUtil {

    public static java.sql.Timestamp d2t(java.util.Date d) {
        if( null == d){
            return null;
        }
        return new java.sql.Timestamp(d.getTime());
    }

    public static java.util.Date t2d(java.sql.Timestamp t) {
        if (null == t)
            return null;
        return new java.util.Date(t.getTime());
    }
}
