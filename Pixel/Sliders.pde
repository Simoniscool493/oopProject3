class Sliders
{
  PVector pos;
  
  int x, y, scale;
  color C;
  
  Sliders(int y, color C)
  {
    this.scale = 255;
    this.C = C;
    this.pos = new PVector(scale,y);
  }
  
  void drawShape()
  {
    pushMatrix();
    translate(pos.x, pos.y);
    rectMode(CENTER);
    stroke(0);
    fill(C);
    rect(0, 0, 50,50);
    popMatrix();
  }
}
