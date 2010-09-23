import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.*;
import java.io.*;

public class RMania
{
	public RMania(String fileName, String regexFile,String group) throws Exception
	{
		String isiFile = removeAllNewLine(readFileAsString(fileName).trim());
		String regex = removeAllNewLine(readFileAsString(regexFile).trim());
		System.out.println(perform(isiFile, regex, group));
	}
    
    public static String removeAllNewLine(String s)
    {
        return s.replace("\n", "");
    }
    
    public  static String readFileAsString(String filePath) throws java.io.IOException{
        byte[] buffer = new byte[(int) new File(filePath).length()];
        BufferedInputStream f = new BufferedInputStream(new FileInputStream(filePath));
        f.read(buffer);
        return new String(buffer);
    }

	
	public static String perform(String masuk, String regex, String group)
	{
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(masuk);
		
		StringTokenizer token = new StringTokenizer(group, ";, ");
		int n = token.countTokens();
		//System.out.println("n="+n);
		
		StringBuffer buff = new StringBuffer();
		
		int grup[] = new int[n];
		
		for(int i=0;i<n;i++)
		{
			grup[i] = Integer.parseInt(token.nextToken());
		}
		
		out:
		while(matcher.find())
		{
			//if(matcher.group(3)!=null) System.out.print("["+matcher.group(3)+"] ");
			//if(matcher.group(2)!=null) System.out.println("["+matcher.group(2)+"]");
			
			for(int i=0;i<n;i++)
			{
				try
				{
					String hasil = matcher.group(grup[i]);
						
					if(hasil.length()>0)
					{
						//System.out.println("["+hasil+"]");
						buff.append(hasil+"\n");
					}
				}
				catch(IndexOutOfBoundsException e)
				{
					JOptionPane.showMessageDialog(null,"group ke "+grup[i]+" tidak ada");
					break out;
				}
			}
		}
		
        
		//output.setText(buff.toString());
        return buff.toString();
	}
	public static void main(String args[]) throws Exception
	{
        new RMania(args[0], args[1], args[2]);
	}
}









