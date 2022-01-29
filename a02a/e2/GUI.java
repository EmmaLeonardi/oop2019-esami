package a02a.e2;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.awt.*;

public class GUI extends JFrame {

	private static final long serialVersionUID = -493943305956943234L;
	private final Map<JButton, Pair<Integer, Integer>> grid;
	private final Logic logic;

	public GUI(int size) {
		
		this.grid = new HashMap<>();
		this.logic = new LogicImpl(size);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(size * 100, size * 100);
		JPanel panel = new JPanel(new GridLayout(size, size));
		final JButton next = new JButton(">");
		this.getContentPane().add(BorderLayout.CENTER, panel);
		this.getContentPane().add(BorderLayout.SOUTH, next);
		ActionListener alNext = (e) -> {
			logic.next();
			redrawGrid();
		};

		ActionListener al = (e) -> {
			final JButton bt = (JButton) e.getSource();
			if (bt.getText().equals("X")) {
				System.exit(1);
			}
		};

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				final JButton jb = new JButton("");
				jb.addActionListener(al);
				panel.add(jb);
				this.grid.put(jb, new Pair<>(j, i));
			}
		}
		next.addActionListener(alNext);
		redrawGrid();
		this.setVisible(true);
	}

	private void redrawGrid() {
		for(var cell: grid.keySet()) {
			//Per ogni bottone
			if(logic.getCrossCells().contains(grid.get(cell))) {
				cell.setText("X");
			}
			else {
				cell.setText("");
			}
		}
	}

}
