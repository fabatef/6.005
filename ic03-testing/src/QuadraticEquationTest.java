import java.util.List;



public class QuadraticEquationTest {

    //
    // Testing strategy:
    //   
    // TODO: write your partitions here
	quadraticRoots(); // b^2< 4ac -no real roots
	quadraticRoots(); // b^2= 4ac  -one real root
	quadraticRoots(); //b^2>4ac  -two real roots
	
// or
	quadraticRoots(); // a>0, a<0 -->you can't do a=0 because it's disallowed in the specification
	quadraticRoots(); // b>0, b=0, b<0 
	quadraticRoots(); //c>0, c=0, c<0
// or
	quadraticRoots(); // a= very large, a=close to 0, a= moderate (same for b and c)

}
