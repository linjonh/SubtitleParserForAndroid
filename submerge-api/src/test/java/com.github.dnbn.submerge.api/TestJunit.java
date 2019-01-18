package com.github.dnbn.submerge.api;

import com.github.dnbn.submerge.api.subtitle.common.TimedLine;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestJunit {
    @Test
    public void assembleTimedLineText() {
        Set<? extends TimedLine> timedLines = com.github.dnbn.submerge.api.Test.getTimedLines();
        List<String> textLines = com.github.dnbn.submerge.api.Test.printTimeLine(timedLines, false);
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : textLines) {
            int start;
            int end;
            StringBuilder stringLeft = new StringBuilder();
            while ((start = line.indexOf("{")) != -1 && (end = line.indexOf("}")) != -1) {
                int nextLineStringStartIndex = end + 1;
                stringLeft.append(line, 0, start);
//                String blockingWords = line.substring(start, nextLineStringStartIndex);
//                System.out.println("blockingWords:" + blockingWords);
                if (nextLineStringStartIndex < line.length())
                    line = line.substring(nextLineStringStartIndex);
//                System.out.println("stringLeft:" + stringLeft);
            }
            stringBuilder.append(stringLeft)
                    .append(line)
                    .append("\n");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.delete(stringBuilder.lastIndexOf("\n"), stringBuilder.length());
        }
        System.out.println(stringBuilder.toString());
    }
}
