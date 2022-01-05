//package ntrisgame;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.*;

public class GameThread extends Thread
{
    private Board myBoard;
    private double time;
    private boolean isPaused;
    
    public GameThread(Board myBoard)
    {
        this.myBoard = myBoard;
        time = 1000;
        isPaused = false;
    }
    
    public void pause() throws InterruptedException
    {
        isPaused = true;
    }
    
    public void unpause()
    {
        isPaused = false;
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            try 
            {
                System.out.print("");
                if (!isPaused){
                    myBoard.moveBlockDown();
                    Thread.sleep((int)(time * 
                        Math.pow(.9, (myBoard.getLevel() - 1.0))));}
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE,
                    null,ex);
            }
        }
    }
}
