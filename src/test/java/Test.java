import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: GuanBin
 * @date: Created in 下午1:48 2020/6/1
 */
public class Test {
    private static final String[] WHITE_LIST_URL = {"process-definition", ""};

    public static void main(String[] args) throws InterruptedException {
//        if(StringUtils.containsAny("/users/simple","")){
////            System.out.println("baohan");
////        }
////        String cloudEntryTypeIds="yacmp:cloudentry:type:vsphere,yacmp:cloudentry:type:powervc,yacmp:cloudentry:type:openstack,yacmp:cloudentry:type:azure,yacmp:cloudentry:type:qingcloud,yacmp:cloudentry:type:aws,yacmp:cloudentry:type:aliyun,yacmp:cloudentry:type:generic-cloud:zstack,yacmp:cloudentry:type:generic-cloud:tencentcloud,yacmp:cloudentry:type:generic-cloud:smartx,yacmp:cloudentry:type:generic-cloud:gcp,yacmp:cloudentry:type:generic-cloud:ksyun,yacmp:cloudentry:type:generic-cloud:ucloud,yacmp:cloudentry:type:generic-cloud:fusioncompute";
////        String c="yacmp:cloudentry:type:vsphere";
////        String[] split = StringUtils.split(cloudEntryTypeIds, ",");
////        List<String> strings = Arrays.asList(StringUtils.split(c, ","));
////
////        String substring = StringUtils.substring(strings.get(0), strings.get(0).length() + 1);
////
////        String collect = Arrays.asList(split).stream().map(s -> s.substring(s.lastIndexOf(":") + 1)).collect(Collectors.joining(","));
////
////        System.out.println(collect);
////
////
////
////
////
////
////
////
////        List<String> names= new ArrayList<>();
////        Arrays.asList(split).forEach(s -> names.add(s.substring(s.lastIndexOf(":")+1)) );


//        HashSet<Object> set = new HashSet<>();
//
//        set.add("a");
//        set.add("b");
//        set.add("c");
//        set.add("d");
//        System.out.println(set.iterator().next());


//        StringBuilder str = new StringBuilder();
//        str.append("ll");
//        str.append(" ");
//        System.out.println("字符是"+str);
//        str.append("\\u0015");
//        System.out.println("最终字符是"+str);

//        StringBuilder str = new StringBuilder();
//        str.append("r");
//        System.out.println("输出"+str.toString());
//        str.append("m");
//        System.out.println("输出"+str.toString());

//        String a="\u001b";
//
//        System.out.println("\u001b");


//        String str="total 14868\n" +
//                "-rw-r--r--  1 root root 13159688 Jan 21 13:39 8663.sql\n" +
//                "-rw-------. 1 root root     1247 Dec 14 19:59 anaconda-ks.cfg\n" +
//                "-rw-r--r--  1 root root    70907 Jan 13 14:22 plugins.log\n" +
//                "-rw-r--r--  1 root root    39800 Jul  4  2014 \u001B[0m\u001B[01;31mpython-iniparse-0.4-9.el7.noarch.rpm\u001B[0m\n" +
//                "-rw-r--r--  1 root root       10 Feb 26 11:32 test.sh\n" +
//                "-rw-r--r--  1 root root       91 Feb 26 11:28 test.txt\n" +
//                "-rw-r--r--  1 root root       79 Feb 23 10:37 text1.txt\n" +
//                "-rw-r--r--  1 root root       33 Feb 23 11:07 text.txt\n" +
//                "-rw-r--r--  1 root root   560272 Feb 22 16:59 \u001B[01;31mwget-1.14-18.el7_6.1.x86_64.rpm\u001B[0m\n" +
//                "-rw-r--r--  1 root root  1298856 Oct 15 03:21 \u001B[01;31myum-3.4.3-168.el7.centos.noarch.rpm\u001B[0m\n" +
//                "-rw-r--r--  1 root root    28348 Jul  4  2014 \u001B[01;31myum-metadata-parser-1.1.4-10.el7.x86_64.rpm\u001B[0m\n" +
//                "-rw-r--r--  1 root root    35216 May 14  2020 \u001B[01;31myum-plugin-fastestmirror-1.1.31-54.el7_8.noarch.rpm\u001B[0m\n" +
//                "[root@cmpf4e1 ~]# ";
//
//
//        String[] split = str.split(System.getProperty("line.separator"));
//        String lastLine =  split[split.length - 1];
//        System.out.println(lastLine);
//
//
//        String viCommond=" vi  test.txt ";
//        String sc = viCommond.trim();
////        System.out.println( sc.substring(sc.length()-1));
//        String substring = sc.substring(sc.length() - 1);
//        if(sc.startsWith("vi ")&&StringUtils.isNotBlank(substring)){
//
//        }


        long l = System.currentTimeMillis();
        Thread.sleep(100);
        long m = System.currentTimeMillis();

        float l1 = (float)(m - l) / 1000;


    }
}
