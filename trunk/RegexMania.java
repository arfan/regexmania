import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.*;
import java.io.*;

public class RegexMania extends JFrame implements ActionListener
{
	private JSplitPane io;
	private JPanel regexPanel;
	private JTextArea input;
	private JScrollPane inputScroll;
	
	private JTextArea output;
	private JScrollPane outputScroll;
	
	private Container container;
	
	private JTextArea regex;
	private JScrollPane regexScroll;
	private JTextField group;
		
	private JButton submit;
		
	public RegexMania()
	{
		super("RegexMania by Abdul Arfan: abdul.arfan@gmail.com");
		regexPanel   = new JPanel();
		input  = new JTextArea("");
		inputScroll = new JScrollPane(input);
		inputScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		output = new JTextArea("");
		outputScroll = new JScrollPane(output);
		outputScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		io	   = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputScroll,outputScroll);
		container = getContentPane();
		
		submit = new JButton("Submit");
		
		Font font = new Font("Monospaced",Font.PLAIN,20);
		
		regex = new JTextArea("", 1, 45) ;
		group = new JTextField(5);
		
		regex.setFont(font);
		group.setText("0");
		setLayout(new BorderLayout());
		//io.setLayout(new GridLayout(1,2));
		
		regexScroll = new JScrollPane(regex);
		regexScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER) ;
		regexScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS) ;
		FlowLayout flow = new FlowLayout();
		flow.setAlignment(FlowLayout.LEFT); 
		regexPanel.setLayout(flow);
		regexPanel.add(new JLabel("regex"));
		regexPanel.add(regexScroll);
		regexPanel.add(new JLabel("group"));
		regexPanel.add(group);
		
		regexPanel.add(submit);
		
		//io.add(inputScroll);
		//io.add(outputScroll);
		
		container.add(io, BorderLayout.CENTER);
		container.add(regexPanel, BorderLayout.NORTH);
		
		io.setResizeWeight(0.5);
		submit.addActionListener(this);
		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent act)
	{
		//System.out.println(act);
		
		String masuk = input.getText();
		
		Pattern pattern = Pattern.compile(regex.getText());
		Matcher matcher = pattern.matcher(masuk);
		
		StringTokenizer token = new StringTokenizer(group.getText(), ";, ");
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
		
		output.setText(buff.toString());
	}
	public static void main(String args[])
	{
		new RegexMania();
	}
}









