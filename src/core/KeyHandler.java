package core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean wPressed, aPressed, sPressed, dPressed;
    public boolean fPressed, qPressed, ePressed;

    private boolean keyBools[] = {
            wPressed, aPressed, sPressed, dPressed,
            fPressed, qPressed, ePressed
    };

    public KeyHandler(){

        //init
        for(boolean b : keyBools) b = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        switch(code){
            case KeyEvent.VK_W -> wPressed = true;
            case KeyEvent.VK_A -> aPressed = true;
            case KeyEvent.VK_S -> sPressed = true;
            case KeyEvent.VK_D -> dPressed = true;
            case KeyEvent.VK_F -> fPressed = true;
            case KeyEvent.VK_Q -> qPressed = true;
            case KeyEvent.VK_E -> ePressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        switch(code){
            case KeyEvent.VK_W -> wPressed = false;
            case KeyEvent.VK_A -> aPressed = false;
            case KeyEvent.VK_S -> sPressed = false;
            case KeyEvent.VK_D -> dPressed = false;
            case KeyEvent.VK_F -> fPressed = false;
            case KeyEvent.VK_Q -> qPressed = false;
            case KeyEvent.VK_E -> ePressed = false;
        }
    }
}
