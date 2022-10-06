import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall {
    private Position position;

    public Wall (int x, int y){
        position = new Position(x, y);
    }

    public Position getPosition(){
        return position;
    }

    public void draw(TextGraphics screen){
        screen.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('X')[0]);
    }


}
