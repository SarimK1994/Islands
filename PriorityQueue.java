public class PriorityQueue 
{
   private Comparable[] HeapArray;
   private int Last, Limit;
   public int Node1, Node2; 
   public double Edge; 

   public PriorityQueue(int Capacity)
   {
      HeapArray = new Comparable[Capacity+1];
      Last      = 0;
      Limit     = Capacity;
      return;
   }
   // end constructor

   public PriorityQueue()
   {
      HeapArray = new Comparable[101];
      Last      = 0;
      Limit     = 100;
      return;
   }
   // end constructor

   public void Insert(Comparable PQI)
   {
      if (Last == Limit)
      {
         System.out.println("Priority Queue Overflow!");
         System.exit(0);
      }
      // end if

      HeapArray[++Last] = PQI;
      this.UpHeap(Last);
      return;
   }
   // end public method Insert

   private void UpHeap(int k)
   {
      Comparable V;

      V = HeapArray[k];
   
      while (k > 1  &&  HeapArray[k/2].compareTo(V) < 0)
      {
         HeapArray[k] = HeapArray[k/2];
         k = k/2;
      }
      // end while

      HeapArray[k] = V;
      return;
   }
   // end private method UpHeap

   public Comparable Remove()
   {
      Comparable PQI ;

      if (Last == 0)
      {
         System.out.println("Priority Queue Underflow!");
         System.exit(0);
      }
      // end if

      PQI = HeapArray[1];
      HeapArray[1] = HeapArray[Last--];
      this.DownHeap(1);
      return PQI;
   }
   // end public method Remove

   private void DownHeap(int k)
   {
      Comparable V;
      int    j;

      V = HeapArray[k];
   
      while (k <= Last/2)
      {
         j = k+k;

         if (j < Last  &&  HeapArray[j].compareTo(HeapArray[j+1]) < 0)
            j++;
         // end if

         if (V.compareTo(HeapArray[j]) >= 0)
            break;
         // end if

         HeapArray[k] = HeapArray[j];
         k = j;
      }
      // end while

      HeapArray[k] = V;
      return;
   }
   // end private method DownHeap

   public boolean IsEmpty()
   {
      if (Last == 0)
         return true;
      else
         return false;
      // end if
   }
   // end public method IsEmpty

   public boolean IsFull()
   {
      if (Last == Limit)
         return true;
      else
         return false;
      // end if
   }
   // end public method IsFull

   public int Length()
   {
      return Last;
   }
   // end public method Length

}
// end class PriorityQueue
