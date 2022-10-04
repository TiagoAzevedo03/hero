import com.googlecode.lanterna.screen.Screen;

public class Arena {
    private int width;
    private int height;

    static private Hero hero;

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
        return p.getX() <= width && p.getY() <= height;
    }

    public void draw(Screen screen){
        hero.draw(screen);
    }
}
