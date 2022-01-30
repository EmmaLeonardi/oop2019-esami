package a02b.e2;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.awt.*;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5258348340008142139L;
	private final Map<JButton, Pair<Integer, Integer>> grid;
	private final Logic logic;

	public GUI(int size) {
		this.logic = new LogicImpl(size);
		this.grid = new HashMap<>();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(size * 100, size * 100);
		JPanel panel = new JPanel(new GridLayout(size, size));
		JButton next = new JButton(">");
		this.getContentPane().add(BorderLayout.CENTER, panel);
		this.getContentPane().add(BorderLayout.SOUTH, next);
		next.addActionListener(a->{
			logic.next();
			refreshGrid();
		});
		ActionListener al = (e) -> {
			final JButton bt = (JButton) e.getSource();
			bt.setText("X");
			logic.addX(grid.get(bt));
			//refreshGrid();
		};
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				final JButton jb = new JButton("");
				jb.addActionListener(al);
				grid.put(jb, new Pair<>(j, i));
				panel.add(jb);
			}
		}
		refreshGrid();
		this.setVisible(true);
	}

	private void refreshGrid() {
		for (var elem : this.grid.entrySet()) {
			if (logic.Xcoords().contains(elem.getValue())) {
				elem.getKey().setText("X");
			} else {
				elem.getKey().setText("");
			}

		}
	}

}
