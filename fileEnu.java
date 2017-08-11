import java.io.*;
public class fileEnu{
    
    // The root of the Binary Tree, which contains the hierarchy of the given directory
    TreeNode root;
    
    
    // This method builds the binary tree of directory "rootPath"
    public void buildBT(String rootPath){
    
        // list all the files under the sepecified directory
        File[] files = new File(rootPath).listFiles();
        // create a TreeNode for specified directory root
        root = new TreeNode(rootPath, rootPath);
        // search all the files and directories under specified directory, build the rest of binary tree
        dispFiles(files, root);
    }
    
    
    // visit the binary tree by layers
    // For example, if we want to encrypt directory A. Under A, we have B, C, and D.
    // Under C, we have E and F
    // If the binary tree of this hierachy is properly built and the root of this binary tree is refereed by instance variable "root"
    // This method will visit the files in order of A, B, C, D, E, F
    // In other words, it visits the root, then the children of the root, then the lower level of root's children
    // Please use QueueLList<TreeNode> to save the direcories to be processed
    // Before implmenting this method, you need to implement QueueLList class first
    public void traverseBT(){
        QueueLList<TreeNode> queue = new QueueLList<TreeNode>();
		queue.enqueue(root);
		System.out.println(root.getPath());
        
		while(!queue.isEmpty()){
			TreeNode curr = queue.dequeue();
			TreeNode currChild = curr.getchild();
			while(currChild != null){
				System.out.println(currChild.getPath());
				
				if(currChild.getchild() != null){
					queue.enqueue(currChild);
				}
				currChild = currChild.getsibling();
			}
		}
    }
    
    // this method either encrypt or decrypt the directory
    // it will use the binary tree rooted at instance variable "root"
    // String key is the keyword for encryption/decryption
    // key must be at least 8 letters
    // String mode must be either "encode" or "decode"
    // This method uses the same way of traverseBT() to visit each file/directory
    // In the example we show above, the files/directories will be visited by order of A, B, C, D, E, F
    // When a TreeNode object is visited, you can use getPath() to get the filename along with path
    // For example, if variable temp points to TreeNode of E, temp.getPath() will return A/C/E as the result
    // Then, we can use encodeDecode.DESmain(key, temp.getPath(), mode); to encrypt or decrypt the file
    // DESmain() is already implemented. Please do not modify it
    public void EnDe(String key, String mode){
		QueueLList<TreeNode> queue = new QueueLList<TreeNode>();
		TreeNode curr = root;
		int i = 1;
		
		while(i > 0){
			while(curr != null){
				if(curr.getchild() != null)
					queue.enqueue(curr.getchild());
				else
					encodeDecode.DESmain(key, curr.getPath(), mode);
				
				curr = curr.getsibling();
			}
			if(queue.isEmpty())
				break;
			else
				curr = queue.dequeue();
		}
    }

    // check the OS is Windows or not
    // return true if the current OS is Windows
    // otherwise, return false
    public boolean isWindows(){
        String OS = System.getProperty("os.name").toLowerCase();
        
        if(OS.indexOf("win")>=0)return true;
        else
            return false;
    
    }
    
    public static void main(String[] args) {
        

        fileEnu example = new fileEnu();
        // build the binary tree listing all the files and directories under args[0]
        System.out.println("building the tree...");
        example.buildBT(args[0]);
        
        // traverse the binary tree we just built
        System.out.println("traversing the tree...");
        example.traverseBT();
        
        // use args[1] as password, args[2] is either "encode" or "decode"
        System.out.println("working on each file...");
        example.EnDe(args[1], args[2]);

    }

    // list the files listed in the array "files"
    // if files[i].isDirectory() is true, recursively call dispFiles() to list the files under files[i]
    // When traversing the files and directories listed in  files[], build the binary tree rooted at node
    // Whenever find a file or directory, creat a TreeNode object,
    // which contains the name of the file or directory, and the name with path
    // For example, if we want to encrypt directory A. Under A, we have B, C, and D.
    // Under C, we have E and F
    // The TreeNode of A includes ("A", "A")
    // The TreeNode of B includes ("B", "A/B")
    // The TreeNode of C includes ("C", "A/C")
    // The TreeNode of D includes ("D", "A/D")
    // The TreeNode of E includes ("E", "A/C/E")
    // The TreeNode of F includes ("E", "A/C/F")
    public void dispFiles(File[] files, TreeNode node) {
		TreeNode last = node;
		
		for(int i = 0; i < files.length; i++){
			TreeNode temp = new TreeNode(files[i], node.getPath() + "/" + files[i].getName());
			
			if(node.getchild() == null){
				node.setchild(temp);
			} else {
				last.setsibling(temp);
			}
			last = temp;
			
			if(files[i].isDirectory() == true){
				dispFiles(files[i].listFiles(), temp);
			}
		}
    }
 
    
}