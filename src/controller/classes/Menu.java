package controller.classes;

public class Menu {
    
    private final String directory;
    
    public Menu(String directory) {
        this.directory = directory;
    }
    
    public void startGame() {
        StartGame game = new StartGame();
    }

}
