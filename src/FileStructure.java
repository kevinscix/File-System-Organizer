import java.util.ArrayList;
import java.util.Iterator;
/**
 * A file structure class that builds the file system and has functions to navigate it
 * @author Kevin Wu kwu347
 */
public class FileStructure {
	/**
	 * Instance variable for the class
	 */
	private NLNode<FileObject> root;
	/**
	 * Constructor for the FileStructure class.
	 * @param fileObjectName the name of the file object to represent as the root of the linked structure.
	 * @throws FileObjectException if the file object cannot be created.
	 */
	public FileStructure(String fileObjectName) throws FileObjectException {
		try {
			FileObject objectFile = new FileObject(fileObjectName);
			root = new NLNode<FileObject>(objectFile, null);
			buildSystem(root);
		} catch (FileObjectException e) {
			throw e;
		}
	}
	/**
	 * Recursive algorithm to build the file system.
	 * @param node the current node to build the structure from.
	 */
	private void buildSystem(NLNode<FileObject> root) {
		FileObject objectFile = root.getData();
		if (objectFile.isDirectory()) {
			Iterator<FileObject> itr = objectFile.directoryFiles();
			while (itr.hasNext()) {
				FileObject child = itr.next();
				NLNode<FileObject> childNode = new NLNode<FileObject>(child, root);
				root.addChild(childNode);
				buildSystem(childNode);
			}
		}
	}
	/**
	 * Returns the root node of the linked structure.
	 * @return the root node.
	 */
	public NLNode<FileObject> getRoot() {
		return root;
	}
	/**
	 * Returns an iterator with the names of all files in the file system that have the specified file type.
	 * @param type the file type to search for (e.g. ".java", ".docx").
	 * @return the iterator with the names of the found files.
	 */
	public Iterator<String> filesOfType (String type){
		ArrayList<String> fileNames = new ArrayList<>();
		findFilesOfType(this.root, type, fileNames);
		return fileNames.iterator();
	}

	/**
	 * Recursive algorithm to find all files in the file system that have the specified file type.
	 * @param node the current node to search from.
	 * @param type the file type to search for (e.g. ".java", ".docx").
	 * @param files the container to store the absolute paths of the found files.
	 */
	private void findFilesOfType(NLNode<FileObject> root2, String type, ArrayList<String> fileNames) {
		if (root2.getData().isDirectory()) {
			Iterator<NLNode<FileObject>> list = root2.getChildren();
			while(list.hasNext()) {
				NLNode<FileObject> temp = list.next();
				findFilesOfType(temp, type, fileNames);
			}
		} else {
			if (root2.getData().getName().endsWith(type)) {
				fileNames.add(root2.getData().getLongName());
			}
		}
	}
	/**
	 * Searches the linked structure for a file with the specified name and returns its absolute path if found.
	 * @param fileName the name of the file to search for.
	 * @return the absolute path of the file if found, or "" otherwise.
	 */
	public String findFile(String name) {
		return searchForFile(this.root, name);
	}
	/**
	 * Recursive algorithm to find a file with the specified name.
	 * @param node the current node to search from.
	 * @param fileName the name of the file to search for.
	 * @return the absolute path of the file if found, or "" otherwise.
	 */
	private String searchForFile(NLNode<FileObject> root2, String name) {
		if (root2.getData().getName().equals(name)) {
			return root2.getData().getLongName();
		}

		if (root2.getData().isDirectory()) {
			Iterator<NLNode<FileObject>> list = root2.getChildren();
			while(list.hasNext()) {
				NLNode<FileObject> temp = list.next();
				String filePath = searchForFile(temp, name);
				if (filePath != "") {
					return filePath;
				}
			}
		}
		return "";
	}
}