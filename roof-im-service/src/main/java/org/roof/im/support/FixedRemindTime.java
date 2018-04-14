package org.roof.im.support;

import org.apache.commons.lang.time.DateUtils;
import org.roof.im.session.Session;

/**
 * @author liuxin
 * @since 2018/4/13
 */
public class FixedRemindTime implements RemindTime {

    private static final int MIN_REMIND_TIME_MINUTE = 3 * 60;
    private static final int MID_REMIND_TIME_MINUTE = 5 * 60;
    private static final int MAX_REMIND_TIME_MINUTE = 10 * 60;


    private static final long MIN_REMIND_TIME = MIN_REMIND_TIME_MINUTE * DateUtils.MILLIS_PER_SECOND;

    private static final long MID_REMIND_TIME = MID_REMIND_TIME_MINUTE * DateUtils.MILLIS_PER_SECOND;

    private static final long MAX_REMIND_TIME = MAX_REMIND_TIME_MINUTE * DateUtils.MILLIS_PER_SECOND;

    /**
     * 咨询时间小于3分钟返回咨询时间<br/>
     * 咨询时间3-5分钟返回3分钟 <br/>
     * 咨询时间5-20分钟返回5分钟 <br/>
     * 咨询时间大于20分钟返回10分钟 <br/>
     *
     * @return 提醒结束时间
     */
    @Override
    public int getRemindTime(Session session) {
        long startTime = session.getStartTime();
        long endTime = session.getEndTime();
        long interval = endTime - startTime;
        if (interval < MIN_REMIND_TIME) {
            return (int) (interval / DateUtils.MILLIS_PER_SECOND);
        }
        if (interval > MIN_REMIND_TIME && interval < MID_REMIND_TIME) {
            return MIN_REMIND_TIME_MINUTE;
        }
        if (interval > MID_REMIND_TIME && interval < 2 * MAX_REMIND_TIME) {
            return MID_REMIND_TIME_MINUTE;
        }
        return MAX_REMIND_TIME_MINUTE;
    }
}
