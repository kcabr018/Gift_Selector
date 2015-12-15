import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Panel extends JPanel
{
	JLabel titleLabel, instructionLabel, randomizeLabel, finalLabel, result, errorLabel;
	JButton randomizeButton, add, remove;
	ArrayList<String> stores;
	DefaultListModel model;
	ActionListener b1;
	JTextField field;
	JList area;

	public Panel()
	{
		//instantiate labels
		titleLabel = new JLabel("***** What to get her??? *****", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Monotype Corsiva", Font.PLAIN, 30));
		instructionLabel = new JLabel("Type in your favorite stores");
		instructionLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		randomizeLabel = new JLabel("Click button to select a store at random", SwingConstants.CENTER);
		randomizeLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		finalLabel = new JLabel("" , SwingConstants.CENTER);
		finalLabel.setFont(new Font("Andalus", Font.PLAIN, 14));
		result = new JLabel("", SwingConstants.CENTER);
		result.setFont(new Font("Kunstler Script", Font.PLAIN, 40));
		errorLabel = new JLabel("");
		errorLabel.setFont(new Font("Narkisim", Font.PLAIN, 16));
		errorLabel.setForeground(Color.RED);

		//instantiate remaining components
		b1 = new ButtonListener();
		randomizeButton = new JButton("Select Store");
		randomizeButton.setFont(new Font("Monotype Corsiva", Font.PLAIN, 15));
		randomizeButton.addActionListener(b1);
		add = new JButton("Add Store");
		add.setFont(new Font("Monotype Corsiva", Font.PLAIN, 15));
		add.addActionListener(b1);
		remove = new JButton("Remove Store");
		remove.setFont(new Font("Monotype Corsiva", Font.PLAIN, 15));
		remove.addActionListener(b1);
		model = new DefaultListModel();
		area = new JList(model);
		area.setBackground(new Color(211,232,236));
		area.setBorder(BorderFactory.createTitledBorder("Stores"));
		area.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		field = new JTextField();
		stores = new ArrayList<String>();

		//construct top left panel
		JPanel topLeft = new JPanel();
		topLeft.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.ipady = 100;
		c.ipadx = 10;
		topLeft.add(titleLabel, c);
		c.ipady = 20;
		c.gridy = 1;
		topLeft.add(errorLabel, c);

		//construct middle left panel
		JPanel middleLeft = new JPanel();
		middleLeft.setLayout(new GridBagLayout());
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 0;
		middleLeft.add(instructionLabel, c);
		c.gridy = 1;
		c.fill = 1;
		c.ipady = 0;
		middleLeft.add(field, c);
		c.fill = 0;
		c.gridy = 2;
		c.ipadx = 19;
		middleLeft.add(add, c);
		c.gridy = 3;
		c.ipadx = 0;
		middleLeft.add(remove, c);

		//construct bottom left panel
		JPanel bottomLeft = new JPanel();
		bottomLeft.setLayout(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 25;
		bottomLeft.add(randomizeButton, c);
		c.gridy = 1;
		c.ipady = 10;
		bottomLeft.add(randomizeLabel, c);

		//construct left panel
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(3, 1));
		leftPanel.add(topLeft);
		leftPanel.add(middleLeft);
		leftPanel.add(bottomLeft);

		//construct right bottom panel
		JPanel rightBottom = new JPanel();
		rightBottom.setLayout(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 100;
		c.ipady = 50;
		rightBottom.add(finalLabel, c);
		c.gridy = 1;
		c.ipadx = 50;
		c.ipady = 0;
		rightBottom.add(result, c);

		//construct right panel
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(2, 1));
		rightPanel.add(area);
		rightPanel.add(rightBottom);

		//add all panels to main panel
		setLayout(new GridLayout(1, 2));
		add(leftPanel);
		add(rightPanel);
	}

	public void updateList()
	{
		model.clear();
		for (int i = 0; i < stores.size(); i++)
		{
			model.addElement(stores.get(i));
		}
	}

	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if (event.getSource() == add)
			{
				String store = field.getText();
				int arraySize = stores.size();

				if (arraySize == 0)
				{
					stores.add(store);
					updateList();
				}
				else
				{
					for (int i = 0; i < arraySize; i++)
					{
						if (store.equals(stores.get(i)))
						{
							errorLabel.setText("You can't add the same store twice, cheater");
							break;
						}
						else if (i == stores.size() - 1)
						{
							stores.add(store);
							errorLabel.setText("");
							updateList();
						}
					}
				}
			}
			else if (event.getSource() == remove)
			{
				if (stores.size() != 0)
				{
					stores.remove(area.getSelectedIndex());
					errorLabel.setText("Store was removed");
					updateList();	
				}
				else;
			}
			else
			{
				Random generator = new Random();
				errorLabel.setText("");

				if (stores.size() == 0);
				else
				{
					finalLabel.setText("Congratulations, you have chosen to recieve a gift from: ");
					int index = generator.nextInt(stores.size());
					result.setText(stores.get(index));
				}
			}
		}
	}
}