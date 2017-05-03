package day3part2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class Assignment323 {
	public static void main(String[] args) throws Exception {
		BufferedReader inp=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter the source path in local file system: ");
		String localSrc = inp.readLine();
		
		System.out.println("Enter the destination path in HDFS: ");
		String dst = inp.readLine();
		
		copyFile(localSrc, dst);

	}
	
	
	public static void copyFile(String localSrc,String dst) throws IOException
	{

		InputStream in = new BufferedInputStream(new FileInputStream(localSrc));
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(dst), conf);
		OutputStream out = fs.create(new Path(dst));
		
		IOUtils.copyBytes(in, out, 4096, true);
	}
}