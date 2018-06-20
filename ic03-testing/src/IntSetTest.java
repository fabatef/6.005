
import static org.junit.Assert.*;

import org.junit.Test;


public class IntSetTest {
    //
    // Testing strategy:
    //
    // Partition for this.intersect(that) -> result:
    //
    //   this.size: 0, 1, >1
    //   that.size: 0, 1, >1
    //   result.size: 0, 1, >1
    //   this = that, this subset-of that, this superset-of that, none of the above
    //
    
    // This test covers this.size=1, that.size=1, result.size=0
    @Test
    public void resultIsEmptySet() {
        // this={5}, that={8}, result={}
    }
   
    @Test  //covers this.size=0, that.size=0 , result.size= 0
    
    public void testOne(){
    	// this={}, that= {}, result={}
    }
    
    @Test //covers this.size>1, that.size>0, result.size=0
    
    public void testTwo(){
    	// this={1,2}, that={2,3}, result= {2}
    }
    	
    @Test // covers result.size>1, that is a subset of this
    
    public void testThree(){
    	// this={5,1,3}, that={5,1}, result={5,1}
    }
    
    @Test // covers that is a superset of this
    
    public void testFor(){
    	//this= {5,1}, that= {5,1,3}, result= {5,1}
    }
    
  
    	
    
    
    // TODO: add test cases to cover the rest of the partitions, following the example above:
    //    - each test case should be an @Test method
    //    - comment before method says which partitions the test case covers
    //    - comment inside method is the test case, with actual values for this, that, result
    // You don't need to do a full Cartesian product, just cover every part.
}
