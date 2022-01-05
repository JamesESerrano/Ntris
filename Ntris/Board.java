//package ntrisgame;
import javax.swing.*;
import java.awt.*;
import java.lang.*;
import java.util.*;
public class Board extends JPanel
{
    private DynamicArray pieces;
    private int[][] gameBoard;
    private NtrisPiece current;
    private int score, highScore, gridRows, gridColumns, gridCellSize, poly;
    private JLabel ScoreDisplay, LevelDisplay, HighScoreDisplay;
    
    public Board(JPanel panel, int n, JLabel ScoreDisplay, JLabel LevelDisplay,
        JLabel HighScoreLabel)
    {
        panel.setVisible(false);
        this.setBounds(panel.getBounds());
        this.setBackground(panel.getBackground());
        this.setBorder(panel.getBorder());
        poly = n;
        gridColumns = 2 * n + 2;
        gridRows = 2 * gridColumns;
        gridCellSize = panel.getHeight() / gridRows;
        createPieces();
        gameBoard = new int[gridRows][gridColumns];
        score = 0;
        highScore = 0;
        this.ScoreDisplay = ScoreDisplay;
        this.LevelDisplay = LevelDisplay;
        this.HighScoreDisplay = HighScoreLabel;
    }
    
    public void UpdateScore()
    {
        ScoreDisplay.setText("Score: " + score);
        LevelDisplay.setText("Level: " + (score / 10 + 1));
        HighScoreDisplay.setText("High Score: " + highScore);
    }
    
    public void paintBlockOnGrid()
    {
        for(int x = 0; x < current.getWidth(); x++)
        {
            for (int y = 0; y < current.getHeight(); y++)
            {
                gameBoard[y + current.getY()][x + current.getX()] 
                    += current.getShape()[y][x];
            }
        }
    }
    
    public int getScore()
    {
        return score;
    }
    
    public int getLevel()
    {
        return (score / 10) + 1;
    }
    
    public boolean isValidSpawn()
    {
        for (int x = 0; x < current.getWidth(); x++)
        {
            for (int y = 0; y < current.getHeight(); y++)
            {
                if (current.getShape()[y][x] == 1
                    && gameBoard[current.getY() + y][current.getX() + x] >= 2)
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void clearBoard()
    {
        for (int i = 0; i < gridRows; i++)
        {
            for (int j = 0; j < gridColumns; j++)
            {
                gameBoard[i][j] = 0;
            }
        }
    }
    
    public void resetGame()
    {
        if (score > highScore)
        {
            highScore = score;
        }
        score = 0;
        UpdateScore();
        clearBoard();
        spawnBlock();
    }
    
    public void spawnBlock()
    {
        int[][] shape = pieces.data[(int)(Math.random() * pieces.size)];
        NtrisPiece currentPiece = new NtrisPiece(shape, gridColumns);
        this.current = currentPiece;
        if (isValidSpawn() == true)
        {
            paintBlockOnGrid();
        }
        else
        {
            resetGame();
        }
    }
    
    public void tempErasePiece()
    {
        for(int x = 0; x < gridColumns; x++)
        {
            for (int y = 0; y < gridRows; y++)
            {
                if (gameBoard[y][x] == 1)
                {
                    gameBoard[y][x] = 0;
                }
            }
        }
    }
    
    public int[] findRightestValues()
    {
        int[] rightistValues = new int[current.getHeight()];
        
        for (int i = 0; i < rightistValues.length; i++)
        {
            for (int j = 0; j < current.getWidth(); j++)
            {
                if (current.getShape()[i][j] == 1)
                {
                    rightistValues[i] = j;
                }
            }
        }
        
        return rightistValues;
    }
    
    public int[] findLeftistValues()
    {
        int[] leftistValues = new int[current.getHeight()];
        
        for (int i = 0; i < leftistValues.length; i++)
        {
            for (int j = 0; j < current.getWidth(); j++)
            {
                if (current.getShape()[i][j] == 1)
                {
                    leftistValues[i] = j;
                    j = current.getWidth() + 1;
                }
            }
        }
        
        return leftistValues;
    }
    
    public int[] findLowestValues()
    {
        int[] lowestValues = new int[current.getWidth()];
        
        for (int i = 0; i < lowestValues.length; i++)
        {
            for (int j = 0; j < current.getHeight(); j++)
            {
                if (current.getShape()[j][i] == 1)
                {
                    lowestValues[i] = j;
                }
            }
        }
        
        return lowestValues;
    }
 
    public boolean isValidMoveRight()
    {
        int[] rightestValues = findRightestValues();
        
        for (int i = 0; i < rightestValues.length; i++)
        {
            if (current.getX() + current.getWidth() == gridColumns)
            {
                return false;
            }
            if (gameBoard[current.getY() + i]
                [current.getX() + rightestValues[i] + 1] >= 2)
            {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean isValidMoveLeft()
    {
        int[] leftistValues = findLeftistValues();
        
        for (int i = 0; i < leftistValues.length; i++)
        {
            if (current.getX() == 0)
            {
                return false;
            }
            if (gameBoard[current.getY() + i]
                [current.getX() + leftistValues[i] - 1] >= 2)
            {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean isValidMoveDown()
    {
        int[] lowestValues = findLowestValues();
        
        for (int i = 0; i < lowestValues.length; i++)
        {
            if (current.getY() + lowestValues[i] + 1 == gridRows
                || gameBoard[current.getY() + lowestValues[i] + 1]
                [current.getX() + i] >= 2)
            {
                return false;
            }
        }
        
        return true;
    }
    
    public void cementCurrentPiece()
    {
        for(int x = 0; x < current.getWidth(); x++)
        {
            for (int y = 0; y < current.getHeight(); y++)
            {
                gameBoard[y + current.getY()][x + current.getX()] 
                    += current.getShape()[y][x] * 2;
            }
        }
    }
    
    public void moveBlockRight()
    {
        if (isValidMoveRight() == true)
        {
            tempErasePiece();
            current.moveRight();
            paintBlockOnGrid();
            repaint();
        }
    }
    
    public void moveBlockLeft()
    {
        if (isValidMoveLeft() == true)
        {
            tempErasePiece();
            current.moveLeft();
            paintBlockOnGrid();
            repaint();
        }
    }
    
    public boolean isRowFull(int[] row)
    {
        for (int i = 0; i < gridColumns; i++)
        {
            if (row[i] == 0)
            {
                return false;
            }
        }
        
        return true;
    }
    
    public void clearFirstRow()
    {
        for (int i = 0; i < gridColumns; i++)
        {
            gameBoard[0][i] = 0;
        }
    }
    
    public void moveRowsDown(int rowNumber)
    {
        for (int i = rowNumber; i > 0; i--)
        {
            System.arraycopy(gameBoard[i-1], 0, gameBoard[i], 0, gridColumns);
        }
        clearFirstRow();
    }
    
    public void addToScore(int n)
    {
        for (int i = 1; i <= n; i++)
        {
            score += i;
        }
    }
    
    public void clearFullRows()
    {
        int totalRowsRemoved = 0;
        
        for (int i = gridRows - 1; i >= 0; i--)
        {
            while (isRowFull(gameBoard[i]) == true)
            {
                moveRowsDown(i);
                totalRowsRemoved++;
            }
        }
        addToScore(totalRowsRemoved);
    }
    
    public void moveBlockDown()
    {
        if (isValidMoveDown() == true)
        {
            tempErasePiece();
            current.moveDown();
            paintBlockOnGrid();
        }
        else
        {
            cementCurrentPiece();
            clearFullRows();
            UpdateScore();
            spawnBlock();
        }
        repaint();
    }
    
    public boolean isValidRotate(){
        int newY = current.getY() - (current.getWidth() - current.getHeight())
            / 2;
        int newX = current.getX() - (current.getHeight() - current.getWidth())
            / 2;
        if (newY < 0 || newY + current.getWidth() > gridRows || newX < 0
            || newX + current.getHeight() > gridColumns){
            return false;
        }
        for (int i = 0; i < current.getHeight(); i++){
            for (int j = 0; j < current.getWidth(); j++){
                if (gameBoard[newY + current.getWidth() - j][newX + i] >= 2
                    && current.getShape()[i][j] == 1){
                    return false;
                }
            }
        }
        return true;
    }
        
    public void rotateBlock()
    {
        if (isValidRotate() == true)
        {
            tempErasePiece();
            current.rotate();
            paintBlockOnGrid();
        }
        repaint();
    }
    
    public void createPieces()
    {
        DynamicArray main = new DynamicArray(1, poly, poly);
        
            for (int i = 1; 2 * i < poly; i++)
            {
                DynamicArray sub = new DynamicArray(1 + i, poly - i, poly);
                for (int j = 0; j < sub.size; j++)
                {
                    main.add(sub.data[j]);
                }
            }
        this.pieces = main;
    }

    public void drawGrid(Graphics g){
        for (int y = 0; y < gridRows; y++){
            for (int x = 0; x < gridColumns; x++){
                if (gameBoard[y][x] == 1){
                    g.setColor(Color.red);
                }
                else if (gameBoard[y][x] >= 2){
                    g.setColor(Color.blue);
                }
                else {
                    g.setColor(Color.white);
                }
                g.fillRect(x * gridCellSize, y * gridCellSize, gridCellSize,
                    gridCellSize);
                g.setColor(Color.black);
                g.drawRect(x * gridCellSize, y * gridCellSize, gridCellSize,
                    gridCellSize);
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        //spawnBlock();
        drawGrid(g);
    }


}
