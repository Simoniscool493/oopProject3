color userC;

float x, y;

int stages;
int pixNum;
float boxSize;

boolean colourMenu;
boolean showLines;

ArrayList<Square> squares = new ArrayList<Square>();

void setup()
{
  size(500, 500);
  stages = 0;
  boxSize = 0;
  
  userC = (255);
  
  showLines = true;
  colourMenu = false;
}

void draw()
{
  background(200);
 
  switch (stages)
  {
     case 0:
   
     textAlign(CENTER);
     fill(0);
     stroke(0);
     text("How many pixels would you want to work with?",width/2, 100);
     text("16 x 16",width/2, 200);
     text("32 x 32",width/2, 300);
     break;
  
     case 1:
     
     //Colour menu
     if(colourMenu == true)
     {
       
     }
     
     boxSize = float(width) / float(pixNum);
     
     if(showLines == true)
     {
       for(int i = 1; i < pixNum+1; i++)
       {
         //Vertical lines
         stroke(0);
         line(boxSize*i, 0, boxSize*i, height);
         println(boxSize);
       
         //Horizontal lines
         line(0, boxSize*i, width, boxSize*i);
       }
     }
     
     for(int i = 0; i < squares.size(); i++)
     {
       Square entity = squares.get(i);
       entity.drawShape();
     }
     
     //println(pixNum);
     break;
   }
}

void mousePressed()
{
  if(stages == 1 && colourMenu == false)
  {
    x = int((mouseX/boxSize));
    y = int((mouseY/boxSize));
    
    Square a = new Square(x*boxSize, y*boxSize, userC);
    squares.add(a);
  }
  
  if(stages == 0)
  {
    if(mouseX > 100 && mouseX < width-100 && mouseY < 220 && mouseY > 180)
    {
      pixNum = 16;
      stages = 1;
    }
    
    if(mouseX > 100 && mouseX < width-100 && mouseY < 320 && mouseY > 280)
    {
      pixNum = 32;
      stages = 1;
    }
  }
}

void keyPressed()
{
  if(key == 't')
  {
    showLines = !showLines;
  }
  
  if(key == 'c')
  {
    colourMenu = true;
  }
}
