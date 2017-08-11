/**
 * TreeNode
 */
public class TreeNode {

    private Object datum;
    private TreeNode child, sibling;
    private String path;

    /**
     * Constructor
     */
    public TreeNode(Object datum, String pathname) {
        this.datum = datum;
        child = null;
        sibling = null;
        path = pathname;
    }
    
    public void setchild(TreeNode pointer){
        this.child=pointer;
    }
    
    public void setsibling(TreeNode pointer){
        this.sibling=pointer;
    }
    
    public TreeNode getchild(){
        return child;
    }
    
    public TreeNode getsibling(){
        return sibling;
    }
    
    public Object getDatum(){
        return datum;
    }
    
    public String getPath(){
        return path;
    }
}
