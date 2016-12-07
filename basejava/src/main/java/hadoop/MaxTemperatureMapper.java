package hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

/**
 * Created by liubo on 16/9/2.
 */
public class MaxTemperatureMapper extends MapReduceBase implements Mapper<LongWritable,Text,Text,IntWritable> {
    private static final int MISSING = 9999;
    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
        String line = value.toString();
        String year = line.substring(15,19);
        int airTemperature ;
        if (line.charAt(87)=='+'){
            airTemperature = Integer.parseInt(line.substring(88,92));
        }else {
            airTemperature = Integer.parseInt(line.substring(87,92));
        }
        String quality = line.substring(92,93);
        if (airTemperature!=MISSING && quality.matches("[01459]")){

        }
    }
}
