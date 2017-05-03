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
public class Assignment322 {

	public static void main(String[] args)throws IOException,URISyntaxException {
		// TODO Auto-generated method stub
		BufferedReader inp=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter File Path :");
		String filePath=inp.readLine();
		getContent(filePath);
	}
	
	public static void getContent(String filePath)
	{

		Path path = new Path(filePath);
		try{
			Configuration conf = new Configuration();
			FileSystem fileSystem = FileSystem.get(path.toUri(), conf);
			FileStatus[] fileStatus=fileSystem.listStatus(path);
			BufferedReader br=new BufferedReader(new InputStreamReader(fileSystem.open(path)));
			String line;
			line=br.readLine();
			while(line!=null)
			{
				System.out.println(line);
				line=br.readLine();
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
