//package ntrisgame;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
public class Game extends JFrame {
    static int poly = 1;
    private static Board myBoard;
    private static GameThread myGameThread;
    private boolean isPaused;
    
    public Game(int n) {
        isPaused = false;
        poly = n;
        initComponents();
        GameSpace.setSize(240 / (poly * 2  + 2) * (poly * 2 + 2), 
            480 / (poly * 4  + 4) * (poly * 4 + 4));
        myBoard = 
            new Board(GameSpace, n, ScoreDisplay, LevelDisplay, HighScoreLabel);
        this.add(myBoard);
        initControls();
        startGame();
        
    }
    
    public void pause() throws InterruptedException
    {
        if (!isPaused)
        {
            isPaused = true;
            myGameThread.pause();
        }
        else 
        {
            isPaused = false;
            myGameThread.unpause();
        }
    }
    
    private void initControls(){
        InputMap im = this.getRootPane().getInputMap();
        ActionMap am = this.getRootPane().getActionMap();
        im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        im.put(KeyStroke.getKeyStroke("LEFT"), "left");
        im.put(KeyStroke.getKeyStroke("UP"), "up");
        im.put(KeyStroke.getKeyStroke("DOWN"), "down");
        im.put(KeyStroke.getKeyStroke("ESCAPE"), "esc");
        am.put("esc", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pause();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE,
                        null, ex);
                }
}
            
        });

        am.put("right", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPaused){
                    myBoard.moveBlockRight();}
            }
            
        });
        am.put("left", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPaused){
                    myBoard.moveBlockLeft();}
            }
        });
        am.put("up", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPaused){
                    myBoard.rotateBlock();}
            }
        });
        am.put("down", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPaused){
                    myBoard.moveBlockDown();}
            }
        });
        
    }
    
    public void startGame()
    {
        myBoard.spawnBlock();
        myGameThread = new GameThread(myBoard);
        myGameThread.start();
    }
    
    
    public static int startGameNum(){
        Scanner in = new Scanner(System.in);
        System.out.println("Please Enter in a number from 1 - 10");
        int n = 0;
        while (n < 1){
            String myString = in.nextLine();
            try{
                n = Integer.parseInt(myString);
            }
            catch(Exception e){}
            if (n > 10 || n < 1){
                System.out.println("Please enter a valid number!");
            }
        }
        in.close();
        return n;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GameSpace = new javax.swing.JPanel();
        ScoreDisplay = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        LevelDisplay = new javax.swing.JLabel();
        HighScoreLabel = new javax.swing.JLabel();

        GameSpace.setBackground(new java.awt.Color(255, 255, 255));
        GameSpace.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        GameSpace.setPreferredSize(new java.awt.Dimension(240, 480));
        GameSpace.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout GameSpaceLayout = new javax.swing.GroupLayout(GameSpace);
        GameSpace.setLayout(GameSpaceLayout);
        GameSpaceLayout.setHorizontalGroup(
            GameSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 238, Short.MAX_VALUE)
        );
        GameSpaceLayout.setVerticalGroup(
            GameSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NTris");
        setMinimumSize(new java.awt.Dimension(360, 360));
        setPreferredSize(new java.awt.Dimension(400, 520));

        ScoreDisplay.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        ScoreDisplay.setText("Score: 0");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(240, 480));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        LevelDisplay.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        LevelDisplay.setText("Level: 1");

        HighScoreLabel.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        HighScoreLabel.setText("High Score: 0");
        HighScoreLabel.setName("HighScoreLabel"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(ScoreDisplay)
                        .addComponent(LevelDisplay))
                    .addComponent(HighScoreLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 152, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(ScoreDisplay)
                .addGap(18, 18, 18)
                .addComponent(LevelDisplay)
                .addGap(18, 18, 18)
                .addComponent(HighScoreLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ScoreDisplay.getAccessibleContext().setAccessibleName("ScoreDisplay");
        LevelDisplay.getAccessibleContext().setAccessibleName("LevelDisplay");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        poly = startGameNum();
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //poly = startGameNum();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Game(poly).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel GameSpace;
    private javax.swing.JLabel HighScoreLabel;
    private javax.swing.JLabel LevelDisplay;
    private javax.swing.JLabel ScoreDisplay;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
