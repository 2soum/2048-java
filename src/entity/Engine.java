package entity;

import java.awt.Dimension;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Engine {
	private Dimension dimension ;
	private Cell grid[][];

	public Engine(int[][] tab) {
		if (tab == null || tab.length == 0 || tab[0].length == 0) {
			throw new IllegalArgumentException("Le tableau ne peut pas être null ou vide.");
		}

		int l = tab.length;
		int c = tab[0].length;

		this.dimension = new Dimension(l, c);

		this.grid = new Cell[l][c];
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < c; j++) {
				this.grid[i][j] = new Cell(tab[i][j]);
			}
		}
	}


	public Engine(int l, int c) {
		this.dimension = new Dimension(l,c);
		this.grid = new Cell[l][c];
		
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < c; j++) {
				this.grid[i][j] = new Cell(0);
			}
		}
	}
	public ArrayList<Cell> getEmptyCells() {
		ArrayList<Cell> emptyCells = new ArrayList<>();

		for (int i = 0; i < dimension.getWidth(); i++) {
			for (int j = 0; j < dimension.getHeight(); j++) {
				if (grid[i][j].getContent() == 0) {
					emptyCells.add(grid[i][j]);
				}
			}
		}

		return emptyCells;
	}
	public void addNewCell() {
		ArrayList<Cell> emptyCells = getEmptyCells();

		if (!emptyCells.isEmpty()) {
			// Génération aléatoire de l'indice de la cellule vide
			Random random = new Random();
			int randomIndex = random.nextInt(emptyCells.size());

			// La nouvelle valeur a 80% de chance d'être 2 et 20% de chance d'être 4
			int newValue = (random.nextDouble() < 0.8) ? 2 : 4;

			// Récupération de la cellule vide choisie aléatoirement
			Cell chosenCell = emptyCells.get(randomIndex);

			// Mise à jour de la valeur de la cellule choisie
			chosenCell.setContent(newValue);
		}
	}
	public int getWidth() {
		return (int) this.dimension.getWidth();
	}
	
	public int getHeight() {
		return (int) this.dimension.getHeight();
	}
	
	public int getCell(int i, int j) {
		return this.grid[i][j].getContent();
	}
	
	public void setCell(int i, int j, int v) {
		this.grid[i][j].setContent(v);
	}

}
