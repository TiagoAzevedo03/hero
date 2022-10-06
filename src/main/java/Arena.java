import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;

    static private Hero hero;

    private TextGraphics graphics;

    private List<Wall> walls;

    private ArrayList<Coin> coins;

    public Arena(int w, int h){
        hero = new Hero (new Position(10, 10));
        width = w;
        height = h;
        walls = createWalls();
        coins = (ArrayList<Coin>) createCoins();
    }

    public static Hero getHero(){
        return hero;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void setWidth(int w){
        width = w;
    }

    public void setHeight(int h) {
        height = h;
    }

    public void moveHero(Position p){
        if (canHeroMove(p)){
            hero.setPosition(p);
        }
        retrieveCoins();
    }

    public boolean canHeroMove(Position p){
        for (Wall w: walls){
            if (w.getPosition().getX() == p.getX() && w.getPosition().getY() == p.getY()) return false;
        }
        return true;
        //return p.getX() <= width && p.getY() <= height;
    }

    public void draw(TextGraphics g){
        graphics = g;
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(hero.getPosition().getX(), hero.getPosition().getY()), "X");
        for (Wall wall : walls)
            wall.draw(graphics);
        for (Coin coin: coins){
            coin.draw(graphics);
        }
        hero.draw(g);
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(new Position(c, 0)));
            walls.add(new Wall(new Position(c, height - 1)));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(new Position(0, r)));
            walls.add(new Wall(new Position(width - 1, r)));
        }
        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1)));
        return coins;
    }

    public void retrieveCoins(){
        for (Coin coin: coins){
            if (hero.getPosition().getY() == coin.getPosition().getY() && hero.getPosition().getX() == coin.getPosition().getX()){
                coins.remove(coin);
                break;
            }
        }
    }
}
