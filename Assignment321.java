package day3part2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
public class Assignment321 {

	public static void main(String[] args)throws IOException,URISyntaxException,InterruptedException,ParseException {
		// TODO Auto-generated method stub
		BufferedReader inp=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter File Path :");
		String filePath=inp.readLine();
		System.out.println("Enter Start Date (dd/MM/yyyy hh:mm:ss):");
		String stdate=inp.readLine();
		System.out.println("Enter End Date(press enter if you want to skip) :");
		String enddate=inp.readLine();
		
		printFile(filePath,stdate,enddate);
	}
		
		
		public static void printFile(String filePath,String stdate,String enddate) throws ParseException
		{
			
			Path path = new Path(filePath);
			
			try
			{
				Configuration conf = new Configuration();
				FileSystem fileSystem = FileSystem.get(path.toUri(), conf);
				FileStatus[] fileStatus=fileSystem.listStatus(path);
				HashMap modifiedList=new HashMap();
				
				for (FileStatus fStat : fileStatus) {
					if (fStat.isDirectory()) {
						//System.out.println("Directory: " + fStat.getPath());
						printFile(fStat.getPath().toString(),stdate,enddate);
					}
					else if (fStat.isFile()) {
						//System.out.println("File: " + fStat.getPath());
						
						SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
						Date startdate=sdf.parse(stdate);
						long stmilli=startdate.getTime();
						Date endDate=new Date();
						if(!enddate.isEmpty())
						{
							endDate=sdf.parse(enddate);
						}
						
						
						
						long endmilli=endDate.getTime();
						
						
						
						if(fStat.getModificationTime()>=stmilli && fStat.getModificationTime()<=endmilli)
						{
						
						DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
						Calendar calendar=Calendar.getInstance();
						calendar.setTimeInMillis(fStat.getModificationTime());
						String formatTime=formatter.format(calendar.getTime());
						
//						modifiedList.put(fStat.getPath(), fStat.getModificationTime());
						modifiedList.put(fStat.getPath(), formatTime);
						}
						
						
					}
					else if (fStat.isSymlink()) {
						//System.out.println("Symlink: " + fStat.getPath());
					}
				}
				printFileNames(modifiedList);
			}
			catch (IOException e)
			{
	            e.printStackTrace();
			}
		}
		
		public static void printFileNames(HashMap list)
		{
			
			for(Object obj:list.keySet())
			{
				
				System.out.print("File Path:"+obj.toString());
				System.out.println("---Modified last: "+list.get(obj));
				System.out.println("------------------------");
			}
		}
	

}
