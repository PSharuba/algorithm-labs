package sorts.lab2_3;

import java.util.Scanner;

public class BinarySearch {
    public Integer[] arr;
    public Scanner scanner;

    public BinarySearch() {
        scanner = new Scanner(System.in);
        /*System.out.println("Enter size of array: ");
        int n = scanner.nextInt();
        arr = new Integer[n];

        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * 1000 - 500);
        }
        Collections.sort(Arrays.asList(arr));
        scanner.reset();*/
        arr = new Integer[]{-494, -490, -490, -476, -473, -464, -460, -448, -446, -437, -436, -435, -428, -428, -423, -420, -419, -415,
                -408, -403, -393, -366, -364, -356, -348, -339, -337, -336, -307, -285, -264, -258, -247, -244, -216, -207, -159, -157,
                -151, -127, -124, -116, -105, -77, -66, -59, -49, -35, -27, -24, -21, -15, -15, -4, -1, 0, 11, 45, 49, 65, 117, 137, 139,
                149, 153, 167, 172, 187, 190, 201, 205, 211, 213, 217, 218, 224, 228, 248, 275, 276, 307, 314, 317, 340, 340, 351, 354,
                358, 370, 370, 378, 412, 417, 425, 437, 453, 466, 469, 481, 490};
        System.out.println("Array:");
        for (int i : arr)
            System.out.print(i + ", ");
        System.out.println();
        /*System.out.println("Enter element to search for: ");
        int search = scanner.nextInt();*/
        int search = -21;
        long startTime = System.nanoTime();
        //int position = binarySearch(search);
        int position = binarySearchRec(arr, search, 0, arr.length);
        System.out.println("Time: " + (System.nanoTime() - startTime));
        if (position != -1)
            System.out.println("Element '" + search + "' in " + (position + 1) + " position");
        else
            System.out.println("There aren't such element in array!");
    }

    public int binarySearch(int elementToSearch) {
        int firstIndex = 0;
        int lastIndex = arr.length - 1;
        int middleIndex;
        while (firstIndex <= lastIndex) {
            middleIndex = (firstIndex + lastIndex) / 2;
            if (arr[middleIndex] == elementToSearch) {
                return middleIndex;
            } else if (arr[middleIndex] < elementToSearch)
                firstIndex = middleIndex + 1;
            else if (arr[middleIndex] > elementToSearch)
                lastIndex = middleIndex - 1;
        }
        return -1;
    }

    public int binarySearchRec(Integer[] arr, int x, int firstIndex, int lastIndex) {
        if (firstIndex < lastIndex) {
            int middleIndex = firstIndex + (lastIndex - firstIndex) / 2;
            if (x < arr[middleIndex])
                return binarySearchRec(arr, x, firstIndex, middleIndex);
            else if (x > arr[middleIndex])
                return binarySearchRec(arr, x, middleIndex + 1, firstIndex);
            else
                return middleIndex;
        }
        return -(firstIndex + 1);
    }

    public static void main(String[] args) {
        new BinarySearch();
    }
    //semmi.val@mail.ru
}
