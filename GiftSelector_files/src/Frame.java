
import javax.swing.JFrame;

public class Frame 
{
	public static void main(String[] args)
	{
		Panel panel = new Panel();
		JFrame frame = new JFrame();
		
		frame.setSize(800, 500);
		frame.setTitle("Fernanda's B-day Program");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setVisible(true);
	}
}