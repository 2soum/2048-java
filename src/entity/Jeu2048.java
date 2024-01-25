package entity;

import control.IControl;

public class Jeu2048 implements IControl {
     private Engine engine;
    public Jeu2048() {
        init();
    }

    @Override
    public void init() {
        int[][] vals = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        engine = new Engine(vals);

        engine.addNewCell();
        engine.addNewCell();

    }

    @Override
    public int[][] getGrid() {
        int width = engine.getWidth();
        int height = engine.getHeight();
        int[][] grid = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = engine.getCell(i, j);
            }
        }

        return grid;
    }


    private void rotate() {
        int width = engine.getWidth();
        int height = engine.getHeight();
        int[][] newGrid = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                newGrid[j][width - 1 - i] = engine.getCell(i, j);
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                engine.setCell(i, j, newGrid[i][j]);
            }
        }
    }
    private void moveRight() {
        int width = engine.getWidth();
        int height = engine.getHeight();

        for (int i = 0; i < height; i++) {
            for (int j = width - 2; j >= 0; j--) {
                if (engine.getCell(i, j) != 0) {
                    int k = j;
                    while (k + 1 < width && engine.getCell(i, k + 1) == 0) {
                        engine.setCell(i, k + 1, engine.getCell(i, k));
                        engine.setCell(i, k, 0);
                        k++;
                    }
                }
            }
        }
    }
    private void fuseRight() {
        int width = engine.getWidth();
        int height = engine.getHeight();

        for (int i = 0; i < height; i++) {
            for (int j = width - 1; j > 0; j--) {
                if (engine.getCell(i, j) != 0 && engine.getCell(i, j) == engine.getCell(i, j - 1)) {
                    engine.setCell(i, j, engine.getCell(i, j) * 2);
                    engine.setCell(i, j - 1, 0);
                    j--;
                }
            }
        }
    }
    public void right() {
        moveRight();

        fuseRight();

        moveRight();

        engine.addNewCell();
    }

    @Override
    public void down() {
        for (int i = 0; i < 3; i++) rotate();
        moveRight();
        fuseRight();
        moveRight();
        rotate();
        engine.addNewCell();


    }

    @Override
    public void up() {
        rotate();
        moveRight();
        fuseRight();
        moveRight();
        for (int i = 0; i < 3; i++) rotate();
        engine.addNewCell();
    }

    @Override
    public void left() {
        for (int i = 0; i < 2; i++) rotate();
        moveRight();
        fuseRight();
        moveRight();
        for (int i = 0; i < 2; i++) rotate();
        engine.addNewCell();
    }

    @Override
    public boolean isOver() {
        int width = engine.getWidth();
        int height = engine.getHeight();

        // Check for any empty cell
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (engine.getCell(i, j) == 0) {
                    return false;
                }
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width - 1; j++) {
                if (engine.getCell(i, j) == engine.getCell(i, j + 1)) {
                    return false;
                }
            }
        }

        // Check vertically
        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height - 1; i++) {
                if (engine.getCell(i, j) == engine.getCell(i + 1, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int score() {
        int maxScore = 0;
        int width = engine.getWidth();
        int height = engine.getHeight();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int cellValue = engine.getCell(i, j);
                if (cellValue > maxScore) {
                    maxScore = cellValue;
                }
            }
        }

        return maxScore;
    }
}
