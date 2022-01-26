package a01a.e2;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.awt.*;

public class GUIExample extends JFrame {
	private final Logic logic;
	private static final long serialVersionUID = 1L;
	private final Map<JButton, Pair<Integer, Integer>> grid;
	private final int moves=5;

	public GUIExample(int size, int boat) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(size * 100, size * 100);
		this.grid = new HashMap<>();
		this.logic= new LogicImpl(size, boat, moves);
		JPanel panel = new JPanel(new GridLayout(size, size));
		this.getContentPane().add(BorderLayout.CENTER, panel);
		ActionListener al = (e) -> {
			final JButton bt = (JButton) e.getSource();
			var output=logic.tile(grid.get(bt));
			switch(output) {
			case BOAT:
				bt.setText("X");
				bt.setEnabled(false);
				break;
			case DEFEAT:
				bt.setText("0");
				bt.setEnabled(false);
				JOptionPane.showMessageDialog(panel, "You lost!");
				System.exit(1);
				break;
			case INVALID:
				JOptionPane.showMessageDialog(panel, "Invalid hit, retry");
				break;
			case MISS:
				bt.setText("0");
				bt.setEnabled(false);
				break;
			case VICTORY:
				bt.setText("X");
				bt.setEnabled(false);
				JOptionPane.showMessageDialog(panel, "You won!");
				System.exit(1);
				break;
			default:
				break;
			}
		};
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				final JButton jb = new JButton("");
				jb.addActionListener(al);
				this.grid.put(jb, new Pair<>(j, i));
				panel.add(jb);
			}
		}
		this.setVisible(true);
	}

}
