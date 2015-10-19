package guzdial;

/**
 * Demonstrate search algorithms
 **/
public class SearchMethods {
  
  /**
   * Implement a linear search through the list
   **/
  static public String lfind(String target, String[] list){
    for (int index=0; index < list.length; index++) {
      if (target.compareTo(list[index]) == 0)
      {return("Found it!"); }
    }
    return("Not found");
  }
  
  /** 
   * Implement a simple binary search through the list
   **/
  static public String find(String target, String[] list){
    int start = 0;
    int end = list.length - 1;
    int checkpoint = 0;
    
    while (start <= end) { //While there are more to search
      // find the middle
      checkpoint = (int)((start+end)/2.0);
      System.out.println("Checking at: "+checkpoint+" start="+start+" end="+end);
      if ((checkpoint < 0) || (checkpoint > list.length)){
        break;}
      if (target.compareTo(list[checkpoint]) == 0) {
        return "Found it!";}
      if (target.compareTo(list[checkpoint]) > 0) {
        start=checkpoint + 1;}
      if (target.compareTo(list[checkpoint]) < 0) {
        end=checkpoint - 1;}
    }
    return "Not found";
  }
  
  static public void main(String[] args){
    String[] searchMe = {"apple","bear","cat","dog","elephant"};
    
    System.out.println(find("apple",searchMe));
    System.out.println(find("cat",searchMe));
    System.out.println(find("giraffe",searchMe));
  }
  
}
