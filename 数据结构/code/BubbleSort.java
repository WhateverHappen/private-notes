/**
 * Created by thanatos on 2018/5/30.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] test = {1, 1, 2, 0, 9, 3, 12, 7, 8, 3, 4, 65, 22};

        BubbleSort.bubbleSort3(test);
        for (int i : test) {
            System.out.print(i + " ");
        }
    }

    /**
     * 基本的冒泡排序，未经过任何优化
     *
     * @param arr 待排序数组
     */
    public static void bubbleSort1(int[] arr) {
        for (int i = arr.length; i > 0; i--) {
            for (int j = 1; j < i; j++) {
                if (arr[j - 1] > arr[j]) {  //前面的数字大于后面的数字就交换
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }

    /**
     * 设置一个标志位，如果这一趟发生了交换，则为true，否则为false。
     * 明显如果有一趟没有发生交换，说明排序已经完成。
     *
     * @param arr 待排序数组
     */
    public static void bubbleSort2(int[] arr) {
        Boolean flag = true;    //若发生交换则为true，未发生交换则为false。第一次判断必须为true。

        int rear = arr.length;
        while (flag) {
            flag = false;   //每次排序前，重置flag

            for (int j = 1; j < rear; j++) {    //前面的数字大于后面的数字就交换
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;

                    flag = true;    //发生过数据交换，修改flag
                }
            }

            rear--; //减小排序尾边界
        }
    }

    /**
     * 设置一个标志位，用来记录上一次排序的最后一次交换位置，下一次排序只需从头遍历至该位置即可。
     *
     * @param arr 待排序数组
     */
    public static void bubbleSort3(int[] arr) {
        int flag = arr.length;  //用来记录最后一次交换的位置，也即排序的尾边界

        while (flag > 0) {
            int k = flag;   //k用来记录遍历尾边界
            flag = 0;   //若最后一次交换都没用进行，则可以跳出循环

            for (int j = 1; j < k; j++) {   //前面的数字大于后面的数字就交换
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;

                    flag = j;    //记录最新尾边界
                }
            }
        }
    }

}
