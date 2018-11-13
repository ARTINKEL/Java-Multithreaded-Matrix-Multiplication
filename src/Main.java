import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int[][] A = createMatrix();
        int[][] B = createMatrix();
        int[][] C = new int[A.length][B.length];

        WorkerThread[][] workers = new WorkerThread[A.length][B.length];

        if (A[0].length == B.length) {
            for (int iRow = 0; iRow < A.length; iRow++) {
                for (int jCol = 0; jCol < A.length; jCol++) {
                    workers[iRow][jCol] = new WorkerThread(iRow, jCol, A, B, C);
                    workers[iRow][jCol].run();
                }
            }

            System.out.println("\nResult: ");
            printMatrix(C);
        } else {
            System.out.println("\nError: Solution is undefined. Columns of first matrix have to be equal to rows of the second matrix.");
        }
    }

    private static int[][] createMatrix() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of rows and then number of columns: ");
        int rowCount = scanner.nextInt();
        int colCount = scanner.nextInt();

        int[][] matrix = new int[rowCount][colCount];

        System.out.println("Enter the numbers for the matrix");

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                System.out.printf("[%d][%d]: ", i, j);
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        System.out.println("Elements List:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <matrix[i].length; j++) {
                System.out.println("[" + i + "," + j + "] = " + matrix[i][j]);
            }
        }

        System.out.println("\nFormatted Matrix:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}

class WorkerThread implements Runnable {

    private int row;
    private int col;
    private int[][] A;
    private int[][] B;
    private int[][] C;

    public WorkerThread(int row, int col, int[][] A, int[][] B, int[][] C) {
        this.row = row;
        this.col = col;
        this.A = A;
        this.B = B;
        this.C = C;
    }

    @Override
    public void run() {
        for (int i = 0; i < B.length; i++) {
            C[row][col] += A[row][i] * B[i][col];
        }
    }
}