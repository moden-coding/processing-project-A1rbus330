import processing.core.*;

public class App extends PApplet {
    public static void main(String[] args) {
        PApplet.main("App");
    }

    int rectX = 100;
    int rectY = 100;
    double speed = 5;
    int score = 0;
    float foodX = random(0, 400);
    float foodY = random(0, 400);
    boolean left, right;
    boolean up, down;
    int scene = 1;
    float foodSpeedX = 3;
    float foodSpeedY = 3;

    public void setup() {
        background(0, 0, 80);
    }

    public void settings() {
        size(600, 600);

    }

    public void draw() {
        background(0, 0, 80);
        rect(rectX, rectY, 50, 50);
        rect(foodX, foodY, 50, 50);
        if (left == true) {
            rectX -= speed;
        }
        if (right == true) {
            rectX += speed;
        }
        if (down == true) {
            rectY += speed;
        }
        if (up == true) {
            rectY -= speed;
        }
        score();
        gameOver();
        if (gameOver()) { // corrected with chatgpt
            System.out.println("Game over");
            scene = 3;
        }
        if (scene == 1) {
            background(0, 0, 80);
            text("Welcome to Be a Job Application!", 100, 100);
        }
        if (scene == 3) {
            background(0, 0, 80);
            text("Game over", 500, 100);
        }
        if (scene == 2 || scene == 3) {
            text("Score: " + score, 100, 100);
        }
        runAway();
    }

    public void keyPressed() {
        if (key == ' ') {
            scene = 2;
        }
        if (key == 'w') {
            left = false;
            right = false;
            down = false;
            up = true;
        }
        if (key == 's') {
            left = false;
            up = false;
            right = false;
            down = true;
        }
        if (key == 'd') {
            left = false;
            up = false;
            down = false;
            right = true;
        }
        if (key == 'a') {
            up = false;
            down = false;
            right = false;
            left = true;
        }
    }

    public void score() {
        if (dist(rectX, rectY, foodX, foodY) < (50 / 2 + 50 / 2)) {
            score = score + 1;
            System.out.println(score);
            background(0, 0, 80);
            foodX = random(550);
            foodY = random(550);
            speed += .1;
        }
    }

    public boolean gameOver() { // error corrected with chatgpt
        if (rectX + 50 > 600 || rectX < 0 || rectY + 50 > 600 || rectY < 0) { // corrected with chatgpt
            scene = 3;
            return true;
        } else {
            return false;
        }
    }

    public void runAway() {
        if (frameCount % 50 == 0) {
            foodSpeedX = random(-3, 3);
            foodSpeedY = random(-3, 3);
        }
        if (foodX + 50 > 600 || foodX < 0 || foodY + 50 > 600 || foodY < 0) {
            foodSpeedX = -foodSpeedX;
            foodSpeedY = -foodSpeedY;
        }
        foodX += foodSpeedX;
        foodY += foodSpeedY;
    }
}