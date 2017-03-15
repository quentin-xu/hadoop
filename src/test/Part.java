package test;

import org.apache.hadoop.mapreduce.Partitioner;

public class Part<KEY, VALUE> extends Partitioner<KEY, VALUE> {

	@Override
	public int getPartition(KEY key, VALUE value, int numPartitions) {
		// TODO Auto-generated method stub
		return 0;
	}

}
