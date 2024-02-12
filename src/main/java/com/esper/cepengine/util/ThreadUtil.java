package com.esper.cepengine.util;

import java.util.concurrent.TimeUnit;

/**
 * @author Bikash Shah
 */
public class ThreadUtil {

    public static void sleepForSecond(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException ire) {
        }
    }

    public static void sleepForMilliSecond(int milliSeconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliSeconds);
        } catch (InterruptedException ire) {
        }
    }
}
