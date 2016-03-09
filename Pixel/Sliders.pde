class Sliders
{
  PVector pos;
  
  int x, y;
  float scale;
  color C;
  
  Sliders(int y, color C)
  {
    this.scale = 255.0;
    this.C = C;
    this.pos = new PVector(map(scale, 0, 255,150,width-150),y);
  }
  
  void drawShape()
  {
    pos.x = map(scale, 0, 255,150,width-150);
    pushMatrix();
    translate(map(scale, 0, 255, 150, width-150), pos.y);
    stroke(0);
    fill(C);
    ellipse(0, 0, 25,25);
    popMatrix();
  }
}
