package basic.erfenfa;

/**
 * Created by liubo on 16/11/4.
 */
public class TwoHalfAlgorithm {
    public static void main(String[] args) {
        int[] arr = new int[]{5 ,10,19,21,31,37,42,48,50,52,65};
        int result = binSearch(arr,1,11,48);
        System.out.println("index: "+result+" value: "+arr[result]);
    }

    private static int binSearch(int[] arr,int low,int high,int key){
        System.out.println("----------");
        if (low<=high){
            int middle = (low+high)/2;
            if (arr[middle]==key){
                return middle;
            }else if (key<arr[middle]){
               return binSearch(arr,low,middle-1,key);
            }else {
                return binSearch(arr,middle+1,high,key);
            }
        }else {
            return -1;
        }
    }
}
