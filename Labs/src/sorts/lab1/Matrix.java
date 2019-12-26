package sorts.lab1;

public class Matrix {
    public static void main(String[] args) {
        new Matrix();
    }

    public Matrix() {
        int chetn = 0;
        int nechetn = 0;
        int n = (int) (Math.random() * 9995) + 5;
        //int n=1000;
        System.out.println(n);
        long startTime = System.nanoTime();
        double[][] matrix = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (int) (Math.random() * 100);
                if (matrix[i][j] % 2 == 0) chetn++;
                else nechetn++;
            }
        System.out.println("Time: " + (System.nanoTime() - startTime));
        System.out.println("Четных: " + chetn);
        System.out.println("Нечетных: " + nechetn);
    }

}
