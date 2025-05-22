

public class Main {

    static Window MAIN_WINDOW;
    static Game GAME;

    public static void main(String[] args) {

        MAIN_WINDOW = new Window("mm");

        GAME = new Game();

        MAIN_WINDOW.add(GAME);
        MAIN_WINDOW.pack();

        MAIN_WINDOW.activate();

    }
}