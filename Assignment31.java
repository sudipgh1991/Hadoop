package assignments;
import java.io.*;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileStatus;
public class Assignment31 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		if (args.length != 1) {
//			System.out.println("Pass one argument");
//			System.exit(1);
//		}
		
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the HDFS path: ");
		String filePath=sc.next();
		
		Path path = new Path(filePath);
		
		try
		{
			Configuration conf = new Configuration();
			FileSystem fileSystem = FileSystem.get(path.toUri(), conf);
			FileStatus[] fileStatus=fileSystem.listStatus(path);
			
			for (FileStatus fStat : fileStatus) {
				if (fStat.isDirectory()) {
					System.out.println("Directory: " + fStat.getPath());
				}
				else if (fStat.isFile()) {
					System.out.println("File: " + fStat.getPath());
				}
				else if (fStat.isSymlink()) {
					System.out.println("Symlink: " + fStat.getPath());
				}
			}
		}
		catch (IOException e)
		{
            e.printStackTrace();
		}
	}

}
