package org.example.display;

import org.example.entities.AbstractCharacter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import static org.example.Game.handleInput;

public class TerminalDisplay {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 40;

    private static final JTextArea textArea;

    private static final char[][] buffer = new char[HEIGHT][WIDTH];
    static {
        // Create all
        JFrame frame = new JFrame("Hero Tournament");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);

        textArea = new JTextArea(HEIGHT, WIDTH);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setEditable(false);
        textArea.setBackground(Color.DARK_GRAY);
        textArea.setForeground(Color.white);

        // Disable widening
        textArea.setPreferredSize(textArea.getPreferredSize());
        textArea.setMinimumSize(textArea.getPreferredSize());
        textArea.setMaximumSize(textArea.getPreferredSize());

        // Center
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.black);
        panel.add(textArea);

        frame.getContentPane().add(panel);
        frame.setLocationRelativeTo(null); // Center text area

        // Fullscreen
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(frame);

        // Show
        frame.setVisible(true);

        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleInput(e);
            }
        });

        textArea.requestFocusInWindow();

        initBuffer();
    }

    private static void stringToBuffer(int width, int height, String string) { stringToBuffer(width, height, string, string.length()); }
    private static void stringToBuffer(int width, int height, String string, int length){
        assert (width >= 0 && width < WIDTH && height >= 0 && height < HEIGHT);

        int len = Math.min(length, string.length());
        for(int i = 0; i < len && width + i < WIDTH; i++){
            buffer[height][width + i] = string.charAt(i);
        }
    }

    private static void initBuffer(){
        for (char[] chars : buffer) {
            Arrays.fill(chars, ' ');
        }
    }

    public static void flush(){
        StringBuilder sb = new StringBuilder();
        for (char[] row : buffer) {
            sb.append(row);
            sb.append("\n");
        }
        textArea.setText(sb.toString());
        initBuffer();
    }

    public static void miniInfo(String name, int health, int maxHealth, int level, int experience, int maxExperience){
        stringToBuffer(0, 0, "Name: "+name, 15);
        stringToBuffer(0, 1, "Health: "+health+"/"+maxHealth, 15);
        stringToBuffer(0, 2, "Level: "+level+" ("+experience+"/"+maxExperience+")", 40);
    }

    public static void textInMiddle(String text){
        int wmin = 50; int w = 50; int wmax = 151;
        int hmin = 5; int h = 5; int hmax = 35;
        for(char c : text.toCharArray()){
            assert (wmin <= w && w < wmax && hmin <= h && h < hmax);
            if(c == '\n'){
                h++; w = wmin;
            } else {
                buffer[h][w] = c;
                w++;
            }
        }
    }

    public static void displayLevelUp(AbstractCharacter player){

        flush();
    }

    public static void print(String text){
        textArea.setText(text);
    }

    public static void print(char c, int width, int height){
        buffer[height][width] = c;
    }

    public static void print(String text, int width, int height){
        stringToBuffer(width, height, text);
    }
}
