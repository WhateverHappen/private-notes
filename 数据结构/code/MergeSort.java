/**
 * Created by admin on 2018/7/4.
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] test = {1, 1, 2, 0, 9, 3, 12, 7, 8, 3, 4, 65, 22};

        MergeSort.mergeSort(test, 0, test.length - 1);
        for (int i : test) {
            System.out.print(i + " ");
        }
    }

    /**
     * 归并排序
     *
     * @param a
     * @param low
     * @param high
     * @return
     */
    public static int[] mergeSort(int[] a, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            mergeSort(a, low, mid);
            mergeSort(a, mid + 1, high);
            //左右归并
            merge(a, low, mid, high);
        }
        return a;
    }

    /**
     * 数组归并
     *
     * @param a
     * @param low
     * @param mid
     * @param high
     */
    public static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = a[j++];
        }
        // 把新数组中的数覆盖nums数组
        for (int x = 0; x < temp.length; x++) {
            a[x + low] = temp[x];
        }
    }

}
