package test;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;

public class Reducer extends org.apache.hadoop.mapreduce.Reducer<LongWritable, LongWritable, LongWritable, LongWritable> {
	public void reduce(LongWritable key, Iterable<LongWritable> values, Context context
			) throws IOException, InterruptedException {
		int i = 0;
		for (LongWritable it : values) i += it.get();
		System.out.println(Thread.currentThread().getId() + ":" + key.get() + "redddddce" + i);
		context.write(key, new LongWritable(i));
	}
}
