import java.util.Scanner; 
import java.io.*; 
import java.text.DecimalFormat;

public class Khan3
{

public static void main(String[] args) throws IOException
{
   int TotalVertices, NumOfIslands, i, j, k, l, p, q;
   int m = 0; int Bridges = 0; int FootPrint = 0;
   
   DecimalFormat df = new DecimalFormat("0.000");   
       
   double x1, x2, y1, y2, StoreX, StoreY, PowerX, PowerY; 
   double StoreTemp = 1000000000.00; double StoreTotal = 1000000000.00; double StoreFinal; double Total = 0.00; 
   
   PQItem[] StoreShort; PQItem X = new PQItem(); 
      
   String FileName;
   Scanner Keyboard = new Scanner (System.in); 
   Scanner InFile;  
   System.out.println("Please enter the name of the input file: "); 
   FileName = Keyboard.nextLine(); 
   InFile = new Scanner (new File(FileName));
   //Declare all variables 
   
   NumOfIslands = InFile.nextInt(); 
   Polygon[] Islands = new Polygon[NumOfIslands];
   for (i = 0; i<NumOfIslands; i++)
   {
      Islands[i] = new Polygon(); 
   } 
   
   for (i = 0; i<NumOfIslands; i++)
   {
      Islands[i].NumOfVertices = InFile.nextInt();             
      for (j = 0; j<Islands[i].NumOfVertices; j++)
      {            
         Islands[i].Vertices[j].x = InFile.nextInt(); 
         Islands[i].Vertices[j].y = InFile.nextInt();            
      }          
      //end inner for
   }       
      //end outer for
   //Load data file into Islands array and Vertices arrays         
   
   StoreShort = new PQItem[Recursion(NumOfIslands)];
   for (i=0; i<Recursion(NumOfIslands); i++)
   {
      StoreShort[i] = new PQItem(); 
   }  
   
   for (i = 0; i<NumOfIslands; i++)
      { 
         for (j = i+1; j<NumOfIslands; j++)
         { 
            for (k = 0; k<Islands[i].NumOfVertices; k++)
            {
               for (l = 0; l<Islands[j].NumOfVertices; l++)
               {
                  x1 = Islands[i].Vertices[k].x; 
                  y1 = Islands[i].Vertices[k].y; 
                  x2 = Islands[j].Vertices[l].x; 
                  y2 = Islands[j].Vertices[l].y; 
                  
                  StoreX = Math.pow((x2-x1),2);
                  StoreY = Math.pow((y2-y1),2); 
                  if (Math.sqrt(StoreX+StoreY) < StoreTemp)
                  {
                     StoreTemp = Math.sqrt(StoreX + StoreY); 
                  }
               }  
                  //end inner-most for  
                  
                  if (StoreTemp < StoreTotal) 
                  {
                     StoreTotal = StoreTemp; 
                  }                          
                  
               if (k == Islands[i].NumOfVertices-1) 
               {
                  StoreFinal = StoreTotal; 
                  StoreTotal = 10000000.00;
                  StoreTemp = 10000000.00; 
                  StoreShort[m].Edge = StoreFinal;
                  StoreShort[m].Node1 = i; 
                  StoreShort[m].Node2 = j;  
                  m++; 
               }           
           } 
              //end inner for                         
         }
         //end island 2 for  
      }            
      //end island 1 for  

      PriorityQueue P = new PriorityQueue(Recursion(NumOfIslands)); 
      for (i = 0; i<Recursion(NumOfIslands); i++)
      {
         P.Insert(StoreShort[i]);
      }
      //create priority queue

      int[] CheckPad = new int[NumOfIslands]; 
         for (j=0; j<NumOfIslands; j++) 
          CheckPad[j] = -1-j;
      //create checkpad             
      
      while(!P.IsEmpty())
      {  
         X = (PQItem) P.Remove(); 

         if (CheckPad[X.Node1] == CheckPad[X.Node2])
         { 
         }
         //end condition 1 
         
         if (CheckPad[X.Node1] < 0 && CheckPad[X.Node2] < 0 && CheckPad[X.Node1] != CheckPad[X.Node2])
         {
            CheckPad[X.Node1] = FootPrint; 
            CheckPad[X.Node2] = FootPrint; 
            FootPrint++; 
            Total += X.Edge; 
            Bridges++;  
         } 
         //end condition 2
         
         if (CheckPad[X.Node1] < 0 && CheckPad[X.Node2] >= 0)
         {
             Bridges++;
             Total = Total + X.Edge;
             CheckPad[X.Node1] = CheckPad[X.Node2];     
         }     
         if(CheckPad[X.Node1] >= 0 && CheckPad[X.Node2] < 0)
         {
             Bridges++;
             Total = Total + X.Edge;
             CheckPad[X.Node2] = CheckPad[X.Node1];  
         }
         //end condition 3
         
         if (CheckPad[X.Node1] >= 0 && CheckPad[X.Node2] >= 0 && CheckPad[X.Node1] != CheckPad[X.Node2])
         {
            if(CheckPad[X.Node1] < CheckPad[X.Node2])
            {
               Bridges++;
               Total = Total + X.Edge;
               p = CheckPad[X.Node1];
               q = CheckPad[X.Node2]; 
               for (i=0; i<NumOfIslands; i++)
               {
                  if(CheckPad[i] == q)
                  {
                     CheckPad[i] = p;
                  }
               }                
            }
            if(CheckPad[X.Node2]<CheckPad[X.Node1])
            {
               Bridges++;
               Total = Total + X.Edge;
               p = CheckPad[X.Node2];
               q = CheckPad[X.Node1];
               for (i=0; i<NumOfIslands; i++)
               {
                  if(CheckPad[i] == q)
                  {
                     CheckPad[i] = p;
                  }
               }                  
            }                 
         }
         //end condition 4 
        
      }
      //end while      
      System.out.println("The minimal interconnect consists of " + Bridges + " bridges with a total length of "
        + df.format(Total) + "."); 
}      
//end main

public static int Recursion(int n)
{
   if (n == 3) 
   {
      return 3; 
   }
   int[] StoreArray = new int[n+1]; 
   StoreArray[3] = 3;  
   for (int i = 4; i <= n; i++) 
   {
      StoreArray[i] = StoreArray[i-1] + (i-1); 
   }
      return StoreArray[n];
}
//end recursion

public static class PQItem implements Comparable<PQItem>
{
   int Node1; 
   int Node2; 
   double Edge; 
   
   public int compareTo(PQItem S) 
   {
      if (Edge - S.Edge < 0)
         return 1; 
      else if (Edge - S.Edge > 0)
         return -1; 
      else 
         return 0; 
   }          
   
}
//end class PQItem

public static class Point
{
   int x; 
   int y; 
}
//end class Point

public static class Polygon 
{
   int NumOfVertices;
   Point[] Vertices; 
   
   public Polygon()
   {
      Vertices = new Point[25]; 
      for (int i = 0; i < 25; i++)
         Vertices[i] = new Point();    
   }
   //end Polygon constructor 
}
//end class Polygon 

}
//end class Khan3
