package basic.file;

import java.io.*;

/**
 * Created by liubo on 16/7/16.
 */
public class LargeFileRead {

    private void largeFileRandomAccess(String inputFile,String outputFile){
        try {
            RandomAccessFile br = new RandomAccessFile(inputFile,"r");
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile,true));
            StringBuffer sb =null;
            while (new StringBuffer(br.readLine()).length()>0){
                bw.write(br.readLine());
                sb=null;
            }
            br.close();
            bw.flush();
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void largeFileBuffered(String inputFile,String outputFile){
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(inputFile)));//create input stream
            BufferedReader br = new BufferedReader(new InputStreamReader(bis,"utf-8"),10*1024*1024);//create input stream
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile,true),10*1024*1024);//create output stream
            while (br.ready()){
                bw.append(br.readLine());
            }
            br.close();
            bis.close();
            bw.flush();
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
