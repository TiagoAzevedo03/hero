import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{
    private Position position;

    public Monster(Position p){
        position = p;
    }

    public Position getPosition(){
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void draw(TextGraphics screen){
        screen.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('M')[0]);
    }

    public Position move(){
        Random random = new Random();
        Position p = getPosition();
        int x = random.nextInt(4);
        if(x == 1){
            p.setX(p.getX());
            p.setY(p.getY()+1);
        }
        if(x == 2) {
            p.setX(p.getX());
            p.setY(p.getY()-1);
        }
        if(x == 3) {
            p.setX(p.getX()+1);
            p.setY(p.getY());
        }
        if(x == 0) {
            p.setX(p.getX()-1);
            p.setY(p.getY());
        }
        return p;
    }
}
