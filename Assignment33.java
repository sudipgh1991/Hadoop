package assignments;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
public class Assignment33 {

	public static void main(String[] args)throws IOException,URISyntaxException,InterruptedException {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the HDFS paths separated by commas: ");
		String filePath=sc.next();
		//Split the path by space
		String []path=filePath.split(" ");
		for(String file:path)
		{
			System.out.println("Files under the path "+file+" is: ");
			printFile(file);
		}
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
