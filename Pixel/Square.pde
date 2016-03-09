class Square
{
  float x;
  float y; 
  color c; 
  
  Square(float x, float y, color c)
  {
    this.x = x;
    this.y = y;
    this.c = c;
  }
  
  void drawShape()
  {
    stroke(c);
    fill(c);
    rect(x, y, boxSize,boxSize);
  }
}
