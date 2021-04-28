package leetcode;

/**
 * @author: GuanBin
 * @date: Created in 上午9:25 2021/4/15
 */
public class StringTest {

    private static int m;
    public static void main(String[] args) {
        String str="hello";
        int length = str.length();
        char[] newChar=new char[length];
        char[] chars1 = str.toCharArray();
        int index=0;
        for (int i=length-1;i>=0;i--){
            newChar[index++]=chars1[i];
        }


        String s = new String(newChar, 0, index);
        System.out.println(s);

        String a="asdfasdf";
        String k = a.replace("a", "k");
        System.out.println(m);



    }
}
