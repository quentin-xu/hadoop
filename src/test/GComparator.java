package test;

import java.io.IOException;

import org.apache.hadoop.io.DataInputBuffer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.RawComparator;

public class GComparator implements RawComparator {

	@Override
	public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {

		
		DataInputBuffer buffer = new DataInputBuffer();
		LongWritable lh = new LongWritable();
		LongWritable rh = new LongWritable();


		try {
			buffer.reset(b1, s1, l1);                   // parse key1
			lh.readFields(buffer);

			buffer.reset(b2, s2, l2);                   // parse key2
			rh.readFields(buffer);

			System.out.println(Thread.currentThread().getId() + "!!! " + lh.get() + " gcmp " + rh.get());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return 1;
		//return (int) (lh.get() % 10 - rh.get() % 10);
	}

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		//return ((LongWritable)o1).compareTo((LongWritable)o2);
		return 0;
	}


}
