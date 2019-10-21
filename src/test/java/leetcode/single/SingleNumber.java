package leetcode.single;

import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 *
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: GuanBin
 * @date: Created in 上午9:21 2019/10/16
 */
public class SingleNumber {
    public static void main(String[] args) {
        int[] num={2,2,3,3,4};
        System.out.println(singleNumber(num));
        System.out.println(singleNumber2(num));
        System.out.println(singleNumber3(num));
    }

    /**
     * 交换律：a ^ b <=> b ^ a
     * 任何数于0异或为任何数 0 ^ n => n
     * 相同的数异或为0: n ^ n => 0
     * @param nums
     * @return res
     */
    public static int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }

    /**
     * 不满足空间复杂度o(1)
     * @param nums
     * @return
     */
    public static int singleNumber2(int[] nums){
        HashSet<Integer> set = new HashSet<>();
        //这样最后的set就只包含这个不重复的元素了
        for(int i = 0; i < nums.length; i++){
            if(set.contains(nums[i])){
                set.remove(nums[i]);
            }else{
                set.add(nums[i]);
            }
        }
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            if(set.contains(nums[i])){
                res = nums[i];
                break;
            }
        }
        return res;
    }

    public static int singleNumber3(int[] nums) {
        if(nums.length < 2){
            return nums[0];
        }
        Arrays.sort(nums);
        //唯一的数不在第一个位置
        int res = 0;
        for(int i = 0; i < nums.length; i += 2){
            if(i == nums.length - 1 || nums[i] != nums[i + 1]){
                res = nums[i];
                break;
            }
        }
        return res;
    }

}
