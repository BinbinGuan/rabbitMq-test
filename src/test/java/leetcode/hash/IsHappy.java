package leetcode.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 编写一个算法来判断一个数是不是“快乐数”。
 * <p>
 * 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。
 * <p>
 * 示例:
 * <p>
 * 输入: 19
 * 输出: true
 * 解释:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/happy-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: GuanBin
 * @date: Created in 下午11:18 2019/10/20
 */
public class IsHappy {


    public static void main(String[] args) {
        System.out.println(isHappy(9));
//        System.out.println(isHappy(11));
//        System.out.println(isHappy(19));
    }


    /**
     * 快慢指针
     * 若是快乐数，则快指针和慢指针最终都会为1，因为快指针是多计算两步，所以快指针为1后，后面的计算一直为1，一直等到慢指针也为1则结束循环
     * @param n
     * @return
     */
    private static boolean isHappy(int n) {
        int slow = n, fast = bitSquareSum(n);
        while (slow != fast) {
            slow = bitSquareSum(slow);
            fast = bitSquareSum(fast);
            fast = bitSquareSum(fast);
        }
        return slow == 1;
    }

    /**
     * 方法：使用“快慢指针”思想找出循环：“快指针”每次走两步，“慢指针”每次走一步，当二者相等时，即为一个循环周期。此时，判断是不是因为1引起的循环，是的话就是快乐数，否则不是快乐数。
     * 注意：此题不建议用集合记录每次的计算结果来判断是否进入循环，因为这个集合可能大到无法存储；另外，也不建议使用递归，同理，如果递归层次较深，会直接导致调用栈崩溃。不要因为这个题目给出的整数是int型而投机取巧。
     *
     * @param n
     * @return
     */
    private static int bitSquareSum(int n) {
        int sum = 0;
        while (n > 0) {
            int bit = n % 10;
            sum += bit * bit;
            n = n / 10;
        }
        return sum;
    }


    /**
     * @see https://leetcode-cn.com/problems/happy-number/solution/kuai-le-shu-wen-ti-de-tui-dao-lun-zheng-fen-xi-by-/
     * @param n
     * @return
     */
    public static boolean isHappy2(int n) {
        if (n <= 0) return false;

        HashSet<Integer> sumHistory = new HashSet<Integer>();
        while (n != 1) {
            if (sumHistory.contains(n)) {
                return false;
            } else {
                sumHistory.add(n);
            }

            int sumTemp = 0;
            while (n != 0) {
                int singleNum = n % 10;
                sumTemp += singleNum * singleNum;
                n /= 10;
            }

            n = sumTemp;
        }

        return true;
    }



}
