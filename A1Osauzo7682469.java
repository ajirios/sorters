
/**
 * Assignment1
 * 
 * COMP 2140         SECTION A01
 * INSTRUCTOR        Helen Cameron
 * ASSIGNMENT        Assignment 1
 * @author           Ajiri Osauzo Jeffrey, 7682469
 * @version          February 9, 2016
 * 
 * PURPOSE           The purpose of this program is to time and determine the 
 *                   accuracy of three different sorting algorithms, namely 
 *                   insertion sort, merge sort, and quick sort.
 */


//import required packages
import java.util.*;
import java.io.*;

//main class
public class A1Osauzo7682469
  
{
 //declare global variables for multiple methods
 private static long bestSortTime = Integer.MAX_VALUE;
 private static String bestSortName;
 private static int bestSortArrayLength;
 
  public static void main(String[] args)
    
  {
    //declare file input variables
    Scanner keyboard;
    String fileName;
    BufferedReader file;
    int[] array;
    int arrayLength;
    String line;
    
    //declare unsorted arrays for each sorting algorithm
    int[] insertionSortArray;
    int[] mergeSortArray;
    int[] hybridMergeSortArray;
    int[] iterativeMergeSortArray;
    int[] quickSortArray;
    int[] hybridQuickSortArray;
    
    //declare sort timing variables
    long insertionSortStart;
    long insertionSortEnd;
    long insertionSortTime;
    long mergeSortStart;
    long mergeSortEnd;
    long mergeSortTime;
    long hybridMergeSortStart;
    long hybridMergeSortEnd;
    long hybridMergeSortTime;
    long iterativeMergeSortStart;
    long iterativeMergeSortEnd;
    long iterativeMergeSortTime;
    long quickSortStart;
    long quickSortEnd;
    long quickSortTime;
    long hybridQuickSortStart;
    long hybridQuickSortEnd;
    long hybridQuickSortTime;
    
    //initialize the array length as one to reduce memory costs
    arrayLength = 1;
    
    //initialize unsorted arrays for each sorting algorithm
    array = new int[arrayLength];
    insertionSortArray = new int[arrayLength];
    mergeSortArray = new int[arrayLength];
    hybridMergeSortArray = new int[arrayLength];
    iterativeMergeSortArray = new int[arrayLength];
    quickSortArray = new int[arrayLength];
    hybridQuickSortArray = new int[arrayLength];
    
    //print program output header
    System.out.println("\nCOMP 2140\t\tAssignment 1\tWinter 2016");
    System.out.println("Sorting with insertion sort, merge sort, and quick sort.");
    System.out.println("Solution developed by AJIRI OSAUZO JEFFREY (7682469)\n");
    
    //get text file from user
    keyboard = new Scanner(System.in);
    System.out.println("\nEnter the input file name (.txt files only): ");
    fileName = keyboard.nextLine();
    
    try
      
    {
      //try to read the file name provided into buffer
      file = new BufferedReader(new FileReader(fileName));
      line = file.readLine();
      
      //when first line is not empty
      if (line != null)
        
      {
        //read the length of the integer array from the first line
        arrayLength = Integer.parseInt(line);
        
        //instantiate unsorted arrays for each sorting algorithm
        array = new int[arrayLength];
        insertionSortArray = new int[arrayLength];
        mergeSortArray = new int[arrayLength];
        hybridMergeSortArray = new int[arrayLength];
        iterativeMergeSortArray = new int[arrayLength];
        quickSortArray = new int[arrayLength];
        hybridQuickSortArray = new int[arrayLength];
        
        //read next line
        line = file.readLine();
        
        //get subsequent integers iteratively
        for (int count = 0; count < arrayLength && line != null; count++)
          
        {
          
          //store the integers directly into the original array
          array[count] = Integer.parseInt(line);
          
        }
        
      }
      
      //close the file
      file.close();
      
    }
    
    //when exceptions are caught
    catch (IOException e)
      
    {
      
      //print error messages
      System.out.println("IO Error: " + e.getMessage());
      
    }
    
    //copy into unsorted arrays
    System.arraycopy(array, 0, insertionSortArray, 0, array.length);
    System.arraycopy(array, 0, mergeSortArray, 0, array.length);
    System.arraycopy(array, 0, hybridMergeSortArray, 0, array.length);
    System.arraycopy(array, 0, iterativeMergeSortArray, 0, array.length);
    System.arraycopy(array, 0, quickSortArray, 0, array.length);
    System.arraycopy(array, 0, hybridQuickSortArray, 0, array.length);
    
    //separation 
    System.out.println("\n\n************************************************************************************");
    
    //insertion sort
    insertionSortStart = System.nanoTime();
    insertionSort(insertionSortArray);
    insertionSortEnd = System.nanoTime();
    insertionSortTime = insertionSortEnd - insertionSortStart;
    System.out.println("\nTime for ordinary merge sort: " + insertionSortTime + " nanoseconds.");
    printAccuracy(isSorted(insertionSortArray), "Insertion sort", insertionSortArray.length);
    computeBestTime("insertion sort", insertionSortTime, insertionSortArray.length);
    
    //ordinary merge sort
    mergeSortStart = System.nanoTime();
    mergeSort(mergeSortArray);
    mergeSortEnd = System.nanoTime();
    mergeSortTime = mergeSortEnd - mergeSortStart;
    System.out.println("\nTime for ordinary merge sort: " + mergeSortTime + " nanoseconds.");
    printAccuracy(isSorted(mergeSortArray), "Ordinary merge sort", mergeSortArray.length);
    computeBestTime("ordinary merge sort", mergeSortTime, mergeSortArray.length);
    
    //hybrid merge sort with breakpoint 149
    hybridMergeSortStart = System.nanoTime();
    hybridMergeSort(hybridMergeSortArray, 149);
    hybridMergeSortEnd = System.nanoTime();
    hybridMergeSortTime = hybridMergeSortEnd - hybridMergeSortStart;
    System.out.println("\nTime for hybrid merge sort with breakpoint = 149: " + hybridMergeSortTime + " nanoseconds.");
    printAccuracy(isSorted(hybridMergeSortArray), "Hybrid merge sort", hybridMergeSortArray.length);
    computeBestTime("hybrid merge sort with breakpoint 149", hybridMergeSortTime, hybridMergeSortArray.length);
    
    //hybrid merge sort with breakpoint 257
    hybridMergeSortStart = System.nanoTime();
    hybridMergeSort(hybridMergeSortArray, 257);
    hybridMergeSortEnd = System.nanoTime();
    hybridMergeSortTime = hybridMergeSortEnd - hybridMergeSortStart;
    System.out.println("\nTime for hybrid merge sort with breakpoint = 257: " + hybridMergeSortTime + " nanoseconds.");
    printAccuracy(isSorted(hybridMergeSortArray), "Hybrid merge sort", hybridMergeSortArray.length);
    computeBestTime("hybrid merge sort with breakpoint 257", hybridMergeSortTime, hybridMergeSortArray.length);
    
    //hybrid merge sort with breakpoint 277
    hybridMergeSortStart = System.nanoTime();
    hybridMergeSort(hybridMergeSortArray, 277);
    hybridMergeSortEnd = System.nanoTime();
    hybridMergeSortTime = hybridMergeSortEnd - hybridMergeSortStart;
    System.out.println("\nTime for hybrid merge sort with breakpoint = 277: " + hybridMergeSortTime + " nanoseconds.");
    printAccuracy(isSorted(hybridMergeSortArray), "Hybrid merge sort", hybridMergeSortArray.length);
    computeBestTime("hybrid merge sort with breakpoint 277", hybridMergeSortTime, hybridMergeSortArray.length);
    
    //hybrid merge sort with breakpoint 307
    hybridMergeSortStart = System.nanoTime();
    hybridMergeSort(hybridMergeSortArray, 307);
    hybridMergeSortEnd = System.nanoTime();
    hybridMergeSortTime = hybridMergeSortEnd - hybridMergeSortStart;
    System.out.println("\nTime for hybrid merge sort with breakpoint = 307: " + hybridMergeSortTime + " nanoseconds.");
    printAccuracy(isSorted(hybridMergeSortArray), "Hybrid merge sort", hybridMergeSortArray.length);
    computeBestTime("hybrid merge sort with breakpoint 307", hybridMergeSortTime, hybridMergeSortArray.length);
    
    //iterative merge sort
    iterativeMergeSortStart = System.nanoTime();
    iterativeMergeSort(iterativeMergeSortArray);
    iterativeMergeSortEnd = System.nanoTime();
    iterativeMergeSortTime = iterativeMergeSortEnd - iterativeMergeSortStart;
    System.out.println("\nTime for iterative merge sort " + iterativeMergeSortTime + " nanoseconds.");
    printAccuracy(isSorted(iterativeMergeSortArray), "Iterative merge sort", iterativeMergeSortArray.length);
    computeBestTime("iterative merge sort with breakpoint 149", iterativeMergeSortTime, iterativeMergeSortArray.length);
    
    //quick sort
    quickSortStart = System.nanoTime();
    quickSort(quickSortArray);
    quickSortEnd = System.nanoTime();
    quickSortTime = quickSortEnd - quickSortStart;
    System.out.println("\nTime for quick sort: " + quickSortTime + " nanoseconds.");
    printAccuracy(isSorted(quickSortArray), "Quick sort", quickSortArray.length);
    computeBestTime("quick sort", quickSortTime, quickSortArray.length);
    
    //hybrid quick sort with breakpoint 149
    hybridQuickSortStart = System.nanoTime();
    hybridQuickSort(hybridQuickSortArray, 149);
    hybridQuickSortEnd = System.nanoTime();
    hybridQuickSortTime = hybridQuickSortEnd - hybridQuickSortStart;
    System.out.println("\nTime for hybrid quick sort with breakpoint = 149: " + hybridQuickSortTime + " nanoseconds.");
    printAccuracy(isSorted(hybridQuickSortArray), "Hybrid quick sort", hybridQuickSortArray.length);
    computeBestTime("hybrid quick sort with breakpoint 149", hybridQuickSortTime, hybridQuickSortArray.length);
    
    //hybrid quick sort with breakpoint 257
    hybridQuickSortStart = System.nanoTime();
    hybridQuickSort(hybridQuickSortArray, 257);
    hybridQuickSortEnd = System.nanoTime();
    hybridQuickSortTime = hybridQuickSortEnd - hybridQuickSortStart;
    System.out.println("\nTime for hybrid quick sort with breakpoint = 257: " + hybridQuickSortTime + " nanoseconds.");
    printAccuracy(isSorted(hybridQuickSortArray), "Hybrid quick sort", hybridQuickSortArray.length);
    computeBestTime("hybrid quick sort with breakpoint 257", hybridQuickSortTime, hybridQuickSortArray.length);
    
    //hybrid quick sort with breakpoint 277
    hybridQuickSortStart = System.nanoTime();
    hybridQuickSort(hybridQuickSortArray, 277);
    hybridQuickSortEnd = System.nanoTime();
    hybridQuickSortTime = hybridQuickSortEnd - hybridQuickSortStart;
    System.out.println("\nTime for hybrid quick sort with breakpoint = 277: " + hybridQuickSortTime + " nanoseconds.");
    printAccuracy(isSorted(hybridQuickSortArray), "Hybrid quick sort", hybridQuickSortArray.length);
    computeBestTime("hybrid quick sort with breakpoint 277", hybridQuickSortTime, hybridQuickSortArray.length);
    
    //hybrid quick sort with breakpoint 307
    hybridQuickSortStart = System.nanoTime();
    hybridQuickSort(hybridQuickSortArray, 307);
    hybridQuickSortEnd = System.nanoTime();
    hybridQuickSortTime = hybridQuickSortEnd - hybridQuickSortStart;
    System.out.println("\nTime for hybrid quick sort with breakpoint = 307: " + hybridQuickSortTime + " nanoseconds.");
    printAccuracy(isSorted(hybridQuickSortArray), "Hybrid quick sort", hybridQuickSortArray.length);
    computeBestTime("hybrid quick sort with breakpoint 307", hybridQuickSortTime, hybridQuickSortArray.length);
    
    //print the best sorting algorithm
    System.out.println("\n************************************************************************************");
    
    System.out.println("\nThe best sorting method is " + bestSortName + ".");
    System.out.println("It sorted " + bestSortTime + " numbers in " + bestSortArrayLength + " nanoseconds.");
    
    System.out.println("\n************************************************************************************");
    
    //print end of program
    System.out.println("\nProgram ends.\n");
    
    
  }
  
  /**
   * Sort an integer array iteratively, using insertion sort.
   * 
   * @param  int[] a   get an integer array that the calling method needs to sort.
   *                   the integer array is sorted for better processing in ascending order.
   */
  public static void insertionSort(int[] array)
    
  {
    
    //initialize start and end positions and pass to a helper method
    insertionSort(array, 0, array.length);
    
  }
  
  /**
   * Sort an integer array iteratively, using insertion sort.
   * 
   * @param  int[] a    get the integer array from the public driver method.
   *                    the integer array is sorted into ascending order.
   * @param  int start  the position at the first element to be sorted
   *                          
   * @param  int end    the position after the last element to be sorted
   */
  private static void insertionSort(int[] array, int start, int end)
    
  {
   //declare method variables
    int insertionValue;
    int i;
    int j;
    
    //loop through the array from the second element
    for (i = start + 1; i < end; i++)
     
    {
     //store the first unsorted value in the loop
     insertionValue = array[i];
     
     //initialize the position from which the second loop decrements
     j = i - 1;
     
     //loop through the sorted part, decrementing until an element is at least the insertionValue
     while ((j >= start) && (array[j] > insertionValue))
      
     {
      
      //shift the greater sorted elements to the right
      array[j + 1] = array[j];
      
      //decrement the loop position
      j--;
      
     }
     
     //the element at the last shift's previous position is made the insertionValue
     array[j + 1] = insertionValue;
     
    }
    
    
  }
  
  /**
   * Sort an integer array recursively, using merge sort.
   * 
   * @param  int[] array   the integer array to be sorted
   *                          the array integers are sorted in increasing order
   */
  public static void mergeSort(int[] array)
    
  {
    //declare a temporary array
    int[] temp;
    
    //initialize the temporary array
    temp = new int[array.length];
    
    //drive private mergeSort()
    mergeSort(array, 0, array.length, temp);
    
  }
  
  /**
   * Sort an integer array recursively, using merge sort.
   * 
   * @param  int[] array   the integer array to be sorted
   *                          the array integers are sorted in increasing order
   * @param  int start    the element position to start sorting from
   *                          
   * @param  int end    the element position before which the sort must stop
   *                          
   * @param  int[] temp    a temporary array that stores and transfers sorts 
   *                                                 
   */
  private static void mergeSort(int[] array, int start, int end, int[] temp)
    
  {
    //declare method variables
    int mid;
    
    //recursive case for 2 or more array elements, base case does nothing
    if (1 < end - start)
     
    {
     
     //instantiate the mid position in the array
     mid = start + ((end - start) / 2);
     
     //mergeSort each half of the array split from the mid position
     mergeSort(array, start, mid, temp);
     mergeSort(array, mid, end, temp);
     
     //merge the resulting sorted halves of the array
     merge(array, start, mid, end, temp);
     
    }
    
    
  }
  
  /**
   * Merge an array with two distinct sorted halves into one sorted array.
   * 
   * @param  int[] array   the integer array to be sorted
   *                          the array integers are sorted in increasing order
   * @param  int start    the element position to start sorting the left from
   *                          
   * @param  int mid    the element position before which the left sort must stop
   * 
   * @param  int end    the element position before which the right sort must stop
   *                         
   * @param  int[] temp    a temporary array that stores and transfers sorts 
   *                                                 
   */
  private static void merge(int[] array, int start, int mid, int end, int[] temp)
    
  {
   //declare variables for a merge algorithm
    int currentLeft;
    int currentRight;
    int currentTemp;
    
    //initialize the declared variables
    currentLeft = start;
    currentRight = mid;
    
    //loop through the temporary array, adding elements
    for (currentTemp = start; currentTemp < end; currentTemp++)
     
    {
     //add element to the temporary array if left element is less than right element
     if (currentLeft < mid && (currentRight >= end || array[currentLeft] < array[currentRight]))
      
     {
      //position left element into the temporary array
      temp[currentTemp] = array[currentLeft];
      
      //increment the left array counter
      currentLeft++;
      
     }
     
     else
      
     {
      //add right element to the temporary array 
      temp[currentTemp] = array[currentRight];
      
      //increment the right array counter
      currentRight++;
      
     }
     
    }
    
    //loop through the temporary array
    for (currentTemp = start; currentTemp < end; currentTemp++)
     
    {
     //copy each element in the temporary array into the original array
     array[currentTemp] = temp[currentTemp];
     
    }
    
    
  }
  
  /**
   * Sort an integer array recursively, using a hybrid merge sort with a breakpoint.
   * 
   * @param  int[] array   the integer array to be sorted
   *                          the array integers are sorted in increasing order
   * @param  int breakpoint   the element position after which to sort recursively
   *                                                                       
   */
  public static void hybridMergeSort(int[] array, int breakpoint)
    
  {
    //declare a temporary array
    int[] temp;
    
    //initialize
    temp = new int[array.length];
    
    //drive merge sort algorithm
    hybridMergeSort(array, 0, array.length, temp, breakpoint);
    
  }
  
  /**
   * Sort an integer array recursively, using merge sort.
   * 
   * @param  int[] array   the integer array to be sorted
   *                          the array integers are sorted in increasing order
   * @param  int start    the element position to start sorting from
   *                          
   * @param  int end    the element position before which the sort must stop
   *                          
   * @param  int[] temp    a temporary array that stores and transfers sorts 
   *    
   * @param  int breakpoint   the element position after which to sort recursively
   *                                              
   */
  private static void hybridMergeSort(int[] array, int start, int end, int[] temp, int breakpoint)
    
  {
    //declare method variables
    int mid;
    
    //recursive case for 2 or more array elements, base case does nothing
    if ((1 < end - start) && (breakpoint >= end - start))
      
    {
      //call the private insertionSort method
      insertionSort(array, start, end);
      
    }
    
    //when the array length is greater than breakpoint
    if ((1 < end - start) && (breakpoint < end - start))
     
    {
     
     //instantiate the mid position in the array
     mid = start + ((end - start) / 2);
     
     //mergeSort each half of the array split from the mid position
     hybridMergeSort(array, start, mid, temp, breakpoint);
     hybridMergeSort(array, mid, end, temp, breakpoint);
     
     //merge the resulting sorted halves of the array
     merge(array, start, mid, end, temp);
     
    }
    
    
  }
  
  /**
   * Sort an integer array iteratively, using merge sort.
   * 
   * @param  int[] array    get the integer array from the public driver method.
   *                        the integer array is sorted into ascending order.
   */
  public static void iterativeMergeSort(int[] array)
    
  {
    //declare a temporary array
    int[] temp;
    
    //instantiate the temporary array size to be same as the passed array
    temp = new int[array.length];
    
    //double the sublists size incrementally
    for (int i = 1; 2*i <= array.length; i *= 2)
     
    {
      
      //increment through i-divided sublists
      for (int j = 0; i*j < array.length; j++)
        
      {
        
        //merge each i-sized sublist iteratively as j increases with i
        merge(array, 0, j, array.length, temp);
        merge(array, 0, i*j, array.length, temp);
        
      }
     
    }
    
    
  }
  
  /**
   * Sort an integer array recursively, using quick sort.
   * 
   * @param  int[] array   the integer array to be sorted
   *                          the array integers are sorted in increasing order
   */
  public static void quickSort(int[] array)
    
  {
    
    //call helper method
    quickSort(array, 0, array.length);
    
  }
  
  /**
   * Sort an integer array recursively, using quick sort.
   * 
   * @param  int[] array   the integer array to be sorted
   *                       the array integers are sorted in increasing order
   * @param  int start     the element position to start sorting from
   *                          
   * @param  int end       the element position before which the sort must stop
   *                                                                   
   */
  private static void quickSort(int[] array, int start, int end)
    
  {
    //declare the pivot position
    int pivotPosition;
    
    //base case, when array length is equal to 2
    if (end - start == 2)
      
    {
      
      //when next element is less than the current
      if (array[start + 1] < array[start])
        
      {
        
        //swap the two
        swap(array, start + 1, start);
        
      }
      
    }
    
    //when array length is greater than 2  
    else if (end - start > 2)
      
    {
      
      //choose a pivot position and partition the array
      choosePivot(array, start, end);
      pivotPosition = partition(array, start, end);
      
      //recursively quick sort the array using this same method
      quickSort(array, start, pivotPosition);
      quickSort(array, pivotPosition + 1, end);
      
    }
    
  }
  
  /**
   * Swap elements in two positions in an array.
   * 
   * @param  int[] array             the integer array to be swapped
   *                                 array elements are swapped
   * @param  int previousPosition    the previous position
   *                          
   * @param  int currentPosition     the current position
   *                                                                  
   */
  private static void swap(int[] array, int previousPosition, int currentPosition)
    
  {
    //declare a temporary integer variable
    int temp;
    
    //store one position's element in the temporary variable
    temp = array[previousPosition];
    
    //swap the two elements
    array[previousPosition] = array[currentPosition];
    array[currentPosition] = temp;
    
  }
  
  /**
   * Choose a pivot in an array of integers using median-of-three.
   * 
   * @param  int[] array   the integer array to be sorted
   *                       array integers are swapped
   * @param  int start     the first of three element positions
   *                       could be swapped depending on situation
   * @param  int end       the last of three element positions
   *                       could be swapped depending on situation                                             
   */
  private static void choosePivot(int[] array, int start, int end)
    
  {
    //declare needed variables
    int mid;
    int temp;
    
    //instantiate the mid position
    mid = start + (end - start) / 2;
    
    //when mid is the median
    if ((array[start] < array[mid] && array[mid] <= array[end - 1]) || (array[end - 1] < array[mid] && array[mid] < array[start]))
      
    {
      //the element at the mid position is made the median
      temp = array[start];
      array[start] = array[mid];
      array[mid] = temp;
      
    }
    
    //when the end position is the median
    else if ((array[mid] < array[end - 1] && array[end - 1] < array[start]) || (array[start] < array[end - 1] && array[end - 1] < array[mid]))
      
    {
      
      //the element at the end position is made the median
      temp = array[start];
      array[start] = array[end - 1];
      array[end - 1] = temp;
      
    }
    
  }
  
  /**
   * Separate elements smaller than the pivot from elements larger than or equal to the pivot.
   * 
   * @param  int[] array   the integer array to be sorted
   *                       array integers are repositioned
   * @param  int start     the element position to start sorting from
   *                       
   * @param  int end       the element position before which the sort must stop
   *                                                                    
   */
  private static int partition(int[] array, int start, int end)
    
  {
    //declare variables
    int pivot;
    int bigStart;
    
    //initialize variables
    pivot = array[start];
    bigStart = start + 1;
    
    //loop from the second position from start to the end
    for (int current = start + 1; current < end; current++)
      
    {
      //when the pivot is greater than the current element
      if (array[current] < pivot)
        
      {
        
        //swap elements and increment
        swap(array, current, bigStart);
        bigStart++;
        
      }
      
    }
    
    //swap pivot with largest smaller element
    swap(array, start, bigStart - 1);
    
    //return the pivot
    return bigStart - 1;
    
  }
  
  /**
   * Sort an integer array recursively, using a hybrid quick sort with a breakpoint.
   * 
   * @param  int[] array      the integer array to be sorted
   *                          the array integers are sorted in increasing order
   * @param  int breakpoint   the element position after which to sort recursively
   *                                                                       
   */
  public static void hybridQuickSort(int[] array, int breakpoint)
    
  {
    
    //call the helper method
    hybridQuickSort(array, 0, array.length, breakpoint);
    
  }
  
  /**
   * Sort an integer array recursively, using a hybrid quick sort with a breakpoint.
   * 
   * @param  int[] array      the integer array to be sorted
   *                          the array integers are sorted in increasing order
   * @param  int start        the element position to start sorting from
   *                          
   * @param  int end          the element position before which the sort must stop
   *                          
   * @param  int breakpoint   the element position after which to sort recursively
   *                                              
   */
  private static void hybridQuickSort(int[] array, int start, int end, int breakpoint)
    
  {
    //declare the pivot position variable
    int pivotPosition;
    
    //base case
    if (end - start == 2)
      
    {
      //when next element is less than the current
      if (array[start + 1] < array[start])
        
      {
        
        //swap the two
        swap(array, start + 1, start);
        
      }
      
    }
    
    //when breakpoint is greater than or equal to the length of an array greater than 2 in length
    else if ((end - start > 2) && (end - start <= breakpoint))
      
    {
      //call the insertionSort method
      insertionSort(array, start, end);
      
    }
      
    //when array length is greater than breakpoint
    else if ((end - start > 2) && (end - start > breakpoint))
      
    {
      
      //choose a pivot position and partition the array
      choosePivot(array, start, end);
      pivotPosition = partition(array, start, end);
      
      //recursively sort the array using this same method
      hybridQuickSort(array, start, pivotPosition, breakpoint);
      hybridQuickSort(array, pivotPosition + 1, end, breakpoint);
      
    }
    
  }
  
  /**
   * Check whether an entire integer array is completely sorted in ascending order.
   * 
   * @param  int[] array      the integer array to be sorted
   *                          the array is sorted in ascending order                     
   * @return isSorted         whether the array is entirely sorted
   */
  public static boolean isSorted(int[] array)
    
  {
    //declare local variables
    boolean isSorted;
    
    //instantiate the boolean variable
    isSorted = isSorted(array, 0, array.length);
    
    //return the boolean instance
    return isSorted;
    
  }
  
  /**
   * Check whether an entire integer array is completely sorted in ascending order.
   * 
   * @param  int[] array      the integer array to be sorted
   *                          the array is sorted in ascending order
   * @param  int start        start position for sort completion consideration
   *      
   * @param  int end          end position for sort completion consideration
   *                          
   * @return isSorted         whether the array is entirely sorted
   */
  private static boolean isSorted(int[] array, int start, int end)
    
  {
    //declare local variables
    boolean isSorted;
    int count;
    
    //initialize boolean as true
    isSorted = true;
    
    //loop through the entire array
    for (count = start + 1; count < end; count++)
      
    {
      
      //compare element to the previous
      if (array[count - 1] > array[count])
        
      {
        
        //set boolean as false if previous is greater
        isSorted = false;
        
      }
      
    }
    
    //return the resulting boolean
    return isSorted;
    
  }
  
  /**
   * Print whether a sorting method sorted a complete array successfully or not.
   * 
   * @param  boolean isSorted     the truth value indicating whether the array is sorted or not
   *                          
   * @param  String method        the name of the sorting method used
   *      
   * @param  int arrayLength      the length of the array
   */
  private static void printAccuracy(boolean isSorted, String method, int arrayLength)
    
  {
    //when the array is successfully sorted
    if (isSorted == true)
      
    {
      
      //print success details
      System.out.println(method + " successfully sorted " + arrayLength + " elements.");
      
    }
    
    //when the array is not successfully sorted
    else if (isSorted == false)
      
    {
      
      //print failure details
      System.out.println(method + " unsuccessfully sorted " + arrayLength + " elements.");
      
    }
    
  }
  
  /**
   * Compute the best sorting algorithm based on the shortest running time.
   * The best time and global variables are changed every time a smaller value is entered.
   * @param  String sortName      the name of the sorting method
   *                              
   * @param  long sortTime        the running time of the sort method
   *      
   * @param  int arrayLength      the length of the array that was sorted
   */
  private static void computeBestTime(String sortName, long sortTime, int arrayLength)
   
  {
   
    //when the passed sort time is less than the current best time
   if (sortTime < bestSortTime)
    
   {
    
     //update best sort method information
     bestSortTime = sortTime;
     bestSortName = sortName;
     bestSortArrayLength = arrayLength;
    
   }
   
  }
  
}