import java.util.Comparator;
import java.util.Iterator;
/**
 * A node class that represents the non-linear data structure that will be used to store information about the file system
 * @param <T> The type of the data stored in the node.
 * @author Kevin Wu kwu347
 */
public class NLNode<T> {
	/**
	 * Instance variables for the NLNode class
	 */
	private NLNode<T> parent;
	private ListNodes<NLNode<T>> children;
	private T data;
	/**
	 * Constructor for the NLNode class.
	 */
	public NLNode() {
		parent = null;
		data = null;
		children = new ListNodes<NLNode<T>>();
	}
	/**
	 * Constructor for the NLNode class.
	 * @param T d the data for the NLNode
	 * @param NLNode<T> p the parent of the NLNode
	 */
	public NLNode (T d, NLNode<T> p) {
		children = new ListNodes<NLNode<T>>();
		data = d;
		parent = p;
	}
	/**
	 * sets the parent of the NLNode
	 * @param p
	 */
	public void setParent(NLNode<T> p) {
		this.parent = p;
	}
	/**
	 * returns the parent
	 * @return the parent of the NLNode
	 */
	public NLNode<T> getParent(){
		return this.parent;
	}
	/**
	 * Given the newChild node it sets its parent and adds it to the list of nodes
	 * @param newChild node to add to list
	 */
	public void addChild(NLNode<T> newChild) {
		newChild.setParent(this);
		this.children.add(newChild);
	}
	/**
	 * @return the child Iterator 
	 */
	public Iterator<NLNode<T>> getChildren() {
		return children.getList();
	}
	/**
	 * takes in the comparator sorter to sort the list of NLNodes
	 * @param sorter used to sort the NLNode list
	 * @return a sorted Iterator of the list of Nodes
	 */
	public Iterator<NLNode<T>> getChildren(Comparator<NLNode<T>> sorter){
		children.sortedList(sorter);
		return children.getList();
	}
	/**
	 * @return the data of the NLNode
	 */
	public T getData() {
		return this.data;
	}
	/**
	 * @param sets the data of the NLNode
	 */
	public void setData(T d) {
		this.data = d;
	}
}
