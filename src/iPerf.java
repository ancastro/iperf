
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import javaQuery.j2ee.GeoLocation;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
		
public class iPerf extends JFrame implements ActionListener{

	private static final int JFrame_W = 437;
	private static final int JFrame_H = 586;
	private static final int titlePanel_W = 438;
	private static final int titlePanel_H = 48;
	private static final int locationPanel_W = 438;
	private static final int locationPanel_H = 47;
	private static final int buttonPanel_W = 438;
	private static final int buttonPanel_H = 47;
	private static final int resultsPanel_W = 438;
	private static final int resultsPanel_H = 448;
	private static final int outputPanel_W = 371;
	private static final int outputPanel_H = 129;
	private static final int outputPanelTitle_W = 371;
	private static final int outputPanelTitle_H = 42;
	BackgroundPanel titlePanel = new BackgroundPanel("images/titlePanel.png");
	BackgroundPanel locationPanel = new BackgroundPanel("images/locationPanel.jpg");
	BackgroundPanel buttonPanel = new BackgroundPanel("images/locationPanel.jpg");
	BackgroundPanel resultsPanel = new BackgroundPanel("images/resultsPanel.png");
	BackgroundPanel pingResultsTitle = new BackgroundPanel("images/pingResultsTitle.png");
	BackgroundPanel iperfResultsTitle = new BackgroundPanel("images/iperfResultsTitle.png");
	JPanel pingResultsPanel = new JPanel();
	JPanel iperfResultsPanel = new JPanel();
	
	JLabel iperfTitle = new JLabel();
	JLabel cityTitleLabel =  new JLabel("City");
	JLabel cityResultLabel = new JLabel("");
	JLabel countryTitleLabel =  new JLabel("Country");
	JLabel countryResultLabel = new JLabel("");
	JLabel latitudeTitleLabel =  new JLabel("Latitude");
	JLabel latitudeResultLabel = new JLabel("");
	JLabel longitudeTitleLabel =  new JLabel("Longitude");
	JLabel longitudeResultLabel = new JLabel("");
	
	JScrollPane pingResultsScrollPane = new JScrollPane();
	JScrollPane iPerfResultsScrollPane = new JScrollPane();
	
	String iPerfResults;
	String pingResults;
    JTextArea iperfLabel = new JTextArea(iPerfResults);
    JTextArea pingLabel = new JTextArea(pingResults);
	
    ImageIcon runButtonUp = new ImageIcon("images/runButtonUp.png");
	ImageIcon runButtonDown = new ImageIcon("images/runButtonDown.png");
	ImageIcon runButtonOver = new ImageIcon("images/runButtonOver.png");
	JButton runButton = new JButton(runButtonUp);
	
	public static void main(String[] args){
		iPerf w = new iPerf( );
		w.setVisible(true);
	
	}

	public iPerf() {
		super();
		setSize(JFrame_W, JFrame_H);
		setResizable(false);
		setTitle("iPerf"); 
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		titlePanel.setBackground(Color.GRAY);
		titlePanel.setBounds(0, 0, titlePanel_W, titlePanel_H);
		Icon iperfTitleImage = new ImageIcon("images/iperfTitle.png");
		iperfTitle.setIcon(iperfTitleImage);
		titlePanel.add(iperfTitle);
		add(titlePanel);
		
		locationPanel.setBounds(0, titlePanel_H - 1, locationPanel_W, locationPanel_H);
		locationPanel.setBackground(Color.LIGHT_GRAY);
		locationPanel.setLayout(new GridLayout(2, 4));
		cityTitleLabel.setForeground(Color.decode("0x3EC6F3"));
		countryTitleLabel.setForeground(Color.decode("0x3EC6F3"));
		latitudeTitleLabel.setForeground(Color.decode("0x3EC6F3"));
		longitudeTitleLabel.setForeground(Color.decode("0x3EC6F3"));
		cityResultLabel.setForeground(Color.decode("0xFFFFFF"));
		countryResultLabel.setForeground(Color.decode("0xFFFFFF"));
		latitudeResultLabel.setForeground(Color.decode("0xFFFFFF"));
		longitudeResultLabel.setForeground(Color.decode("0xFFFFFF"));
		locationPanel.add(cityTitleLabel);
		locationPanel.add(countryTitleLabel);
		locationPanel.add(latitudeTitleLabel);
		locationPanel.add(longitudeTitleLabel);
		locationPanel.add(cityResultLabel);
		locationPanel.add(countryResultLabel);
		locationPanel.add(latitudeResultLabel);
		locationPanel.add(longitudeResultLabel);
		add(locationPanel);
		
		buttonPanel.setBounds(0, titlePanel_H + locationPanel_H - 2, buttonPanel_W, buttonPanel_H);
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		runButton.setRolloverIcon(runButtonOver);
		runButton.setPressedIcon(runButtonDown);
		runButton.setBounds(593, 380, 130, 45);
		runButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		runButton.setContentAreaFilled(false);
		runButton.addActionListener(this);
		buttonPanel.add(runButton);
		add(buttonPanel);
		
		//Results Panel
		resultsPanel.setBounds(0, titlePanel_H + locationPanel_H + buttonPanel_H - 4, resultsPanel_W, resultsPanel_H);
		resultsPanel.setBackground(Color.GRAY);
		pingResultsPanel.setBackground(Color.WHITE);
		pingResultsScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		pingResultsScrollPane.setPreferredSize(new Dimension(outputPanel_W, outputPanel_H));		
		pingResultsPanel.setBounds(30, 30 + titlePanel_H + locationPanel_H + buttonPanel_H, outputPanel_W, outputPanel_H);
		pingResultsScrollPane.setViewportView(pingLabel);
		pingResultsPanel.add(pingResultsScrollPane);
		
		pingResultsTitle.setBounds(30, 30 + titlePanel_H + locationPanel_H + buttonPanel_H + outputPanel_H, outputPanelTitle_W, outputPanelTitle_H);
		iperfResultsTitle.setBounds(30, 50 + titlePanel_H + locationPanel_H + buttonPanel_H + outputPanel_H + outputPanelTitle_H + outputPanel_H, outputPanelTitle_W, outputPanelTitle_H);
		
		iperfResultsPanel.setBackground(Color.WHITE);
		iPerfResultsScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		iPerfResultsScrollPane.setPreferredSize(new Dimension(outputPanel_W, outputPanel_H));
		iperfResultsPanel.setBounds(30, 50 + titlePanel_H + locationPanel_H + buttonPanel_H + outputPanel_H + outputPanelTitle_H, outputPanel_W, outputPanel_H);
		iPerfResultsScrollPane.setViewportView(iperfLabel);
		iperfResultsPanel.add(iPerfResultsScrollPane);
		
		add(pingResultsTitle);
		add(iperfResultsTitle);
		add(pingResultsPanel);
		add(iperfResultsPanel);
		add(resultsPanel);
		
		}

	public void actionPerformed(ActionEvent e) {
		String buttonString = e.getActionCommand();
		
		
			System.out.println(buttonString + "it works!");
			
			runPing pingThread = new runPing();
			runIperfEastTCP iperfEastTCPThread = new runIperfEastTCP();
			runIperfEastUDP iperfEastUDPThread = new runIperfEastUDP();
			runIperfWestTCP iperfWestTCPThread = new runIperfWestTCP();
			runIperfWestUDP iperfWestUDPThread = new runIperfWestUDP();
			//writeToFile writeToFileThread = new writeToFile();
			getLocation locationThread = new getLocation();
			
			locationThread.start();
			pingThread.start();
			iperfEastTCPThread.start();
			iperfEastUDPThread.start();
			iperfWestTCPThread.start();
			iperfWestUDPThread.start();
			//writeToFileThread.start();
	}
	
	private class runPing extends Thread {
		public void run() {
			try {
				String line;
	            Process p = Runtime.getRuntime().exec("/sbin/ping -c 4 www.google.com");
	            BufferedReader input = new BufferedReader(
	                new InputStreamReader(p.getInputStream()));
	            
	            while ((line = input.readLine()) != null) {
	            	pingResults += line + "\n";
	            	pingLabel.setText(pingResults);
	            	System.out.println(line);
	            	writeToFile(pingResults);
	            }
	            
	            input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private class runIperfEastTCP extends Thread {
		public void run() {
			try {
				String line = "";
				Process p;
				
				p = Runtime.getRuntime().exec("/usr/local/bin/iperf -c 184.72.222.65 -p 5001 -w 64k -P 4 -i 1 -t 10 -f k");
				BufferedReader input = new BufferedReader(
						new InputStreamReader(p.getInputStream()));
				
				while ((line = input.readLine()) != null) {
					iPerfResults += line + "\n";
					iperfLabel.setText(iPerfResults);
					System.out.println(line);
					writeToFile(iPerfResults);
				}
				
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private class runIperfEastUDP extends Thread {
		public void run() {
			try {
				String line = "";
				Process p;
				
				p = Runtime.getRuntime().exec("/usr/local/bin/iperf -c 184.72.222.65 -u -p 5002 -i 1 -t 5 -l 220 -b 88k -f k");
				BufferedReader input = new BufferedReader(
						new InputStreamReader(p.getInputStream()));
				
				while ((line = input.readLine()) != null) {
					iPerfResults += line + "\n";
					iperfLabel.setText(iPerfResults);
					System.out.println(line);
					writeToFile(iPerfResults);
				}
				
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private class runIperfWestTCP extends Thread {
		public void run() {
			try {
				String line = "";
				Process p;
				
				p = Runtime.getRuntime().exec("/usr/local/bin/iperf -c 184.72.63.139 -p 5001 -w 64k -P 4 -i 1 -t 10 -f k");
				BufferedReader input = new BufferedReader(
						new InputStreamReader(p.getInputStream()));
				
				while ((line = input.readLine()) != null) {
					iPerfResults += line + "\n";
					iperfLabel.setText(iPerfResults);
					System.out.println(line);
					writeToFile(iPerfResults);
				}
				
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private class runIperfWestUDP extends Thread {
		public void run() {
			try {
				String line = "";
				Process p;
				
				p = Runtime.getRuntime().exec("/usr/local/bin/iperf -c 184.72.63.139 -u -p 5002 -i 1 -t 5 -l 220 -b 88k -f k");
				BufferedReader input = new BufferedReader(
						new InputStreamReader(p.getInputStream()));
				
				while ((line = input.readLine()) != null) {
					iPerfResults += line + "\n";
					iperfLabel.setText(iPerfResults);
					System.out.println(line);
					writeToFile(iPerfResults);
				}
				
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private class getLocation extends Thread {
		public void run() {
			try {
				InetAddress thisIP = InetAddress.getLocalHost();
				
	            GeoLocation _gl = new GeoLocation();
	
	            _gl.GetGeoLocation(thisIP);
	            String IP = _gl.IP;
	            String Country = _gl.Country;
	            String City = _gl.City;
	            String Latitude = _gl.Latitude;
	            String Longitude = _gl.Longitude;
	        	
	            cityResultLabel.setText(City);
	        	countryResultLabel.setText(Country);
	        	latitudeResultLabel.setText(Latitude);
	        	longitudeResultLabel.setText(Longitude);
	        	
	        	iPerfResults += "IP: " + IP + "\nCity: " + City + "\nCountry: " + Country + "\nLatitude: " + Latitude + "\nLongitude: " + Longitude;
	        	writeToFile(iPerfResults);
	            
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void writeToFile(String result){
		
			try {
	   			 
				File file = new File("results.txt");
			     
    			// if file doesnt exists, then create it
    			if (!file.exists()) {
    				file.createNewFile();
    			}
     
    			FileWriter fw = new FileWriter(file.getAbsoluteFile());
    			BufferedWriter bw = new BufferedWriter(fw);
    			bw.write(result);
    			bw.close();
     
    			System.out.println("Done"); 
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
}
