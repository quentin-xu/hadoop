package test;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

public class Mapper extends org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, LongWritable, LongWritable> {

	  protected void map(LongWritable key, Text value,
	                     Context context) throws IOException, InterruptedException {
		  //System.out.println(value.toString());
		  String[] fileds = value.toString().split("\t", -1);
		  //System.out.println(fileds.length);
		  //if (fileds.length != 94) System.out.println(value.toString());
		  //if (filed)
		  long skuid = new Long(fileds[2]);
		  long num = new Long(fileds[3]);
		  context.write(new LongWritable(skuid), new LongWritable(num));
	  }
	
}
