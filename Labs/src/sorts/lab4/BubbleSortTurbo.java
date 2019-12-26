package sorts.lab4;

public class BubbleSortTurbo {

    public BubbleSortTurbo() {
        int len = 10000;
        int[] arr = new int[len];
        System.out.println("Array: ");
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 100001 - 50000);
            System.out.print(arr[i] + ", ");
        }
        long time = System.nanoTime();
        bubbleSortTurbo(arr);
        time = System.nanoTime() - time;
        System.out.println("\nSorted array: ");
        for (int i : arr)
            System.out.print(i + " ");

        System.out.println("\nTime: " + time);
    }

    private void bubbleSortTurbo(int[] arr) {
        int k;
        int n = arr.length;
        do {
            k = 0;
            for (int i = 1; i < n; i++) {
                if (arr[i] < arr[i - 1]) {
                    int tmp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = tmp;
                    k++;
                }
            }
        } while (k != 0);
    }

    public static void main(String[] args) {
        BubbleSortTurbo sort = new BubbleSortTurbo();
    }
}
