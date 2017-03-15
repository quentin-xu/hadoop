package test;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



public class VDeal {

	
	
	public static void main(String[] args) {
	    Configuration conf = new Configuration();
	    Job job;
	    
	    /**
	     * 系统调用的还是job里面的方法，conf作为配置的不同class之间传递参数的中间配置数据
	     * 
	     */
	    
	   System.out.println("debug info");
	    
		try {
			//Job里面有一个JobConf，是用上面的conf初始化的，实际使用时，设置Job里面的conf才能生效
			//所以wc的示例里面不使用ob.getInstance()的目的只是为了设置jobname
			job = Job.getInstance(conf, "vdeal");
			job.setJarByClass(VDeal.class);
			job.setInputFormatClass(Input.class);
			job.setMapperClass(Mapper.class);
			//job.setCombinerClass(Reducer.class);
			job.setReducerClass(Reducer.class);
			job.setOutputKeyClass(LongWritable.class);
			job.setOutputValueClass(LongWritable.class);
			/* NOTE:
			 * setGroupingComparatorClass 会把判断是相同key的数据一次调用传给reduce
			 * 调用setGroupingComparatorClass 和调用reduce可能是交叉进行的
			 * 调用发生在combiner之后
			 * setCombinerKeyGroupingComparatorClass 会把判断是相同key的数据一次调用传给combiner的reduce
			 * 调用发生在map之后combiner之前
			 * 调用setCombinerKeyGroupingComparatorClass之前会先调用setSortComparatorClass
			 * sort会影响传入reduce的顺序，另外也会影响分组
			 * group的判断是建立在sort结果之上的，不会进行全局的比较判断是否相等
			*/
			//job.setGroupingComparatorClass(GComparator.class);
			//job.setCombinerKeyGroupingComparatorClass(GComparator.class);
			job.setSortComparatorClass(KComparator.class);
			//默认的InputFormatClass是TextInputFormat(继承了FileInputFormat)
			//默认的OutputFormatClass是TextOutputFormat(继承了FileOutputFormat)
			//job.getInputFormatClass();
			//job.setOutputFormatClass(cls);
			//TODO:为什么是0和1，不应该是1和2么？？？？
			job.getConfiguration().set(Input.INPUT_FILE, args[0]);
			//job.getConfiguration().set(Output.OUTDIR, args[1]);
			//FileInputFormat.addInputPath(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			System.exit(job.waitForCompletion(true) ? 0 : 1);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
