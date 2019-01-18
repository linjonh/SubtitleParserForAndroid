package com.github.dnbn.submerge.api.subtitle.ass;

//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;

import com.github.dnbn.submerge.api.subtitle.common.SubtitleTime;

import java.util.Arrays;

/**
 * The class <code>ASSTime</code> represents a SubStation Alpha time : meaning the time at
 * which the text will appear and disappear onscreen
 */
public class ASSTime extends SubtitleTime {

    /**
     * Serial
     */
    private static final long serialVersionUID = -8393452818120120069L;

    /**
     * The time pattern
     */
    public static final String TIME_PATTERN = "H:mm:ss.SS";
    private static final String TS_PATTERN = "%02d:%02d:%02d.%02d";

    /**
     * The time pattern formatter
     */
//    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(TIME_PATTERN);

    /**
     * Constructor
     */
    public ASSTime(long start, long end) {
        super(start, end);
    }

    /**
     * Constructor
     */
    public ASSTime() {
        super();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(format(this.start));
        sb.append(" ");
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
//        if (time != null) {
//            return time.format(FORMATTER);
//        } else {
//            System.out.println("time = [" + time + "]");
//            return "00:00:00";
//        }
        long ms = time % 1000;
        long s = time / 1000;
        long hh = s / 60 / 60;
        long min = (s / 60) % 60;
        long sec = s % 60;

        return String.format(TS_PATTERN, hh, min, sec, ms);
    }

    /**
     * @param in string like 10:23:56.90 最小为0.01秒
     * @return long milliseconds
     */
    public static long fromString(String in) {
        String[] split1 = in.split(":");
        long hours = Long.parseLong(split1[0].trim());
        long minutes = Long.parseLong(split1[1].trim());
        long seconds = 0;
        long millies = 0;
        if (split1.length > 2) {
            String[] split = split1[2].split("\\.");//ASS format
            seconds = Long.parseLong(split[0].trim());
            if (split.length > 1) {
                millies = Long.parseLong(split[1].trim());
            } else {
                System.out.println("in = [" + in + "]");
            }
        } else {
            System.out.println("in = [" + in + "] split:" + Arrays.toString(split1));
        }
        return hours * 60 * 60 * 1000 + minutes * 60 * 1000 + seconds * 1000 + millies;
    }
}
