# SubtitleParserForAndroid
Subtitle file parser. support .ass, .srt format

reference from [submerge](https://github.com/linjonh/submerge)
and then approve it to support android platform at least Android OS API 15, JAVA SDK 1.7.
<p>because Submerge is for console program and using new JAVA SDK API 1.8, so it not support below android 26.


# Usage

Parsing ASS subtitles:
```java

File file = new File("subtitle.ass");

ASSParser parser = new ASSParser();
ASSSub subtitle = parser.parse(file);

System.out.println(subtitle.toString());
Parsing SRT subtitles:

File file = new File("subtitle.srt");
SRTParser parser = new SRTParser();

SRTSub subtitle = parser.parse(file);

System.out.println(subtitle.toString());
```
Using interfaces:
```java
File file = new File("subtitle.srt");
String extension = FilenameUtils.getExtension(file.getName());

SubtitleParser parser = ParserFactory.getParser(extension);
TimedTextFile subtitle = parser.parse(file);

System.out.println(subtitle.toString());
```