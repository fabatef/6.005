
/**
 * Represents a search of the Twitter API.
 */
public class TweetSearch {
    
    // Warning: terrible ideas ahead!
    
    private String substring = ""; // really?
    private long lastId = 0;       // really?
    
    /** @return substring filter */
    public String getSubstring() {
        return substring;
    }
    
    /** Set the substring filter
     *  @param substring new substring filter, or "" for no filter */
    public void setSubstring(String substring) {
        this.substring = substring;
    }
    
    /** @return tweetId upper bound */
    public long getLastId() {
        return lastId;
    }
    
    /** Set the tweetId upper bound
     *  @param lastId new tweetId lower bound, or 0 for no lower bound */
    public void setLastId(long lastId) {
        this.lastId = lastId;
    }
}
