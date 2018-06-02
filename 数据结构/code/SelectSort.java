/**
 * Created by thanatos on 2018/6/2.
 * <p>
 * 简单选择排序
 * 时间复杂度为O(n^2)，稳定
 */
public class SelectSort {
    public static void main(String[] args) {
        int arr[] = {49, 38, 65, 97, 76, 13, 27, 49};

        SelectSort.selectSort(arr);

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    /**
     * 简单选择排序
     *
     * @param arr 待排序数组
     */
    public static void selectSort(int[] arr) {
        if (null == arr || 0 == arr.length) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            int k = i;
            for (int j = i + 1; j < arr.length; j++) {
                //查找最小数的下标
                if (arr[j] < arr[k]) {
                    k = j;
                }
            }

            //将最小数与未排序序列的第一位交换
            if (k > i) {
                int temp = arr[k];
                arr[k] = arr[i];
                arr[i] = temp;
            }
        }
    }

}
