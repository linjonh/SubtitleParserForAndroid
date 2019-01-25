package com.github.dnbn.submerge.api;

import com.github.dnbn.submerge.api.parser.ASSParser;
import com.github.dnbn.submerge.api.parser.SRTParser;
import com.github.dnbn.submerge.api.subtitle.ass.ASSSub;
import com.github.dnbn.submerge.api.subtitle.common.TimedLine;
import com.github.dnbn.submerge.api.subtitle.srt.SRTSub;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Test {

    private static boolean isSrt;

    public static void main(String args[]) {

        Set<? extends TimedLine> timedLines = getTimedLines();
        printTimeLine(timedLines, isSrt);

    }

    public static Set<? extends TimedLine> getTimedLines() {
        File assFile = new File("E:\\有字幕的视频\\Game.of.Thrones.S06E10.1080p.HDTV.x264-BATV.简体.ass");
        File srtFile = new File("C:\\Users\\jianyou.lin\\Downloads\\interstellar.(2014).chi.1cd.(7601884)\\interstellar.srt");
        isSrt = true;


//        for (TimedLine next : parse.getTimedLines()) {
//            LocalTime start = next.getTime().getStart();
//            LocalTime end = next.getTime().getEnd();
//            List<String> textLines = next.getTextLines();
//            System.out.println("next start:" + start);
//            System.out.println("next end:" + end);
//            for (int i = 0; i < textLines.size(); i++) {
//                System.out.println("next line:" + textLines.get(i));
//            }
//        }
        System.out.println("effect:");
        Set<? extends TimedLine> timedLines;
        if (isSrt) {
            SRTParser srtParser = new SRTParser();
            SRTSub srtSub = srtParser.parse(srtFile,"utf-8");
            timedLines = srtSub.getTimedLines();
        } else {
            ASSParser assParser = new ASSParser();
            ASSSub parse = assParser.parse(assFile,"utf-8");
            String filename = parse.getFilename();
            System.out.println("filename:" + filename);
            timedLines = parse.getTimedLines();
        }
        return timedLines;
    }

    public static List<String> printTimeLine(Set<? extends TimedLine> timedLines, boolean isSrt) {
        List<String> arrayList = new ArrayList<>();
        for (TimedLine timedLine : timedLines) {
            long startMs = timedLine.getTime().getStart();
            long endMs = timedLine.getTime().getEnd();

//            String toString = start.toString();
//            if (isSrt) {
//                startMs = parseSRTSubtitleTime(toString);
//                endMs = parseSRTSubtitleTime(end.toString());
//            } else {
//            startMs = parseASSSubtitleTime(toString);
//            endMs = parseASSSubtitleTime(end.toString());
//            }
            List<String> textLines = timedLine.getTextLines();
            StringBuilder stringBuilder = new StringBuilder();
            for (String line : textLines) {
                stringBuilder.append(line);
            }
//            String orgin = "";
//            if (timedLine.getTime() instanceof SRTTime) {
//                SRTTime srtTime = (SRTTime) timedLine.getTime();
//                orgin = srtTime.toString();
//            } else if (timedLine.getTime() instanceof ASSTime) {
//                ASSTime srtTime = (ASSTime) timedLine.getTime();
//                orgin = srtTime.toString();
//            }
            String printText = "origin " + timedLine.getTime().toString() + " start:" + startMs + " end:" + endMs + " text:" + stringBuilder.toString();
            System.out.println(printText);
//            System.out.println( " start:" + startMs + " end:" + endMs + " text:" + stringBuilder.toString());
            arrayList.add(printText);
        }
        return arrayList;
    }

    /**
     * @param in string like 10:23:56.90 最小为0.01秒
     * @return long milliseconds
     */
    public static long parseASSSubtitleTime(String in) {
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
                System.out.println("has not ms in = [" + in + "]");
            }
        } else {
            System.out.println("in = [" + in + "] split:" + Arrays.toString(split1));
        }
        return hours * 60 * 60 * 1000 + minutes * 60 * 1000 + seconds * 1000 + millies * 1000;
    }

    /**
     * @param in string like 10:23:56,909
     * @return long milliseconds
     */
    public static long parseSRTSubtitleTime(String in) {
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
