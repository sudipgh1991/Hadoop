package assignments;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
public class Assignment32 {

	public static void main(String[] args)throws IOException,URISyntaxException,InterruptedException {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the HDFS path: ");
		String filePath=sc.next();
		printFile(filePath);
	}
		
		
		public static void printFile(String filePath)
		{
			
			Path path = new Path(filePath);
			
			try
			{
				Configuration conf = new Configuration();
				FileSystem fileSystem = FileSystem.get(path.toUri(), conf);
				FileStatus[] fileStatus=fileSystem.listStatus(path);
				
				for (FileStatus fStat : fileStatus) {
					if (fStat.isDirectory()) {
						System.out.println("Directory: " + fStat.getPath());
						printFile(fStat.getPath().toString());
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
