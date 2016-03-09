int stages;
int pixNum;
float boxSize;

boolean colourMenu;
boolean showLines;


void setup()
{
  size(500, 500);
  stages = 0;
  boxSize = 0;
  
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
     boxSize = width / pixNum;
     
     if(showLines == true)
     {
       for(int i = 1; i < pixNum+1; i++)
       {
         //Vertical lines
         line(boxSize*i, 0, boxSize*i, height);
       
         //Horizontal lines
         line(0, boxSize*i, width, boxSize*i);
       }
     }
     println(pixNum);
     break;
   }
}

void mousePressed()
{
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
  
  if(stages == 1)
  {
    
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
