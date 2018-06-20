import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// Original painter
// Don't change me, change the versions below
class Painter {
    
    private Set<Brush> brushes;
    private Palette palette;
    
    public Painter(Set<Brush> brushes) {
        this.brushes = brushes;
        this.palette = new Palette();
    }
    public void mix() {
        // mixing
    }
    public void paint() {
        // painting
    }
}

class Brush {
    // ...
}

class Palette {    
    // ...
}

///////////////////////////////////////////////////////

// ImmutablePainter is immutable
class ImmutablePainter {
    // TODO: change some of the code in this class

    private final Set<Brush> brushes;
    private final Palette palette;
    
    // Thread safety argument:
    //    Both fields are final. The set of brushes is unmodifiable and contains
    //    thread safe Brushes

    public ImmutablePainter(Set<Brush> brushes) {
        this.brushes = Collections.unmodifiableSet(new HashSet<>(brushes));
        this.palette = new Palette();
    }
    public void mix() {
        // mixing
    }
    public void paint() {
        // painting
    }
}

// MonitorPainter implements the monitor pattern
class MonitorPainter {
    // TODO: change some of the code in this class
    
    private Set<Brush> brushes;
    private Palette palette;
    
    // Thread safety argument:
    //    both methods are synchronized with 'this' as a lock. A method on an instance
    //    of monitorpainter is run by a single thread until it finishes execution
    
    public  MonitorPainter(Set<Brush> brushes) {
        this.brushes = new HashSet<>(brushes);
        this.palette = new Palette();
    }
    public synchronized void mix() {
        // mixing
    }
    public synchronized void paint() {
        // painting
    }
}

// LockPainter protects all access to its rep using the lock on brushes
class LockPainter {
    // TODO
    
    private Set<Brush> brushes;
    private Palette palette;
    
    // Thread safety argument:
    //    all methods in a lockPainter are synchronized on the set of Brush objects, so while
    //    a thread is modifying this set, no other thread can have access to the lock?
    
    public LockPainter(Set<Brush> brushes) {
 
        this.brushes = new HashSet<>(brushes);
        this.palette = new Palette();
    }
    public void mix() {
    	synchronized(brushes) {
    		 // mixing
    	}
       
    }
    public void paint() {
    	synchronized(brushes){
    		 // painting
    	}  
    }
}

// NoLocksPainter knows that Brush and Palette are threadsafe, so doesn't use locks
class NoLocksPainter {
    // TODO: change some of the code in this class
    
    private final Set<Brush> brushes;
    private final Palette palette;
    
    // Thread safety argument:
    //  the set of Brush objects is synchronized. atomic operations on the set are
    //  performed by a single thread at a time.
    
    public NoLocksPainter(Set<Brush> brushes) {
        this.brushes = Collections.synchronizedSet(new HashSet<>(brushes));
        this.palette = new Palette();
    }
    public void mix() {
        // mixing
    }
    public void paint() {
        // painting
    }
}
