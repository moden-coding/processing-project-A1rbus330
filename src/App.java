import processing.core.*;
import processing.core.PShapeSVG.Font;
import processing.sound.*;

public class App extends PApplet {
    public static void main(String[] args) {
        PApplet.main("App");
    }

    SoundFile music;
    float rectX = 100; // pos for rectangle
    float rectY = 100;
    double speed = 5; // how fast the rect is going
    int score = 0;
    float foodX = random(0, 400); // food pos
    float foodY = random(0, 400);
    float enemyX = random(0, 400);
    float enemyY = random(0, 400);
    boolean left, right; // moving directions
    boolean up, down;
    int scene = 1; // starting screen
    float foodSpeedX = 3; // food speed
    float foodSpeedY = 3;
    float enemySpeedX = 3;
    float enemySpeedY = 3;
    PImage background;
    int highScore = 0;
    PImage image; // owl
    PImage escapee; // Dr. Moden
    PImage enemy; // Enemy
    double extra = 0;

    public void setup() { // makes the background
        background(0, 0, 80);
        music = new SoundFile(this, "bgm.mp3"); // copied and pasted from nathan's code
        music.play();
    }

    public void settings() { // puts in the images that I need
        size(600, 600);
        image = loadImage("Owl.png");
        escapee = loadImage("drmoden.png");
        background = loadImage("tunnel.png");
        enemy = loadImage("dude.jpeg");
    }

    public void draw() {
        background(background);
        rect(foodX, foodY, 50, 50); // animations
        rect(enemyX, enemyY, 50, 50);
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
        image(image, rectX, rectY); // puts the owl in
        score();
        gameOver();
        if (gameOver()) { // corrected with chatgpt
            System.out.println("Game Over");
            scene = 3;
        }
        if (scene == 1) { // shows the intro screen
            background(0, 0, 80);
            textSize(20);
            text("Dr. Moden didn't do his Spanish lesson! So get him!", 100, 100);
            text("Be careful not to get the other person! He did his lesson!", 100, 150);
            text("Use W, A, S, and D to move around!", 100, 200);
            text("Press space to start!", 250, 500);
        }
        if (scene == 3) { // shows the game over screen
            background(0, 0, 80);
            text("Game over!", 250, 250);
            text("Press r to restart!", 225, 500);
            fill(255);
            text("Score: " + score, 100, 100);
            text("High Score: " + highScore, 400, 100);
        }
        if (scene == 2) { // shows the playing screen
            fill(0, 0, 0);
            text("Score: " + score, 100, 100);
            text("High Score: " + highScore, 400, 100);
        }
        runAway();
        highScore();
        enemyRun();
    }

    public void keyPressed() { // the code for having the owl move
        if (key == ' ') {
            scene = 2;
            extra = 0;
        }
        if (key == 'w') {
            left = false;
            right = false;
            down = false;
            up = true;
            speed = 5 + extra;
        }
        if (key == 's') {
            left = false;
            up = false;
            right = false;
            down = true;
            speed = 5 + extra;
        }
        if (key == 'd') {
            left = false;
            up = false;
            down = false;
            right = true;
            speed = 5 + extra;
        }
        if (key == 'a') {
            up = false;
            down = false;
            right = false;
            left = true;
            speed = 5 + extra;
        }
        if (key == 'r') {
            fill(255, 255, 255);
            scene = 1;
            score = 0;
            rectX = 0;
            rectY = 0;
            speed = 0;
        }
    }

    public void score() { // keeps track of the score and adds speed
        if (collision(rectX, rectY, foodX, foodY)) {
            score = score + 1;
            System.out.println(score);
            background(background);
            foodX = random(550);
            foodY = random(550);
            extra += .1;
        }
    }

    public boolean gameOver() { // error corrected with chatgpt, keeps track of when the game is done
        if (rectX + 50 > 600 || rectX < 0 || rectY + 50 > 600 || rectY < 0) { // corrected with chatgpt
            scene = 3;
            return true;
        } else if (collision(rectX, rectY, enemyX, enemyY)) {
            scene = 3;
            return true;
        } else {
            return false;
        }
    }

    public void runAway() { // has Dr. Moden run away
        if (scene == 2) {
            image(escapee, foodX, foodY);
        }
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

    public void enemyRun() { // has the enemy move
        if (scene == 2) {
            image(enemy, enemyX, enemyY);
        }
        if (frameCount % 50 == 0) {
            enemySpeedX = random(-3, 3);
            enemySpeedY = random(-3, 3);
        }
        if (enemyX + 50 > 600 || enemyX < 0 || enemyY + 50 > 600 || enemyY < 0) {
            enemySpeedX = -enemySpeedX;
            enemySpeedY = -enemySpeedY;
        }
        enemyX += enemySpeedX;
        enemyY += enemySpeedY;
    }

    public void highScore() { // keeps track of the high score
        if (score >= highScore) {
            highScore = score;
            System.out.println(highScore);
        }
    }

    public boolean collision(float w, float x, float y, float z) {
        if (dist(w, x, y, z) < (50 / 2 + 50 / 2)) {
            return true;
        }
        if ((dist(w, x, y, z) < (50 / 2 + 50 / 2))) {
            return true;
        } else {
            return false;
        }

    }
}