package leetcode.array;

public class GupiaoMaxProfit {

    /**
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/121-mai-mai-gu-piao-de-zui-jia-shi-ji-by-leetcode-/
     *
     * 复杂度分析
     *
     * 时间复杂度：O(n^2)O(n
     * 2
     *  )。循环运行 \dfrac{n (n-1)}{2}
     * 2
     * n(n−1)
     * ​
     *   次。
     * 空间复杂度：O(1)O(1)。只使用了常数个变量。
     * @param prices
     * @return
     */
    public int maxProfit(int prices[]) {
        int maxprofit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxprofit)
                    maxprofit = profit;
            }
        }
        return maxprofit;
    }


    /**
     * 解决2
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     */
    public class Solution {
        public int maxProfit(int prices[]) {
            int minprice = Integer.MAX_VALUE;
            int maxprofit = 0;
            for (int i = 0; i < prices.length; i++) {
                if (prices[i] < minprice)
                    minprice = prices[i];
                else if (prices[i] - minprice > maxprofit)
                    maxprofit = prices[i] - minprice;
            }
            return maxprofit;
        }
    }


}
