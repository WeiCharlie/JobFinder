package utils;

import android.util.Log;

/**
 * Created
 * Author:Charlie Wei[]
 * Email:charlie_net@163.com
 * Date:2015/10/17
 */
public final class MyLog {

    private static final boolean LOG_ON = true;

    private MyLog(){}

    public static void d(String tag,String msg){

        if (LOG_ON) {
            Log.d(tag, msg);
        }
    }
    public static void e(String tag,String msg){

        if (LOG_ON) {
            Log.e(tag, msg);
        }
    }
    public static void i(String tag,String msg){

        if (LOG_ON) {
            Log.i(tag, msg);
        }
    }
    public static void w(String tag,String msg){

        if (LOG_ON) {
            Log.w(tag, msg);
        }
    }
}
