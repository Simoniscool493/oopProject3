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
    pos.x = scale;
    pushMatrix();
    translate(map(pos.x, 0, 255, 150, width-150), pos.y);
    stroke(0);
    fill(C);
    ellipse(0, 0, 25,25);
    popMatrix();
  }
}
