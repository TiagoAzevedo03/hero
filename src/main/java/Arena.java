import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width;
    private int height;

    static private Hero hero;

    private TextGraphics graphics;

    private List<Wall> walls;

    public Arena(int w, int h){
        hero = new Hero (new Position(10, 10));
        width = w;
        height = h;
        walls = createWalls();
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
        hero.draw(g);
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }
}
