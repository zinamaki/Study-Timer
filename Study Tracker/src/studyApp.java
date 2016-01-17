import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;

public class studyApp {

	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub
		try {
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		studyAppFrame frame = new studyAppFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Study Hard, or Take Art");
		frame.pack();

		// put the frame in the middle of the display
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

		frame.setVisible(true);

	}

}

class studyAppFrame extends JFrame implements ActionListener {

	JButton start;
	JButton stop;
	JButton resume;
	JButton motivation;
	public static JLabel label;
	public static Timer timer;
	public static Font inputFont = new Font("Myriad Pro", Font.PLAIN, 32);
	JTextField inputMins = new JTextField(15);
	JTextField inputLabel = new JTextField(15);
	public static JLabel[] history = new JLabel[10];

	public static JPanel historyPanel;
	public static JPanel panel4;
	public static JLabel history1;
	public static JLabel history2;
	public static JLabel history3;
	public static JPanel panel6;
	public static JPanel panel7;

	public static int currentEvent;

	Color backgroundColor = new Color(37, 37, 37);
	Color mainColor = new Color(237, 237, 237);
	Color textColor = new Color(193, 192, 191);
	public static BufferedWriter output;

	String[] motivational = { "https://www.youtube.com/watch?v=26U_seo0a1g",
			"https://www.youtube.com/watch?v=7iaLQZ73ujQ", "https://www.youtube.com/watch?v=UX2tefQHNmk",
			"https://www.youtube.com/watch?v=UNQhuFL6CWg", "https://www.youtube.com/watch?v=W_VQbtO3ChM",
			"https://www.youtube.com/watch?v=AjZ0KbJcav0", "https://www.youtube.com/watch?v=wzhzkKccBi8" };

	public studyAppFrame() throws IOException {

		this.setBackground(backgroundColor);

		currentEvent = 0;

		start = new JButton("STUDY");
		stop = new JButton("Stop");
		resume = new JButton("Resume");
		motivation = new JButton("Motivation");

		resume.setEnabled(false);
		stop.setEnabled(false);

		resume.setForeground(textColor);
		start.setForeground(textColor);
		stop.setForeground(textColor);
		motivation.setForeground(textColor);

		resume.setBackground(backgroundColor);
		stop.setBackground(backgroundColor);
		start.setBackground(backgroundColor);
		motivation.setBackground(backgroundColor);

		JLabel label1 = new JLabel("Enter a duration(mins)");
		JLabel label2 = new JLabel("Never, never, never give up! ");
		JLabel label3 = new JLabel("Session label");

		label1.setForeground(textColor);
		label2.setForeground(mainColor);
		label3.setForeground(textColor);

		label2.setFont(inputFont);

		label = new JLabel("00:00");
		label.setFont(inputFont);
		label.setForeground(mainColor);

		history1 = new JLabel("placeholder");
		history2 = new JLabel("placeholder");
		history3 = new JLabel("placeholder");

		history[0] = history1;
		history[1] = history2;
		history[2] = history3;

		inputMins = new JTextField(5);
		inputLabel = new JTextField(10);

		start.addActionListener(this);
		stop.addActionListener(this);
		resume.addActionListener(this);
		inputMins.addActionListener(this);
		inputLabel.addActionListener(this);
		motivation.addActionListener(this);

		JPanel[] list = new JPanel[20];

		File history = new File("history.txt");
		output = new BufferedWriter(new FileWriter(history));

		JPanel panel2 = new JPanel();
		panel2.add(label2);

		JPanel panel1 = new JPanel();

		panel1.add(resume);
		panel1.add(stop);

		JPanel labelPanel = new JPanel();
		labelPanel.add(label3);
		labelPanel.add(inputLabel);

		JPanel panel3 = new JPanel();
		panel3.add(label1);
		panel3.add(inputMins);

		JPanel panel9 = new JPanel();
		panel9.add(start);
		panel9.add(motivation);

		JPanel panel5 = new JPanel();
		panel5.add(label);

		historyPanel = new JPanel();

		panel4 = new JPanel();
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.PAGE_AXIS));
		// panel4.add(panel2);
		panel4.add(labelPanel);
		panel4.add(panel3);
		panel4.add(panel9);
		panel4.add(panel5);
		panel4.add(panel1);

		panel7 = new JPanel();
		panel7.setLayout(new BoxLayout(panel7, BoxLayout.PAGE_AXIS));
		// panel7.add(historyTitle);
		panel7.add(historyPanel);

		panel6 = new JPanel();
		panel6.add(panel4);
		panel6.add(panel7);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(panel2);
		mainPanel.add(panel6);

		mainPanel.setBackground(backgroundColor);

		list[0] = panel2;
		list[1] = panel3;
		list[2] = panel4;
		list[3] = panel5;
		list[4] = panel6;
		list[5] = panel7;
		list[6] = historyPanel;
		list[7] = mainPanel;
		list[8] = labelPanel;
		list[9] = panel1;
		list[10] = panel9;

		for (int i = 0; i < 11; i++) {
			list[i].setBackground(backgroundColor);
		}

		this.setContentPane(mainPanel);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub

		Object source = ae.getSource();

		if (source == start) {

			int numMins = Integer.parseInt(inputMins.getText());
			String label = inputLabel.getText();

			if (timer == null) {
				timer = new Timer(1000, new TimerListener(numMins, label));
				timer.start();
			} else {
				timer.stop();
				timer = new Timer(1000, new TimerListener(numMins, label));
				timer.start();
			}

			resume.setEnabled(true);
			stop.setEnabled(true);

		} else if (source == stop) {
			timer.stop();
		} else if (source == resume) {
			timer.start();
		} else if (source == motivation) {

			Random random = new Random();
			int x = random.nextInt(7);

			try {
				java.awt.Desktop.getDesktop().browse(new URI(motivational[x]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}

class TimerListener implements ActionListener {
	int remainingSeconds;
	int remainingMinutes;

	int startingMinutes;
	String sessionLabel;

	Date date;
	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	String startingTime;

	public TimerListener(int mins, String label) {
		this.startingMinutes = mins;
		this.remainingMinutes = --mins;
		this.remainingSeconds = 60;
		this.sessionLabel = label;

		date = new Date();
		startingTime = dateFormat.format(date);
	}

	public void setMinutes(int mins) {
		this.remainingMinutes = --mins;
		this.remainingSeconds = 60;
	}

	public void playSound() {
		try {

			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(getClass().getResource("/hallelujah.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent evt) {

		if (remainingSeconds == 0) {
			if (remainingMinutes == 0) {
				studyAppFrame.timer.stop();
				playSound();
				JPanel newSession = new JPanel();
				Date finishDate = new Date();
				JLabel mostRecent = new JLabel();
				newSession.add(mostRecent);

				// studyAppFrame.panel7.add(newSession);
				studyAppFrame.panel7.revalidate();
				studyAppFrame.panel7.repaint();

				try {
					String content = sessionLabel + " for " + String.valueOf(startingMinutes) + " minute(s) from "
							+ startingTime + " to " + dateFormat.format(finishDate);
					// Specify the file name and path here
					File file = new File("Study History.txt");

					/*
					 * This logic is to create the file if the file is not
					 * already present
					 */
					if (!file.exists()) {
						file.createNewFile();
					}

					// Here true is to append the content to file
					FileWriter fw = new FileWriter(file, true);
					// BufferedWriter writer give better performance
					BufferedWriter bw = new BufferedWriter(fw);
					bw.newLine();
					bw.write(content);
					// Closing BufferedWriter Stream
					bw.close();

					System.out.println("Data successfully appended at the end of file");

				} catch (IOException ioe) {
					System.out.println("Exception occurred:");
					ioe.printStackTrace();
				}

				// try(PrintWriter out = new PrintWriter(new BufferedWriter(new
				// FileWriter("history.txt", true)))) {
				// out.println(sessionLabel + " for " +
				// String.valueOf(startingMinutes)+ " minute(s) from " +
				// startingTime + " to " + dateFormat.format(finishDate));

				// }catch (IOException e) {

				// }

			} else {
				remainingMinutes--;
				remainingSeconds = 59;
			}

		} else {
			remainingSeconds--;
		}

		String displaySec = String.valueOf(remainingSeconds);
		String displayMin = String.valueOf(remainingMinutes);

		if (remainingSeconds < 10) {
			displaySec = "0" + displaySec;
		}

		studyAppFrame.label.setText(displayMin + ":" + displaySec);

	}

}
