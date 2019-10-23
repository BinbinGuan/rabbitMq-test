package utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author: GuanBin
 * @date: Created in 下午2:46 2019/10/23
 */
public class StringUtilsTest {

    public static void main(String[] args) {
        //isWhitespace() 方法用于判断指定字符是否为空白字符，空白符包含：空格、tab 键、换行符。
//        System.out.println(Character.isWhitespace('c'));
//        System.out.println(Character.isWhitespace(' '));
//        System.out.println(Character.isWhitespace('\n'));
//        System.out.println(Character.isWhitespace('\t'));


        String str="c";
        String str1="";
        String str2=" ";
        String str3="\n";
        String str4="\t";

        System.out.println("is blank:" + checkStrIsBlank(str1));
        System.out.println("is empty:" + checkStrIsEmpty(str1));
    }

    /**
     * StringUtils.isBlank的实现比StringUtils.isEmpty多了空白符的判断
     * 空白符：空格、tab 键、换行符。
     * 如果需要空白符的判断最好用StringUtils.isBlank
     * @return
     */
    private static boolean checkStrIsBlank(String str){
        return StringUtils.isBlank(str);
    }

    /**
     * StringUtils.isEmpty的判断 (cs == null || cs.length() == 0);
     * @param str
     * @return
     */
    private static boolean checkStrIsEmpty(String str){
        return StringUtils.isEmpty(str);
    }
}
