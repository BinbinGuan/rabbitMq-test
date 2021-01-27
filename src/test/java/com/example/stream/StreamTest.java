package com.example.stream;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        System.out.println("peek使用:");
        Stream.of("one", "two", "three", "four").filter(e -> e.length() > 3).peek(e -> System.out.println("转换之前: " + e))
                .map(String::toUpperCase).peek(e -> System.out.println("转换之后: " + e)).collect(Collectors.toList());

        List<String> strings = Arrays.asList("a", "", "c", "", "e", "", " ");
        // 获取空字符串的数量
        long count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("空字符串的个数:" + count);

        List<String> list13 = Arrays.asList("zhangsan", "lisi", "wangwu", "xuwujing");
        int maxLines = list13.stream().mapToInt(String::length).max().getAsInt();
        int minLines = list13.stream().mapToInt(String::length).min().getAsInt();
        System.out.println("最长字符的长度:" + maxLines + ",最短字符的长度:" + minLines);


        String lines = "good good study day day up";
        List<String> list14 = new ArrayList<String>();
        list14.add(lines);
        List<String> words = list14.stream().flatMap(line -> Stream.of(line.split(" "))).filter(word -> word.length() > 0)
                .map(String::toLowerCase).distinct().sorted().collect(Collectors.toList());

        System.out.println("去重复之后:" + words);
//去重复之后:[day, good, study, up]

        // 求和, 无起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println("有无起始值求和:" + sumValue);
// 求和, 有起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(1, Integer::sum);
        System.out.println("有起始值求和:" + sumValue);
//    有无起始值求和:10
//    有起始值求和:11

        Stream.iterate(2, n -> n + 2).limit(5).forEach(x -> System.out.print(x + " "));

        LocalDate nowDate = LocalDate.now();
//本地日期,包括时分秒
        LocalDateTime nowDateTime = LocalDateTime.now();

        System.out.println("当前时间:"+nowDate);
        System.out.println("当前时间:"+nowDateTime);

        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("格式化时间: "+ ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
//格式化时间:2018-12-19 15:37:47.119


        LocalDate ld=LocalDate.parse("2017-11-17");
        LocalDate ld2=LocalDate.parse("2018-01-05");
        Period p=Period.between(ld, ld2);
        System.out.println("相差年: "+p.getYears()+" 相差月 :"+p.getMonths() +" 相差天:"+p.getDays());
// 相差年: 0 相差月 :1 相差天:19


        LocalDate startDate = LocalDate.of(2017, 11, 17);
        LocalDate endDate = LocalDate.of(2018, 01, 05);
        System.out.println("相差月份:"+ChronoUnit.MONTHS.between(startDate, endDate));
        System.out.println("两月之间的相差的天数   : " + ChronoUnit.DAYS.between(startDate, endDate));
//        System.out.println("两月之间的相差的天数   : " + ChronoUnit.HOURS.between(startDate, endDate));
//相差月份:1
//两天之间的差在天数   : 49


        Instant inst1 = Instant.now();
        System.out.println("当前时间戳 : " + inst1);
        Instant inst2 = inst1.plus(Duration.ofSeconds(10));
        System.out.println("增加之后的时间 : " + inst2);
        System.out.println("相差毫秒 : " + Duration.between(inst1, inst2).toMillis());
        System.out.println("相毫秒 : " + Duration.between(inst1, inst2).getSeconds());
//当前时间戳 : 2018-12-19T08:14:21.675Z
//增加之后的时间 : 2018-12-19T08:14:31.675Z
//相差毫秒 : 10000
//相毫秒 : 10


        Clock clock = Clock.systemUTC();
        System.out.println("当前时间戳 : " + clock.millis());
        Clock clock2 = Clock.system(ZoneId.of("Asia/Shanghai"));
        System.out.println("亚洲上海此时的时间戳:"+clock2.getZone());
        Clock clock3 = Clock.system(ZoneId.of("America/New_York"));
        System.out.println("美国纽约此时的时间戳:"+clock3.getZone());
//当前时间戳 : 1545209277657
//亚洲上海此时的时间戳:1545209277657
//美国纽约此时的时间戳:1545209277658


        ZoneId zoneId= ZoneId.of("America/New_York");
        ZonedDateTime dateTime=ZonedDateTime.now(zoneId);
        System.out.println("美国纽约此时的时间 : " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        System.out.println("美国纽约此时的时间 和时区: " + dateTime);
    }
}
