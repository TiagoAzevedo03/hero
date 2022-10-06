import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Arena {
    private int width;
    private int height;

    static private Hero hero;

    private TextGraphics graphics;

    public Arena(int w, int h){
        hero = new Hero (new Position(10, 10));
        width = w;
        height = h;
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
        return p.getX() <= width && p.getY() <= height && p.getX() >= 0 && p.getY() >= 0;
    }

    public void draw(TextGraphics g){
        graphics = g;
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(hero.getPosition().getX(), hero.getPosition().getY()), "X");

        hero.draw(g);
    }
}
