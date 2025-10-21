import processing.core.*;

public class App extends PApplet{
    public static void main(String[] args)  {
        PApplet.main("App");
    }
    int rectX = 0;
    int rectY = 0;
    int speed = 5;
    int score = 0;
    float foodX = random(0, 400);
    float foodY = random(0, 400);
    boolean left, right;
    boolean up, down;
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
        if (left == true){
            rectX -= speed;
        }
        if (right == true){
            rectX += speed;
        }
        if (down == true){
            rectY += speed;
        }
        if (up == true){
            rectY -= speed;
        }
        score();
    }
    public void keyPressed(){
        if (key == 'w'){
            left = false;
            right = false;
            down = false;
            up = true;
        }
        if (key == 's'){
            left = false;
            up = false;
            right = false;
            down = true;
        }
        if (key == 'd'){
            left = false;
            up = false;
            down = false;
            right = true;
        }
        if (key == 'a'){
            up = false;
            down = false;
            right = false;
            left = true;
        }
    }
    public void score(){
        if (rectX - foodX >= -50 && rectX - foodX <=0 && rectY - foodY >= -50 && rectY - foodY <= 0){
            score = score + 1;
            System.out.println(score);
            background(0, 0, 80);
            
        }
    }
}