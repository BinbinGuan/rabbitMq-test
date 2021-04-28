package leetcode.listnode;

public class MinArray {
    public static void main(String[] args) {
        int[] numbers={5,4,3,2,1};
        System.out.println(minArray(numbers));
    }

    public static int minArray(int[] numbers) {
        int low=0;
        int high=numbers.length-1;
        while (low<high){
            int prvot=low+(high-low)/2;
            if(numbers[prvot]<numbers[high]){
               high=prvot;
            }else if(numbers[prvot]>numbers[high]){
                low=prvot+1;
            }else {
                high-=1;
            }
        }
        return numbers[low];
    }
}
