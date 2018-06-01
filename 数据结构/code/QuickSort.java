/**
 * Created by thanatos on 2018/5/31.
 * <p>
 * 快速排序
 * 平均O(nlogn)，最坏O(n^2)，不稳定
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {1, 9, 3, 12, 7, 8, 3, 4, 65, 22};

        QuickSort.quickSort(arr, 0, arr.length - 1);

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    /**
     * 快排函数
     *
     * @param arr  需要进行快排的序列
     * @param head 序列左边界
     * @param rear 序列右边界
     */
    public static void quickSort(int[] arr, int head, int rear) {
        if (head >= rear || null == arr || 0 == arr.length) {
            return;
        }

        //进行第一次快排，将整个序列分为前后两段
        int index = partition(arr, head, rear);
        //前半段再次进行快排
        quickSort(arr, head, index - 1);
        //后半段再次进行快排
        quickSort(arr, index + 1, rear);
    }

    /**
     * 一次快速排序
     *
     * @param arr  需要进行快排的序列
     * @param head 序列左边界
     * @param rear 序列右边界
     * @return 序列分片的间隔点，下标index
     */
    public static int partition(int[] arr, int head, int rear) {
        int index = arr[head];
        while (rear > head) {
            //从后部向前扫描
            while (rear > head && arr[rear] >= index) {
                rear--;
            }
            arr[head] = arr[rear];

            //从前部向后扫
            while (rear > head && arr[head] < index) {
                head++;
            }
            arr[rear] = arr[head];
        }
        arr[rear] = index;
        return rear;
    }

}
