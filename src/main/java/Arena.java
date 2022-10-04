public class Arena {
    private int width;
    private int height;

    public Arena(int w, int h){
        width = w;
        height = h;
    }

    public void moveHero(Position p){
        if (canHeroMove(p)){
            //hero.setPosition(p);
        }
    }

    public boolean canHeroMove(Position p){
        return true;
    }
}
