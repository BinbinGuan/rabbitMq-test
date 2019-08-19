package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: GuanBin
 * @date: Created in 上午10:01 2019/8/9
 */
public class TowSum {


    @Test
    public void  test1(){
        Boolean a=true;
        test2(a);
        if(!a){
            System.out.println(a);
        }

    }


    private void test2(Boolean a){
       a=false;
    }



    @Test
    public void test() {
        //计算两数之和
//        int[] nums = {2, 7, 11, 15};
//        int[] twoResult = twoSum(nums, 9);

        int[] nums = {2, 3, 3}; //解法2
        int[] twoResult = twoSum1(nums, 5);
        System.out.println(String.format("tow result is [%s,%s]", twoResult[0], twoResult[1]));

    }


    /**
     * 计算两数之和
     * 1. 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 2. 示例：
     * 给定 nums = [2, 7, 11, 15], target = 9
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 解法1
     *
     * @param nums
     * @param target
     * @return
     */
    private int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int add2 = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == add2) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * 解法2
     * 使用HashMap（但是如果出现两个数相同，则map里的key值会被覆盖）
     *
     * @param nums
     * @param target
     * @return
     */
    private int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int add2 = target - nums[i];
            if (map.containsKey(add2) && map.get(add2) != i) {
                return new int[]{i, map.get(add2)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }


}
