import java.awt.Color;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ColorClock
{
	static long h;
	static long m;
	static long s;
	static String Time;
	static JTextArea TimeTA = new JTextArea(2, 5);
	static JPanel CPanel = new JPanel();

	public static void main(String[] args)
	{
		JFrame TheMainFrame = new JFrame();  // create the JFrame window
		TheMainFrame.setTitle("Color Clock");
		TheMainFrame.setSize(500, 500);
		TheMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel genPanel = (JPanel)TheMainFrame.getContentPane();
		TimeTA.setEditable(false);
		CPanel.add(TimeTA);
		genPanel.add(CPanel);
		TheMainFrame.setVisible(true);
		
		DecimalFormat decimalFormat = new DecimalFormat("00");
		long Unow = (System.currentTimeMillis() % (1000*60*60*24)) - (5*1000*60*60);
		long now;
		
		boolean DST = true;
		int dst = 5;
		if(DST)
		{
			dst = 4;
		}
		
		while (true)
		{
			if ((((System.currentTimeMillis() % (1000*60*60*24)) - Unow) > 1000) || ((System.currentTimeMillis() % (1000*60*60*24)) == 0))
			{
				Unow = (System.currentTimeMillis() % (1000*60*60*24));
				now = Unow - (dst*1000*60*60);
				if (now < 0) now +=(1000*60*60*24);
				
				h = now / (1000*60*60);
				m = (now / (1000*60)) % 60;
				s = (now/1000) % 60;
				
				Time = String.format("%s:%s:%s", decimalFormat.format(h), decimalFormat.format(m), decimalFormat.format(s));
				change();
			}
		}
		
	}
	
	public static void change()
	{
		int r = (int) (255*h/23);
		int g = (int) (255*m/59);
		int b = (int) (255*s/59);
		String hex = Integer.toHexString((r*65536) + (g*256) + (b));
		while (hex.length() < 6)
			hex = "0" + hex;
		TimeTA.setText(Time + "\n#" + hex);
		CPanel.setBackground(new Color(r, g, b));
	}


}