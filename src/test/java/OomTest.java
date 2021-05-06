import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author: GuanBin
 * @date: Created in 上午11:18 2021/4/19
 */
public class OomTest {
    private static List list=new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            list.add(IntStream.rangeClosed(1, 10_000)
                    .mapToObj(__ -> "A")
                    .collect(Collectors.joining("")));
        }

    }


}
