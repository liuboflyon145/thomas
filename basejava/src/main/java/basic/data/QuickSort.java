package basic.data;

/**
 * Created by liubo on 16/7/15.
 */
public class QuickSort {
    public void sort(int[] data, int start, int end){

        int left = start;
        int right = end;
        int temp;
        if(start<end){
            while(left<right){

                while(left<end&&data[left]<=data[start]){
                    left++;
                }
                while(right>start&&data[right]>data[start]){
                    right--;
                }
                if(left<right){
                    swap(data,left,right);
                }
            }
            swap(data,start,right);
            this.sort(data, start, right-1);
            this.sort(data, right+1, end);

        }
    }
    private void swap(int[] arrays, int left, int right) {
        int temp;
        temp = arrays[left];
        arrays[left] = arrays[right];
        arrays[right] = temp;
    }

    public static void main(String args[]) {
        QuickSort q = new QuickSort();
        int[] a = {-1, 49, 38, 65,12,45,5 };
        long start = System.nanoTime();
        System.out.println(start);
        q.sort(a,0,a.length-1);
        long end = System.nanoTime();
        System.out.println(end);
        System.out.println(end-start);

        for(int i=0;i<a.length;i++){
            System.out.print(a[i] + " ");
        }
    }
}
