import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Arena arena;
    public Game() throws IOException {
        try {
            arena = new Arena(20, 20);
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
        arena.draw(screen.newTextGraphics());
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
        arena.moveHero(p);
    }

    private void processKey(KeyStroke key){
        System.out.println(key);
        if (key.getKeyType() == KeyType.ArrowUp){
            moveHero(Arena.getHero().moveUp());
        }
        if (key.getKeyType() == KeyType.ArrowDown){
            moveHero(Arena.getHero().moveDown());
        }
        if (key.getKeyType() == KeyType.ArrowRight){
            moveHero(Arena.getHero().moveRight());
        }
        if (key.getKeyType() == KeyType.ArrowLeft){
            moveHero(Arena.getHero().moveLeft());
        }
    }

}

