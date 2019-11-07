package leetcode.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 *
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: GuanBin
 * @date: Created in 下午11:06 2019/10/27
 */
public class ThreeSumClosest {
    public static void main(String[] args) {
        int [] nums = {-1,2,1,-4};
        int target=1;
        //遍历数组，都和target相减，找出差值最小的三个数
        System.out.println(threeSumClosest(nums, target));

    }

    private static int threeSumClosest(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] cun = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i] + target, i);
            cun[i] = nums[i] + target;
        }
        Arrays.sort(cun);

        return nums[map.get(cun[0])] + nums[map.get(cun[1])] + nums[map.get(cun[2])];
    }
}
