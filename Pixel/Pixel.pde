color userC;

float x, y;

int stages;
int mode;
int pixNum;
float boxSize;
PVector mouse;

boolean colourMenu;
boolean showLines;

ArrayList<Square> squares = new ArrayList<Square>();
ArrayList<Sliders> slide = new ArrayList<Sliders>();

void setup()
{
  size(500, 500);
  stages = 0;
  boxSize = 0;
  
  userC = (255);
  mode = 1;
  
  showLines = true;
  colourMenu = false;
  
  //Create 3 sliders
  Sliders red = new Sliders(150, color(255,0,0));
  slide.add(red);
  
  Sliders green = new Sliders(250, color(0,255,0));
  slide.add(green);
  
  Sliders blue = new Sliders(350, color(0,0,255));
  slide.add(blue);
}

void draw()
{
  background(200);
  rectMode(CORNER);
 
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
     
     boxSize = float(width) / float(pixNum);
     
     if(showLines == true)
     {
       for(int i = 1; i < pixNum+1; i++)
       {
         //Vertical lines
         stroke(0);
         line(boxSize*i, 0, boxSize*i, height);
       
         //Horizontal lines
         line(0, boxSize*i, width, boxSize*i);
       }
     }
     
     for(int i = 0; i < squares.size(); i++)
     {
       Square entity = squares.get(i);
       entity.drawShape();
     }
     
     if(colourMenu == true)
     {
       ColourMenu();
     }
     
     //println(pixNum);
     break;
   }
}

void ColourMenu()
{
  //Colour menu
  stroke(255);
  fill(255);
  rect(50,50,400,400);
       
  for(int i = 0; i < slide.size(); i++)
  {
    line(150, 150+(i*100), width-150, 150+(i*100));
    
    Sliders entity = slide.get(i);
    entity.drawShape();
  }
}

void mousePressed()
{
  mouse = new PVector(mouseX,mouseY);
  
  switch (mode)
  {
    case 1:
    if(stages == 1 && colourMenu == false )
    {
      x = int((mouseX/boxSize));
      y = int((mouseY/boxSize));
      
      Square a = new Square(x*boxSize, y*boxSize, userC);
      squares.add(a);
    }
    break;
    
    case 2:
    
    if(stages == 1 && colourMenu == false)
    {
      for(int i = 0; i < squares.size(); i++)
      {
        Square temp = squares.get(i);
        
        if(temp.pos.dist(mouse) < boxSize/2)
        {
          squares.remove(i);
        }
      }
    }
    break;
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

void mouseDragged()
{
  
  //check if mouse is on slider
  if(colourMenu == true)
  {
    for(int i = 0; i < slide.size(); i++)
    {
      Sliders temp = slide.get(i);
        
      if(temp.pos.dist(mouse) < 25)
      {
          println(int(temp.scale+(mouse.x - mouseX)));
          temp.scale = int(temp.scale+(mouse.x - mouseX));
          println(temp.scale);
      }
    }
  }
}

void keyPressed()
{
  if(stages == 1)
  {
    if(key == 't')
    {
      showLines = !showLines;
    }
  
    if(key == 'c')
    {
      colourMenu = !colourMenu;
    }
    
    //Draw mode
    if(key == 'd')
    {
      mode = 1;
    }
    
    if(key == 'e')
    {
      mode = 2;
    }
  }
}
