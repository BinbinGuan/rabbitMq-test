package leetcode.digui;

import org.junit.Test;

/**
 * @author: GuanBin
 * @date: Created in 下午11:13 2019/11/21
 */
public class UnivaluePathTest {

    @Test
    public void test() {
        TreeNode root = new TreeNode(5);

        TreeNode rootLeft = new TreeNode(4);
        TreeNode rootRight = new TreeNode(5);

        TreeNode rootLleft = new TreeNode(1);
        TreeNode rootRright = new TreeNode(1);
        TreeNode rootRright2 = new TreeNode(5);

        rootLeft.left = rootLleft;
        rootLeft.right = rootRright;
        rootRight.right = rootRright2;

        root.right = rootRight;
        root.left = rootLeft;


        System.out.println(longestUnivaluePath(root));
    }

    int ans;

    public int longestUnivaluePath(TreeNode root) {
        ans = 0;
        arrowLength(root);
        return ans;
    }

    public int arrowLength(TreeNode node) {
        if (node == null) return 0;
        int left = arrowLength(node.left);
        int right = arrowLength(node.right);
        int arrowLeft = 0, arrowRight = 0;
        if (node.left != null && node.left.val == node.val) {
            arrowLeft += left + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            arrowRight += right + 1;
        }
        ans = Math.max(ans, arrowLeft + arrowRight);
        return Math.max(arrowLeft, arrowRight);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
