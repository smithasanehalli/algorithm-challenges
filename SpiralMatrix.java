package com.pearson.coding.challenges;

import java.util.Scanner;

/**
 * @author smitha
 *
 */
public class SpiralMatrix {
	
	StringBuffer sb = new StringBuffer();

	/**
	 * @param matrix - 2d array
	 * @param rowIdx - row index for spiral
	 * @param colIdx - col index for spiral
	 * @param n
	 */
	public void print(int[][] matrix, int rowIdx, int colIdx, int n) {
		  int rx = rowIdx;
          int cx = colIdx;
          int rowSize = matrix.length;
          int colSize = matrix[0].length;

          if ((rx <= 0 && cx >= colSize) || (rx >= rowSize && cx <= 0))
              return;

          int sqLen = n + 2; //3

          rx = rx - 1;
          for (int i = colIdx; i >= colIdx - (sqLen - 2); --i)
          {                
              if (rx >= 0 && rx < rowSize && i >= 0 && i < colSize)
                  System.out.println(matrix[rx][i] + " ");
          }

          cx = colIdx - (sqLen - 2);
          for (int j = rowIdx; j <= rowIdx + (sqLen - 2); ++j)
          {                
              if (j >= 0 && j < rowSize && cx >= 0 && cx < colSize)
            	  System.out.println(matrix[j][cx] + " ");
          }

          rx = rowIdx + (sqLen - 2);
          for (int k = colIdx + (3 - sqLen); k <= colIdx + 1; ++k)
          {                
              if (rx >= 0 && rx < rowSize && k >= 0 && k < colSize)
            	  System.out.println(matrix[rx][k] + " ");
          }

          cx = colIdx + 1;
          for (int m = rowIdx + (sqLen - 3); m >= rowIdx - 1; --m)
          {                
              if (m >= 0 && m < rowSize && cx >= 0 && cx < colSize)
            	  System.out.println(matrix[m][cx] + " ");
          }

          //Recursive
          print(matrix, rowIdx - 1, colIdx + 1, sqLen);
     }

	public static void main(String[] args) {
		// Sample input 5 5 3 3

		Scanner scanner = new Scanner(System.in);
		int row = Integer.parseInt(scanner.next());
		int col = Integer.parseInt(scanner.next());
		int rowIn = Integer.parseInt(scanner.next());
		int colIn = Integer.parseInt(scanner.next());
		scanner.close();
		
		if(((rowIn > row  || rowIn <= 0) || (colIn > col || colIn <= 0 ))) {
			System.out.println("Invalid input Please try again!");
			System.exit(0);
		}

		
		// create 2d matrix and initialize with values from 1 to row * col;
		int[][] matrix = new int[row][col];
		int value = 1;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print((matrix[i][j] = value++) + "\t");
			}
			System.out.println();
		}
		System.out.println("");

		// Print values from spiral
		int rowIndex = rowIn - 1;
		int colIndex = colIn - 1;
		SpiralMatrix spiralCh = new SpiralMatrix();
		System.out.println(matrix[rowIndex][colIndex]);
		spiralCh.print(matrix, rowIndex, colIndex, 1);

	}

}
