import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JWindow;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MVC extends JFrame {
    
    // required because JFrame is a serializable class
    private static final long serialVersionUID = 1;
    
    
    //something done to list size +for huge data??
    public MVC() {
        super("MVC"); // sets the window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //implicit controller
        setLayout(new BorderLayout());
        
        // make a listbox that displays integers
        JList<Integer> list = new JList<Integer>();
       // this.add(list); //add the JList into the JFrame //commented out because of the this.add(scroll) hierarchy 
        
        // use the built-in Swing list model
        
/*        DefaultListModel<Integer> model = new DefaultListModel<Integer>();
        for (int i = 1; i <= 20; ++i) {
            model.addElement(i);
        }
        list.setModel(model);  //instructs JList to display the contents of the model 
*/        
        // TODO #1: make the JList scrollable
        JScrollPane scroll= new JScrollPane(list);
        this.add(scroll);
        
        
        // TODO #2: make this custom list model work
         SomeNegativeModel model = new SomeNegativeModel();
         list.setModel(model);
        
        // TODO #3: make negation work
         model.negate(3);
        
        // TODO #4: negate every number that the user selects in the listbox
         list.addListSelectionListener(new ListSelectionListener() {
        	 public void valueChanged(ListSelectionEvent lse){
        		 int[] selected= list.getSelectedIndices(); //get all selected indices 
        		 for (Integer index: selected){
        			 model.negate(index + 1); //call negate on them
        		 }
        	 }
         });
        
        // TODO #5: reset all the numbers back to positive values when the user presses Escape
         list.addKeyListener(new KeyAdapter() {
        	 public void keyPressed(KeyEvent ke){
        		 if(ke.getKeyCode() == KeyEvent.VK_ESCAPE){
        			 model.reset();
        		 }
        	 }
         });
        
        pack();
        setVisible(true);
    }
    
    private static int LIST_SIZE = 1000 * 1000;
    
    // SomeNegativeModel is a list model representing the positive integers from 1 to LIST_SIZE
    // in order, but with a small number of integers replaced by their negations.
    class SomeNegativeModel extends AbstractListModel<Integer> {
        
        // required because AbstractListModel is a serializable class
        private static final long serialVersionUID = 1;
        private final Set<Integer> negated = new HashSet<>();
        @Override public int getSize() {
            return LIST_SIZE;
        }
        
        @Override public Integer getElementAt(int index) {
        	int value= index+1;
        	return negated.contains(value) ? -value: value;  //if the set contains the value display its negated version
        }
        
        /**
         * Modify this list model so that it displays value negated.
         * @param value integer to negate, requires 1 <= value <= getSize()
         */
        public void negate(int value) {
        	negated.add(value);  //add the value to be negated into the hashset negated
        	this.fireContentsChanged(this, value-1, value-1);
        }
        
        /**
         * Modify this list model so that all values are positive again.
         */
        public void reset() {
            negated.clear();
            this.fireContentsChanged(this, 0, getSize()-1);  //update the model
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MVC();
            }
        });
    }
}
