import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element{
    private Position position;

    public Wall (Position p){
        position = p;
    }

    public Position getPosition(){
        return position;
    }

    public void setPosition(Position p){
        position = p;
    }

    public void draw(TextGraphics screen){
        screen.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('X')[0]);
    }


}
