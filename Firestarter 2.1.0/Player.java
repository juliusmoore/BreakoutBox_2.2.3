public class Player {

private int X = 100, Y = 100;
private boolean Left, Right, Up, Down;

public void update() {
move();
}



public void move() {
if (Left) {X--;}
if (Right) {X++;}
if (Up) {Y--;}
if (Down) {Y++;}
}



public void left (boolean toggleLeft) {
	Left = toggleLeft;
}
public void up (boolean toggleUp) {
	Up = toggleUp;
}
public void down (boolean toggleDown) {
	Down = toggleDown;
}
public void right (boolean toggleRight) {
	Right = toggleRight;
}

public int x() {
	return X;
}
public int y() {
	return Y;
}



}