//package ntrisgame;
public class NtrisPiece 
{
    private int[][] shape;
    private int x, y;
    
    public NtrisPiece(int[][] shape, int columns)
    {
        this.shape = shape;
        y = 0;
        x = columns/2 - shape[0].length/2;
    }
    
    public int getHeight()
    {
        return shape.length;
    }
    
    public int getWidth()
    {
        return shape[0].length;
    }
    
    public int[][] getShape()
    {
        return shape;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void moveDown()
    {
        y++;
    }
    
    public void moveRight()
    {
        x++;
    }
    
    public void moveLeft()
    {
        x--;
    }
    
    public void rotate()
    {
        int[][] newPiece = new int[shape[0].length][shape.length];
        y -= (shape[0].length - shape.length)/2;
        x -= (shape.length - shape[0].length)/2;
        
        for (int i = 0; i < shape.length; i++)
        {
            for (int j = 0; j < shape[0].length; j++)
            {
                newPiece[shape[0].length - 1 - j][i] = shape[i][j];
            }
        }
        
        shape = newPiece;
    }
    
    
}
