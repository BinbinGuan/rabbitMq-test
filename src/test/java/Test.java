import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: GuanBin
 * @date: Created in 下午1:48 2020/6/1
 */
public class Test {
    private static final String[] WHITE_LIST_URL = {"process-definition", ""};

    public static void main(String[] args) {
        if(StringUtils.containsAny("/users/simple","")){
            System.out.println("baohan");
        }
        String cloudEntryTypeIds="yacmp:cloudentry:type:vsphere,yacmp:cloudentry:type:powervc,yacmp:cloudentry:type:openstack,yacmp:cloudentry:type:azure,yacmp:cloudentry:type:qingcloud,yacmp:cloudentry:type:aws,yacmp:cloudentry:type:aliyun,yacmp:cloudentry:type:generic-cloud:zstack,yacmp:cloudentry:type:generic-cloud:tencentcloud,yacmp:cloudentry:type:generic-cloud:smartx,yacmp:cloudentry:type:generic-cloud:gcp,yacmp:cloudentry:type:generic-cloud:ksyun,yacmp:cloudentry:type:generic-cloud:ucloud,yacmp:cloudentry:type:generic-cloud:fusioncompute";
        String c="yacmp:cloudentry:type:vsphere";
        String[] split = StringUtils.split(cloudEntryTypeIds, ",");
        List<String> strings = Arrays.asList(StringUtils.split(c, ","));

        String substring = StringUtils.substring(strings.get(0), strings.get(0).length() + 1);

        String collect = Arrays.asList(split).stream().map(s -> s.substring(s.lastIndexOf(":") + 1)).collect(Collectors.joining(","));

        System.out.println(collect);








        List<String> names= new ArrayList<>();
        Arrays.asList(split).forEach(s -> names.add(s.substring(s.lastIndexOf(":")+1)) );


    }
}
