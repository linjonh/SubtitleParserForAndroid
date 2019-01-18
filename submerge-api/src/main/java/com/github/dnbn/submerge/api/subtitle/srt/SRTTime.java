package com.github.dnbn.submerge.api.subtitle.srt;

import com.github.dnbn.submerge.api.subtitle.common.SubtitleTime;

//import java.time.LocalTime;
import java.util.Arrays;

public class SRTTime extends SubtitleTime {

    private static final long serialVersionUID = -5787808223967579723L;

    //	public static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(SRTTime.PATTERN);
    public static final String PATTERN = "HH:mm:ss,SSS";
    private static final String TS_PATTERN = "%02d:%02d:%02d,%03d";
    public static final String DELIMITER = " --> ";

    public SRTTime() {
        super();
    }

    public SRTTime(long start, long end) {

        super(start, end);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(format(this.start));
        sb.append(DELIMITER);
        sb.append(format(this.end));
        return sb.toString();
    }

    /**
     * Convert a <code>LocalTime</code> to string
     *
     * @param time: the time to format
     * @return the formatted time
     */
    public static String format(long time) {
//
//		int hr = time.get(ChronoField.HOUR_OF_DAY);
//		int min = time.get(ChronoField.MINUTE_OF_HOUR);
//		int sec = time.get(ChronoField.SECOND_OF_MINUTE);
//		int ms = time.get(ChronoField.MILLI_OF_SECOND);
        long ms = time % 1000;
        long s = time / 1000;
        long hh = s / 60 / 60;
        long min = (s / 60) % 60;
        long sec = s % 60;

        return String.format(TS_PATTERN, hh, min, sec, ms);
    }


    /**
     * @param in string like 10:23:56,909
     * @return long milliseconds
     */
    public static long fromString(String in) {
        String[] split1 = in.split(":");
        long hours = Long.parseLong(split1[0].trim());
        long minutes = Long.parseLong(split1[1].trim());
        long seconds = 0;
        long millies = 0;
        if (split1.length > 2) {
            String[] split = split1[2].split(",");//SRT format
            seconds = Long.parseLong(split[0].trim());
            if (split.length > 1) {
                millies = Long.parseLong(split[1].trim());
            } else {
                System.out.println("in = [" + in + "]");
            }
        } else {
            System.out.println("in = [" + in + "] split1:" + Arrays.toString(split1));
        }
        return hours * 60 * 60 * 1000 + minutes * 60 * 1000 + seconds * 1000 + millies;
    }
}
