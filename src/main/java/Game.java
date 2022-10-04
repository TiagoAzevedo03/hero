import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Hero hero;
    public Game() throws IOException {
        try {
            hero = new Hero(new Position(10, 10));
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if
        } catch (IOException e) {
            e.printStackTrace();
        }
        TerminalSize terminalSize = new TerminalSize(40, 20);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
    }
    private void draw() throws IOException{
        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }
    public void run() throws IOException {
        while (true) {
            draw();
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
                screen.close();
            }
            if (key.getKeyType() == KeyType.EOF) break;
            processKey(key);
        }
    }

    private void moveHero(Position p){
        hero.setPosition(p);
    }

    private void processKey(KeyStroke key){
        System.out.println(key);
        if (key.getKeyType() == KeyType.ArrowUp){
            moveHero(hero.moveUp());
        }
        if (key.getKeyType() == KeyType.ArrowDown){
            moveHero(hero.moveDown());
        }
        if (key.getKeyType() == KeyType.ArrowRight){
            moveHero(hero.moveRight());
        }
        if (key.getKeyType() == KeyType.ArrowLeft){
            moveHero(hero.moveLeft());
        }
    }

}

