package basic.thread.sync.cyclicbarrier;

import java.util.Random;

/**
 * Created by liubo on 16/7/19.
 */
public class MatrixMock {
    private int[][] data;

    public MatrixMock(int size,int length,int number) {
        int counter =0;
        data =new  int[size][length];
        Random random = new Random();

        for (int i = 0; i <size; i++) {
            for (int j = 0; j < length; j++) {
                data[i][j] = random.nextInt(10);
                if (data[i][j]==number){
                    counter++;
                }
            }
        }
        System.out.printf("有%d个%d存在数组中\n",counter,number);
    }
    public int[] getRow(int row){
        if (row>0&&row<data.length){
            return data[row];
        }
        return null;
    }
}
