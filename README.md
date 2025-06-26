# DX Ball Game 🎮

A classic brick-breaker game implementation in Java using the StdDraw library. Control a paddle to bounce a ball and destroy all bricks to win!

## 🎥 Demo Videos

- **Main Game**: [Watch on YouTube](https://youtu.be/tBmkqZIBL0c)
- **Updated Version**: [Watch on YouTube](https://youtu.be/aMzHOANtHeo)

## 🎯 Game Features

- **Dynamic Ball Physics**: Realistic ball movement with velocity-based mechanics
- **Angle Control**: Use arrow keys to set the initial launch angle with visual feedback
- **Paddle Control**: Smooth paddle movement with collision detection
- **Brick Breaking**: Multiple rows of colorful bricks with scoring system
- **Pause/Resume**: Press spacebar to pause and resume gameplay
- **Score System**: Earn 10 points for each brick destroyed
- **Win/Lose Conditions**: Victory screen when all bricks are destroyed, game over when ball hits bottom
- **Corner Collision Physics**: Advanced collision detection using normal vectors

## 🕹️ Controls

| Key | Action |
|-----|--------|
| `←` | Rotate launch angle counter-clockwise / Move paddle left |
| `→` | Rotate launch angle clockwise / Move paddle right |
| `Spacebar` | Launch ball / Pause-Resume game |

## 🚀 How to Play

1. **Setup Phase**: Use left and right arrow keys to adjust the launch angle (0-180 degrees)
2. **Launch**: Press spacebar to launch the ball at the selected angle
3. **Gameplay**: Move the paddle left and right to keep the ball in play
4. **Objective**: Destroy all bricks by hitting them with the ball
5. **Scoring**: Each brick destroyed gives you 10 points
6. **Win Condition**: Destroy all bricks to achieve victory
7. **Lose Condition**: Don't let the ball hit the bottom of the screen

## 🏗️ Game Architecture

### Core Components

- **Ball**: Dynamic circle with velocity-based movement and collision physics
- **Paddle**: Player-controlled rectangle with smooth movement
- **Bricks**: Grid of destructible rectangles with color variety
- **UI Elements**: Angle display, score counter, game status messages

### Physics Implementation

#### Ball Movement
```java
x_Velocity = ball_velocity * cos(movingAngle)
y_Velocity = ball_velocity * sin(movingAngle)
```

#### Collision Detection
- **Wall Collisions**: Simple velocity inversion
- **Paddle Collisions**: Edge and corner detection with appropriate bouncing
- **Brick Collisions**: Advanced collision with normal vector calculations

#### Corner Collision Physics
For corner collisions, the game uses vector reflection:
```java
Vnew = Vold - 2(Vold · N) N
```
Where N is the normalized normal vector from collision point.

## 📁 Project Structure

```
DX-Ball/
├── DXBall.java          # Original game implementation
├── DxBall2.java         # Updated version with enhanced features
├── Report.pdf           # Detailed technical report
└── README.md            # This file
```

## 🔧 Technical Details

### Dependencies
- **StdDraw Library**: Used for graphics rendering, input handling, and animations
- **Java AWT**: For color management and font rendering

### Game Versions

#### DXBall.java (Original)
- Ball velocity: 5
- Paddle dimensions: 60x5 (half-width x half-height)
- Standard game over message
- Red-themed brick colors

#### DxBall2.java (Enhanced)
- Ball velocity: 10 (increased difficulty)
- Paddle dimensions: 40x5 (smaller paddle for more challenge)
- Updated brick layout with more rows
- Enhanced color scheme with golden/red theme
- Humorous game over message: "Error 404: Skills Not Found!"

### Key Algorithms

1. **Line Rotation**: Uses trigonometric functions to calculate line endpoints
2. **Collision Detection**: AABB (Axis-Aligned Bounding Box) collision detection
3. **Vector Mathematics**: Normal vector calculation and reflection for realistic physics
4. **Double Buffering**: Smooth animations using StdDraw's double buffering

## 💻 Setup and Installation

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- StdDraw library (included in Princeton's algs4.jar)

### Installation Steps

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/dx-ball-game.git
   cd dx-ball-game
   ```

2. **Download StdDraw**:
   - Download `algs4.jar` from [Princeton's Algorithms website](https://algs4.cs.princeton.edu/code/)
   - Add it to your classpath

3. **Compile and Run**:
   ```bash
   # For original version
   javac -cp ".:algs4.jar" DXBall.java
   java -cp ".:algs4.jar" DXBall
   
   # For enhanced version
   javac -cp ".:algs4.jar" DxBall2.java
   java -cp ".:algs4.jar" DxBall2
   ```

## 🎨 Customization

The game is highly customizable. You can modify:

- **Ball Properties**: Speed, color, size
- **Paddle Properties**: Size, speed, color
- **Brick Layout**: Positions, colors, arrangement
- **Game Mechanics**: Scoring system, difficulty levels
- **Visual Elements**: Colors, fonts, messages

Example customization in the code:
```java
// Game Components (Easily customizable)
double ballVelocity = 10;  // Adjust ball speed
Color ballColor = new Color(15, 82, 186);  // Change ball color
double paddleSpeed = 20;  // Modify paddle speed
```

## 🐛 Known Issues

- Ball may occasionally get stuck in rapid brick-paddle-wall collision scenarios
- High-speed gameplay might cause minor collision detection inconsistencies

## 🤝 Contributing

Contributions are welcome! Feel free to:
- Report bugs
- Suggest new features
- Submit pull requests
- Improve documentation

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 🏆 Achievements

- ✅ Implemented realistic physics with vector mathematics
- ✅ Created smooth animations using double buffering
- ✅ Designed intuitive controls and user interface
- ✅ Built comprehensive collision detection system
- ✅ Added pause/resume functionality
- ✅ Implemented win/lose conditions with appropriate feedback

## 📞 Contact

If you have any questions or suggestions, feel free to reach out!

---

**Enjoy playing DX Ball! 🎮** Break those bricks and aim for the high score!
