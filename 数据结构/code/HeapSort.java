/**
 * Created by admin on 2018/7/3.
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] test = {1, 1, 2, 0, 9, 3, 12, 7, 8, 3, 4, 65, 22};

        HeapSort.heapSort(test);
        for (int i : test) {
            System.out.print(i + " ");
        }
    }

    /**
     * 堆排序
     *
     * @param array
     */
    public static void heapSort(int[] array) {
        // 1. 创建最大堆：从最后一个节点的父节点开始
        int lastIndex = array.length - 1;
        int startIndex = (lastIndex - 1) / 2;
        for (int i = startIndex; i >= 0; i--) {
            maxHeap(array, array.length, i);
        }
        // 2. 排序：末尾与头交换，逐一找出最大值，最终形成一个递增的有序序列
        for (int i = array.length - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            maxHeap(array, i, 0);
        }
    }

    /**
     * 建立大根堆
     *
     * @param data
     * @param heapSize
     * @param index
     */
    private static void maxHeap(int[] data, int heapSize, int index) {
        // 左子节点
        int leftChild = 2 * index + 1;
        // 右子节点
        int rightChild = 2 * index + 2;
        // 最大元素下标
        int largestIndex = index;
        // 分别比较当前节点和左右子节点，找出最大值
        if (leftChild < heapSize && data[leftChild] > data[largestIndex]) {
            largestIndex = leftChild;
        }
        if (rightChild < heapSize && data[rightChild] > data[largestIndex]) {
            largestIndex = rightChild;
        }
        // 如果最大值是子节点，则进行交换
        if (largestIndex != index) {
            int temp = data[index];
            data[index] = data[largestIndex];
            data[largestIndex] = temp;
            // 交换后，其子节点可能就不是最大堆了，需要对交换的子节点重新调整
            maxHeap(data, heapSize, largestIndex);
        }
    }
}
