/**
 * Created by thanatos on 2018/6/2.
 * <p>
 * 快速排序
 * 平均时间O(nlogn)，最坏O(n^2)，不稳定
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = new int[]{26, 53, 67, 48, 57, 13, 48, 32, 60, 50};

        ShellSort.shellSort(arr);

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    /**
     * 希尔排序
     *
     * @param arr 待排序数组
     */
    public static void shellSort(int[] arr) {
        if (null == arr || 0 == arr.length) {
            return;
        }

        int j = 0;
        //增量每次减半
        for (int increment = arr.length / 2; increment > 0; increment /= 2) {
            for (int i = increment; i < arr.length; i++) {
                int temp = arr[i];
                for (j = i - increment; j >= 0; j -= increment) {
                    if (temp < arr[j]) {
                        arr[j + increment] = arr[j];
                    } else {
                        break;
                    }
                }
                arr[j + increment] = temp;
            }
        }
    }
}
