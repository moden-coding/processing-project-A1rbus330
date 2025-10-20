import processing.core.*;

public class App extends PApplet{
    public static void main(String[] args)  {
        PApplet.main("App");
    }
    float rectX = 0;
    float rectY = 0;
    float speed = 5;
    int score = 0;
    float foodX = random(0, 400);
    float foodY = random(0, 400);
    public void setup(){
        background(0, 0, 80);
    }

    public void settings(){
        size(600, 600);

    }

    public void draw(){
        background(0, 0, 80);
        rect(rectX, rectY, 50, 50);
        rect(foodX, foodY, 50, 50);
    }
    public void keyPressed(){
        if (key == 'd'){ // error corerected w/ chatGPT
        rectX = rectX + speed;
        }
        if (key == 'w'){
            rectY = rectY - speed;
        }
        if (key == 'a'){
            rectX = rectX - speed;
        }
        if (key == 's'){
            rectY = rectY + speed;
        }
    }
    public void score(){
        if (rectX == foodX || rectY == foodY){
            score++;
        }
    }
}