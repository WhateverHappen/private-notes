/**
 * Created by thanatos on 2018/6/1.
 * <p>
 * 插入排序
 * 平均O(n^2),最好O(n),最坏O(n^2);稳定
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 76, 13, 27, 50};

        insertSort(arr);

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    /**
     * 直接插入排序
     *
     * @param arr 待排序数组
     */
    public static void insertSort(int[] arr) {
        if (null == arr || 0 == arr.length) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
