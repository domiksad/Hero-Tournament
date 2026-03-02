package org.example;

import org.example.display.TerminalDisplay;

import java.awt.event.KeyEvent;

public class Game {
    static boolean ignoreInput = true;
    static boolean usingInput = false;
    static String temp = "";

    public static void handleInput(KeyEvent e){
        if(ignoreInput) return;

        char c = e.getKeyChar();
        int code = e.getKeyCode();

        if (usingInput) {
            if(c == KeyEvent.VK_ESCAPE){
                System.exit(0);
            } else if(c == KeyEvent.VK_ENTER){
                usingInput = false;
            } else if(c == KeyEvent.VK_BACK_SPACE && !temp.isEmpty()){
                temp = temp.substring(0, temp.length() - 1);
            } else if(c != KeyEvent.CHAR_UNDEFINED){
                temp = temp + c;
            }
            TerminalDisplay.print(temp);
        } else {
            switch (code){
                // ESC - leave
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                    break;

                case KeyEvent.VK_C:
                    TerminalDisplay.miniInfo("Adam", 10, 15, 2, 200, 2000);
                    TerminalDisplay.flush();
                    System.out.println("C clicked");
                    break;

                case KeyEvent.VK_W:
                    usingInput = true;
                    temp = "";
                    break;

                case KeyEvent.VK_D:
                    TerminalDisplay.flush();
                    TerminalDisplay.print('O', 50, 5);
                    TerminalDisplay.print('O', 149, 34);
                    TerminalDisplay.flush();
                    break;

                case KeyEvent.VK_F:
                    TerminalDisplay.flush();
                    for(int i = 5; i<35; i++){
                    TerminalDisplay.print("O".repeat(100), 50, i);
                    }
                    TerminalDisplay.flush();
            }
        }
    }

    public static void start(){
        TerminalDisplay.textInMiddle("Hello adventurer. Is your name: " + temp);
        TerminalDisplay.flush();
        usingInput = true;
    }
}
