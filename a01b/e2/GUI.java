package a01b.e2;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.awt.*;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8718678006474313654L;
	private final Map<JButton, Pair<Integer, Integer>> grid;
	private final Logic logic;

	public GUI(int size, int mines) {
		this.logic = new LogicImpl(size, mines);
		this.grid = new HashMap<>();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(size * 100, size * 100);
		JPanel panel = new JPanel(new GridLayout(size, size));
		this.getContentPane().add(BorderLayout.CENTER, panel);
		ActionListener al = (e) -> {
			final JButton bt = (JButton) e.getSource();
			var out = logic.press(grid.get(bt));
			switch (out) {
			case MINE:
				bt.setText("X");
				JOptionPane.showMessageDialog(panel, "You lose");
				bt.setEnabled(false);
				//System.exit(-1);
				break;
			case SAFE:
				bt.setText(logic.nearbyMines(grid.get(bt)) + "");
				bt.setEnabled(false);
				if (logic.hasWon()) {
					JOptionPane.showMessageDialog(panel, "You won");
					//System.exit(-1);
				}
				break;
			case INVALID:
				JOptionPane.showMessageDialog(panel, "Invalid move, retry");
				break;
			default:
				break;

			}
		};
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				final JButton jb = new JButton("");
				this.grid.put(jb, new Pair<>(j, i));
				jb.addActionListener(al);
				panel.add(jb);
			}
			this.setVisible(true);
		}
	}
}
