import org.apache.commons.lang3.StringUtils;

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
    }
}
