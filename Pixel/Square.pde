class Square
{
  PVector pos;
  
  float x;
  float y; 
  color c; 
  
  Square(float x, float y, color c)
  {
    this.x = x;
    this.y = y;
    this.c = c;
    this.pos = new PVector(x+(boxSize/2),y+(boxSize/2));
  }
  
  void drawShape()
  {
    rectMode(CORNER);
    stroke(c);
    fill(c);
    rect(x, y, boxSize,boxSize);
  }
}
