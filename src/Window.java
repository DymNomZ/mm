import javax.swing.*;

public class Window extends JFrame {

    public Window(String title){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle(title);
    }

    public void activate(){
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
