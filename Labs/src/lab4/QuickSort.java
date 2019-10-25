package lab4;

public class QuickSort {

    public QuickSort() {
        int len = 100;
        int[] arr = new int[len];
        System.out.println("Array: ");
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 201 - 100);
            System.out.print(arr[i]+" ");
        }
        quickSort(arr);
        System.out.println("\nSorted array: ");
        for(int i:arr)
            System.out.print(i+" ");
    }

    private static void quickSort(int[] arr) {
        int startIndex = 0;
        int endIndex = arr.length - 1;
        doSort(arr, startIndex, endIndex);
    }

    private static void doSort(int[] arr, int start, int end) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (arr[i] <= arr[cur])) {
                i++;
            }
            while (j > cur && (arr[cur] <= arr[j])) {
                j--;
            }
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        doSort(arr, start, cur);
        doSort(arr, cur + 1, end);
    }

    public static void main(String[] args) {
        QuickSort sort =new QuickSort();
    }
}
