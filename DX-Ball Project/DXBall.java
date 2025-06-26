import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;

public class DXBall {
    public static void main(String args[]){

        // Canvas properties, scale and set the canvas with the given parameters
        double xScale = 800.0, yScale = 400.0;
        StdDraw.setCanvasSize(800, 400);
        StdDraw.setXscale(0.0, xScale);
        StdDraw.setYscale(0.0, yScale);

        // Color array for bricks
        Color[] colors = { new Color(255, 0, 0), new Color(220, 20, 60),
                new Color(178, 34, 34), new Color(139, 0, 0),
                new Color(255, 69, 0), new Color(165, 42, 42)
        };

        // Game Components (These can be changed for custom scenarios)
        double ballVelocity = 5; // Magnitude of the ball velocity
        Color ballColor = new Color(15, 82, 186); // Color of the ball
        double[] initialBallPos = {400,18}; //Initial position of the ball in the format {x, y}
        double[] paddlePos = {400, 5}; // Initial position of the center of the paddle
        double paddleSpeed = 20; // Paddle speed
        Color paddleColor = new Color(128, 128, 128); // Paddle colo

         // 2D array to store center coordinates of bricks in the format {x, y}
        double[][] brickCoordinates = new double[][]{
                {250, 320},{350, 320},{450, 320},{550, 320},
                {150,300},{250, 300},{350, 300},{450, 300},{550, 300},{650, 300},
                {50, 280},{150, 280},{250, 280},{350, 280},{450, 280},{550, 280},{650, 280},{750, 280},
                {50, 260},{150, 260},{250, 260},{350, 260},{450, 260},{550, 260},{650, 260},{750, 260},
                {50, 240},{150, 240},{250, 240},{350, 240},{450, 240},{550, 240},{650, 240},{750, 240},
                {150, 220},{250, 220},{350, 220},{450, 220},{550, 220},{650, 220},
                {250, 200},{350, 200},{450, 200},{550, 200}};

        // Brick colors
        Color [] brickColors = new Color[] {
                colors[0], colors[1], colors[2], colors[3],
                colors[2], colors[4], colors[3], colors[0], colors[4], colors[5],
                colors[5], colors[0], colors[1], colors[5], colors[2], colors[3], colors[0], colors[4],
                colors[1], colors[3], colors[2], colors[4], colors[0], colors[5], colors[2], colors[1],
                colors[4], colors[0], colors[5], colors[1], colors[2], colors[3], colors[0], colors[5],
                colors[1], colors[4], colors[0], colors[5], colors[1], colors[2],
                colors[3], colors[2], colors[3], colors[0]};

        double ballRadius = 8; // Ball radius
        double paddleHalfwidth = 60; // Paddle half width
        double paddleHalfheight = 5; // Paddle half height
        double  brickHalfwidth = 50; // Brick half width
        double  brickHalfheight = 10; // Brick half height


        // Rest is mine
        double[] dimensions = new double[]{paddleHalfwidth,paddleHalfheight,brickHalfwidth,brickHalfheight};
        Color[] exactcolors = new Color[]{
                ballColor,
                paddleColor};
        int brickCount = brickCoordinates.length;

        StdDraw.enableDoubleBuffering();   // enabling double buffering for faster animations

        StdDraw.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 20));   // setting the font features
        drawBackground(initialBallPos,ballRadius, paddlePos, brickColors,brickCoordinates,dimensions,exactcolors);  // drawing the initial background

        // drawing the line initially and finding its length
        double[] lineCoordinates = {initialBallPos[0],initialBallPos[1],initialBallPos[0]+50,initialBallPos[1]};
        StdDraw.line(lineCoordinates[0],lineCoordinates[1],lineCoordinates[2],lineCoordinates[3]);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(60,380,"Angle: "+ "0.0");
        StdDraw.show();
        double LineLength = Math.sqrt(Math.pow(lineCoordinates[2] - lineCoordinates[0], 2) + Math.pow(lineCoordinates[3] - lineCoordinates[1], 2));
        double angle = 0.0;
        boolean isStarted = false;
        int score = 0;

        // while loop for angle determining
        while(!isStarted){
            if(StdDraw.isKeyPressed(37)) {  // detecting if left key is pressed
                if (Math.toDegrees(angle) < 180) {
                    angle += Math.PI / 90;  // Increasing the angle by 2 degrees ( counter clocwise)
                    StdDraw.line(lineCoordinates[0], lineCoordinates[1], lineCoordinates[2], lineCoordinates[3]);

                    StdDraw.clear();
                    drawBackground(initialBallPos,ballRadius, paddlePos, brickColors,brickCoordinates,dimensions,exactcolors);

                    StdDraw.setPenColor(StdDraw.RED);
                    lineCoordinates[2] = initialBallPos[0] + LineLength * Math.cos(angle);  // x2 = x1 + r * cos(angle)
                    lineCoordinates[3] = initialBallPos[1] + LineLength * Math.sin(angle);  // y2 = y1 + r * sin(angle)
                    StdDraw.line(lineCoordinates[0], lineCoordinates[1], lineCoordinates[2], lineCoordinates[3]);
                    // displaying the angle onto canvas
                    StdDraw.setPenColor(StdDraw.BLACK);
                    double displayedAngle = Math.toDegrees(angle);
                    if(displayedAngle < 0.05) displayedAngle = 0.0;
                    StdDraw.text(60, 380, "Angle: " + String.format(Locale.US,"%.1f", displayedAngle ));
                }
            }
            if (StdDraw.isKeyPressed(39)) {  // detecting if the right key is pressed
                if (Math.toDegrees(angle) >0.002) {  // To prevent the angle from falling below 0 degrees (0.002 since 0 is not exactly 0.000)
                    angle -= Math.PI / 90;  // Decrease the angle (rotate clockwise)

                    StdDraw.clear();
                    drawBackground(initialBallPos,ballRadius, paddlePos, brickColors,brickCoordinates,dimensions,exactcolors); // drawing the background after each changing

                    // Set the new color for the line
                    StdDraw.setPenColor(StdDraw.RED);
                    lineCoordinates[2] = initialBallPos[0] + LineLength * Math.cos(angle);  // x2 = x1 + r * cos(angle)
                    lineCoordinates[3] = initialBallPos[1] + LineLength * Math.sin(angle);  // y2 = y1 + r * sin(angle)

                    // Draw the updated line
                    StdDraw.line(lineCoordinates[0], lineCoordinates[1], lineCoordinates[2], lineCoordinates[3]);

                    // Display the updated angle
                    StdDraw.setPenColor(StdDraw.BLACK);
                    double displayedAngle = Math.toDegrees(angle);
                    if(displayedAngle < 0.05) displayedAngle = 0.0;
                    StdDraw.text(60, 380, "Angle: " + String.format(Locale.US,"%.1f", displayedAngle ));
                }
            }
            if(StdDraw.isKeyPressed(32)){  // Detecting if the space bar is pressed
                isStarted = true;  // changing the boolean variable isStarted to escape the loop
                StdDraw.clear();
                drawBackground(initialBallPos, ballRadius,paddlePos, brickColors,brickCoordinates,dimensions,exactcolors);
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.text(730,380,"Score: "+ score);
                StdDraw.pause(100);
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        // Assigning some variables for the game flow and calculating the balls position with the angle
        boolean isGameOver = false;
        boolean isPaused = false;
        double movingAngle = angle;
        double xVelocity = ballVelocity * Math.cos(movingAngle);
        double yVelocity = ballVelocity * Math.sin(movingAngle);
        double[] ballPositions = {initialBallPos[0] + xVelocity ,initialBallPos[1] + yVelocity};

        while(!isGameOver) {  // checking if the game is over (victory/lose)
            while (!isPaused) { // checking if the game is paused
                if (StdDraw.isKeyPressed(37) && (paddlePos[0] > 60)) { // left
                    paddlePos[0] -= paddleSpeed;
                }
                if (StdDraw.isKeyPressed(39) && (paddlePos[0] < 740)) { // right
                    paddlePos[0] += paddleSpeed;
                }
                if (StdDraw.isKeyPressed(32)) { // space bar
                    isPaused = true;
                    StdDraw.pause(20);
                }
                // changing the balls positions with velocity at each iteration
                ballPositions[0] += xVelocity;
                ballPositions[1] += yVelocity;
                StdDraw.clear();
                StdDraw.setPenColor(paddleColor);
                // Using a method for determining if the collision is occurred and where it is occurred and updating variables wrt. that
                ArrayList<Object> mixedData = whereIsCollided(ballPositions,brickCoordinates,paddlePos,
                        xVelocity,yVelocity,score,brickColors,isGameOver,dimensions);
                xVelocity = (double ) mixedData.get(0);
                yVelocity = (double) mixedData.get(1);
                score = (int) mixedData.get(2);
                brickCoordinates = (double[][]) mixedData.get(3);
                brickColors = (Color[]) mixedData.get(4);
                isGameOver = (boolean) mixedData.get(5);
                if (isGameOver) {
                    isPaused = isGameOver;
                }

                drawBackground(ballPositions,ballRadius, paddlePos, brickColors,brickCoordinates,dimensions,exactcolors);
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.text(730,380,"Score: "+ score);
                StdDraw.show();
                if (score == brickCount*10){
                    isPaused = true;
                    isGameOver = true;
                }
                StdDraw.pause(20);
            }
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(45,380,"PAUSE");
            StdDraw.show();
            if(StdDraw.isKeyPressed(32)){
                isPaused = false;
                StdDraw.pause(50);
            }
        }

        endGame(score,brickCount);


    }
    // Drawing background method (ball,paddle,bricks)
    public static void drawBackground(double[] ballPositions, double ballRadius, double[] paddlePositions, Color[] brickColors ,double[][] brickCoordinates,double[] dimensions, Color[] exactColors){
        StdDraw.setPenColor(exactColors[0]);
        StdDraw.filledCircle(ballPositions[0],ballPositions[1],ballRadius);
        StdDraw.setPenColor(exactColors[1]);
        StdDraw.filledRectangle(paddlePositions[0],paddlePositions[1], dimensions[0], dimensions[1]);
        // drawing all the bricks with a for loop
        for(int i =0;i <brickCoordinates.length;i++){
            StdDraw.setPenColor(brickColors[i]);
            StdDraw.filledRectangle(brickCoordinates[i][0],brickCoordinates[i][1], dimensions[2], dimensions[3]);
        }

    }
    // method for determining if the collision is occurred and where it is occurred and arranging the variables with respect to the situation
    public static ArrayList<Object> whereIsCollided(double[] ballsLocation, double[][] brickLocations, double[] paddleLocations,
                                                    double xVel, double yVel, int newScore, Color[] brickColors, boolean isOver, double[] dimensions){   // xVel, yVel,brickLocations,score
        ArrayList<Object> mixedData = new ArrayList<>();  // an array list to store different datatypes
        double xBall = ballsLocation[0];
        double yBall = ballsLocation[1];
        double xPaddle = paddleLocations[0];
        double yPaddle = paddleLocations[1];
        double paddleHalfwidth = dimensions[0], paddleHalfheight =   dimensions[1], brickHalfwidth = dimensions[2], brickHalfheight = dimensions[3];

        // wall collision
        if ((xBall - 8 <= 0) || (xBall + 8 >= 800)) { // Left or right wall
            xVel = -xVel;
        }
        if (yBall - 8 <= 0) { //  Bottom wall (Game Over)
            isOver = true;
        }
        if (yBall + 8 >= 400) { // Top wall
            yVel = -yVel;
        }

        // Paddle collision detection
        if (xBall + 8 >= xPaddle - paddleHalfwidth &&
                xBall - 8 <= xPaddle + paddleHalfwidth &&
                yBall + 8 >= yPaddle - paddleHalfheight &&
                yBall - 8 <= yPaddle + paddleHalfheight) {

            boolean cornerHit = (Math.abs(xBall - xPaddle) >= (paddleHalfwidth)) &&
                    (Math.abs(yBall - yPaddle) >= (paddleHalfheight));
            boolean verticalEdgeHit = Math.abs(xBall - xPaddle) >= paddleHalfwidth;
            // Corner hit
            if (cornerHit) {
                double[] normal = normalizeVector(xBall - xPaddle, yBall - yPaddle);
                double[] newVelocity = calculateNewVelocity(xVel, yVel, normal);
                xVel = newVelocity[0];
                yVel = newVelocity[1];
            }
            else if (verticalEdgeHit) {
                xVel = -xVel; // Bounce off vertical edges
            }
            else {
                yVel = -yVel; // Standard bounce off the paddle's top surface
            }
        }

        // Brick collision
        ArrayList<double[]> updatedBrickLocations = new ArrayList<>();
        ArrayList<Color> updatedBrickColors = new ArrayList<>();
        boolean yVelocityChanged = false;
        boolean xVelocityChanged = false;
        for (int j = 0; j < brickLocations.length; j++) {
            double xBrick = brickLocations[j][0];
            double yBrick = brickLocations[j][1];

            if (xBall + 8 >= xBrick - brickHalfwidth &&
                    xBall - 8 <= xBrick + brickHalfwidth &&
                    yBall + 8 >= yBrick - brickHalfheight &&
                    yBall - 8 <= yBrick + brickHalfheight) {
                // Check where did the ball hit the brick from
                boolean cornerHit = (Math.abs(xBall - xBrick) > brickHalfwidth) && (Math.abs(yBall - yBrick) > brickHalfheight);
                boolean horizontalEdgeHit = xBall >= xBrick - brickHalfwidth && xBall <= xBrick + brickHalfwidth;
                boolean verticalEdgeHit = yBall >= yBrick - brickHalfheight && yBall <= yBrick + brickHalfheight;
                if (cornerHit) {
                    double[] normal = normalizeVector(xBall - xBrick, yBall - yBrick);
                    double[] newVelocity = calculateNewVelocity(xVel, yVel, normal);
                    if (!xVelocityChanged) {
                        xVel = newVelocity[0];
                        xVelocityChanged = true;
                    }
                    // Change y_vel only if it hasn't changed yet in this loop
                    if (!yVelocityChanged) {
                        yVel = newVelocity[1];
                        yVelocityChanged = true; // Mark that we changed y_vel
                    }
                }
                else { // Edge hit
                    // Change y_vel only once, preventing double inversion in intersections
                    if (horizontalEdgeHit && !yVelocityChanged) { // Bounce off horizontal edges
                        yVel = -yVel;
                        yVelocityChanged = true;
                    }
                    if (verticalEdgeHit && !xVelocityChanged) { // Bounce off vertical edges
                        xVel = -xVel;
                        xVelocityChanged = true;
                    }
                }

                newScore += 10; // Increase score
                continue; // Skip adding this brick (removal)
            }
            updatedBrickLocations.add(brickLocations[j]);
            updatedBrickColors.add(brickColors[j]);
        }

        // Convert lists back to arrays
        double[][] newBrickLocations = updatedBrickLocations.toArray(new double[0][0]);
        Color[] newBrickColors = updatedBrickColors.toArray(new Color[0]);

        // Add values to the result list
        mixedData.add(xVel);
        mixedData.add(yVel);
        mixedData.add(newScore);
        mixedData.add(newBrickLocations);
        mixedData.add(newBrickColors);
        mixedData.add(isOver);
        return mixedData;
    }
    // Finding the normal vector to use to handle corner collisions
    public static double[] normalizeVector(double x, double y) {
        double magnitude = Math.sqrt(x * x + y * y);
        return new double[]{x / magnitude, y / magnitude};
    }

    // Calculate new velocity using Vnew = Vold - 2(Vold . N) N
    public static double[] calculateNewVelocity(double vx, double vy, double[] normal) {
        double dotProduct = vx * normal[0] + vy * normal[1];
        double newVx = vx - 2 * dotProduct * normal[0];
        double newVy = vy - 2 * dotProduct * normal[1];
        return new double[]{newVx, newVy};
    }
    // Finishing the game method (clear the unnecessary texts and write game over or victory message)
    public static void endGame(int score, int brickCount){
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(45,380,50,20);
        StdDraw.filledRectangle(730,380,50,20);
        StdDraw.show();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 45));
        if (score !=brickCount*10 ){
            StdDraw.text(400,100,"GAME OVER!");
        }
        else{
            StdDraw.text(400,100,"VICTORY!");
        }
        StdDraw.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 30));
        StdDraw.text(400,50,"Score: " + score);
        StdDraw.show();
    }
}


