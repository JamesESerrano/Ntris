//package ntrisgame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author james
 */
import java.util.*;
import java.lang.*;
public class DynamicArray<T> {
    
    int size;
    int initialCapacity;
    int[][][] data;
    int row;
    int column;
    int n;
    int r;
    
    public DynamicArray(int row, int column, int r)
    {
        size = 0;
        initialCapacity = 1;
        data = new int[0][row][column];
        this.row = row;
        this.column = column;
        this.n = row * column;
        this.r = r;
        generatePermutations();
    }

    public int getSize()
    {
        return size;
    }
    
    public void increaseCapacity()
    {
        int[][][] data2 = new int[data.length + 1][row][column];
        System.arraycopy(data, 0, data2, 0, data.length);
        data = data2;
    }
    
    public boolean firstColumnEmpty(int[][] piece)
    {
        for (int i = 0; i < piece.length; i++)
        {
            if (piece[i][0] == 1)
            {
                boolean[] filledSpaces = 
                        new boolean[piece.length * piece[0].length];
                return (isConnected(piece, i, 0, filledSpaces) == r);
            }
        }
        
        return false;
    }
    
    public static int isConnected(int[][] piece, int y, int x,
            boolean[] filledSpaces)
    {
        int total = 1;
        filledSpaces[y * piece[0].length + x] = true;
        if (y != 0 && piece[y-1][x] == 1 
            && filledSpaces[(y-1) * piece[0].length + x] == false)
        {
            total += isConnected(piece, y-1, x, filledSpaces);
        }
        if (y + 1 != piece.length && piece[y + 1][x] == 1 
            && filledSpaces[(y + 1) * piece[0].length + x] == false)
        {
            total += isConnected(piece, y + 1, x, filledSpaces);
        }
        if (x != 0 && piece[y][x - 1] == 1 
            && filledSpaces[(y) * piece[0].length + x - 1] == false)
        {
            total += isConnected(piece, y, x - 1, filledSpaces);
        }
        if (x + 1 != piece[0].length && piece[y][x + 1] == 1 
            && filledSpaces[(y) * piece[0].length + x + 1] == false)
        {
            total += isConnected(piece, y, x + 1, filledSpaces);
        }
        return total;
    }
    
    public static boolean firstRowEmpty(int[][] piece)
    {
        for (int i = 0; i < piece[0].length; i++)
        {
            if (piece[0][i] == 1)
            {
                return true;
            }
        }
        
        return false;
    }
    
    public static boolean lastColumnEmpty(int[][] piece)
    {
        for (int i = 0; i < piece.length; i++)
        {
            if (piece[i][piece[0].length - 1] == 1)
            {
                return false;
            }
        }
        
        return true;
    }
    
    public static int[][] removeLastColumn(int[][] piece)
    {
        int[][] trimmedPiece = new int[piece.length][piece[0].length - 1];
        for (int i = 0; i < piece.length; i++)
        {
           System.arraycopy(piece[i], 0, trimmedPiece[i], 0,
                trimmedPiece[0].length);
        }
        
        return trimmedPiece;
    }
    
        public static boolean lastRowEmpty(int[][] piece)
    {
        for (int i = 0; i < piece[0].length; i++)
        {
            if (piece[piece.length - 1][i] == 1)
            {
                return false;
            }
        }
        
        return true;
    }
    
    public static int[][] removeLastRow(int[][] piece)
    {
        int[][] trimmedPiece = new int[piece.length - 1][piece[0].length];
        for (int i = 0; i < piece.length - 1; i++)
        {
           System.arraycopy(piece[i], 0, trimmedPiece[i], 0,
                trimmedPiece[0].length);
        }
        
        return trimmedPiece;
    }
    
    public static int[][] trim(int[][] piece)
    {
        while (lastColumnEmpty(piece) == true)
        {
            piece = removeLastColumn(piece);
        }
        while (lastRowEmpty(piece) == true)
        {
            piece = removeLastRow(piece);
        }
        
        return piece;
    }
    
    public boolean isValidPiece(int[][] piece)
    {
        if (firstRowEmpty(piece) == false)
        {
            return false;
        }
        if (firstColumnEmpty(piece) == false)
        {
            return false;
        }
        
        return true;
    }
    
    public static int[][] transform(int[][] piece)
    {
        int[][] newPiece = new int[piece[0].length][piece.length];
        
        for (int i = 0; i < piece.length; i++)
        {
            for (int j = 0; j < piece[0].length; j++)
            {
                newPiece[piece[0].length - 1 - j][i] = piece[i][j];
            }
        }
        
        return newPiece;
    }
    
    public static boolean checkEqual(int[][] newPiece, int[][] data)
    {
        if (newPiece.length != data.length 
            || newPiece[0].length != data[0].length)
        {
            return false;
        }
        for (int i = 0; i < data.length; i++)
        {
            for (int j = 0; j < data[0].length; j++)
            {
                if (newPiece[i][j] != data[i][j])
                {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public boolean checkPrev(int[][] newPiece)
    {
        for (int i = 0; i < data.length; i++)
        {
            if (checkEqual(newPiece, data[i]) == true)
            {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isDuplicate(int[][] piece)
    {
        if (checkPrev(piece) == true)
        {
            return true;
        }
        int[][] newPiece = transform(piece);
        if (checkPrev(newPiece) == true)
        {
            return true;
        }
        newPiece = transform(newPiece);
        if (checkPrev(newPiece) == true)
        {
            return true;
        }
        newPiece = transform(newPiece);
        if (checkPrev(newPiece) == true)
        {
            return true;
        }
        return false;
    }
    
    public void add(int[][] piece)
    {
        if (isValidPiece(piece) == false)
        {
            return;
        }
        piece = trim(piece);
        if (piece.length > piece[0].length)
        {
            return;
        }
        if (isDuplicate(piece) == true)
        {
            return;
        }
        increaseCapacity();
        data[size] = piece;
        size++;
    }
    

    
    public void generatePermutations()
    {
        List<int[]> permutations = generate(n, r);
        
        for (int[] permutation: permutations)
        {
            int[][] piece = new int[row][column];
            for (int i = 0; i < permutation.length; i++)
            {
                piece[permutation[i]/column][permutation[i]%column] = 1;
            }
            add(piece);
        }
    }
    
    public void printArray()
    {
        for (int i = 0; i < data.length; i++)
        {
            for (int j = 0; j < data[i].length; j++)
            {
                for (int k = 0; k < data[i][j].length; k++)
                {
                    System.out.print(data[i][j][k]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
    
    public static List<int[]> generate(int n, int r) 
    {
        List<int[]> combinations = new ArrayList<>();
        helper(combinations, new int[r], 0, n-1, 0);
        
        return combinations;
    }
    
    private static void helper(List<int[]> combinations, int data[], int start,
        int end, int index) 
    {
        if (index == data.length) 
        {
            int[] combination = data.clone();
            combinations.add(combination);
        }
        else if (start <= end) 
        {
            data[index] = start;
            helper(combinations, data, start + 1, end, index + 1);
            helper(combinations, data, start + 1, end, index);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
    }
}
