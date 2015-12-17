package sort;

/**
 * Created by vishalss on 12/17/2015.
 */
public class OrderStatisticsExample {
    // Order Statistics.

    public static void main(String[] args) {
        Integer a[]={3,6,23,577,58,4,7,1};
        System.out.println("Input array");
        Sort.printArray(a, 1, "");
        int k=1;
        for(k=1;k<=a.length;k++){
            int temp=OrderStatistics.orderStatistics(a,k,0,a.length);
            System.out.println("K="+k+"= "+ temp);
        }
    }
}
