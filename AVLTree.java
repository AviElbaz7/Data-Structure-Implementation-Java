import java.util.ArrayList;
import java.util.List;

public class AVLTree {

    private Node root;
    private int size;
    private String category;
	
	// private Node class for the AVL Tree nodes
    private class Node { 
        private Spell spell;
        private Node left;
        private Node right;
        private int height;

        private Node(Spell spell) {
        	this.spell = spell;
        	this.left = null;
        	this.right = null;
        	this.height = 0;
        }
    }

    // Constructor, getters, setters
    public AVLTree(Spell spell) {
    	this.root = new Node(spell);
    	this.size = 1;
    	this.category = spell.getCategory();
    }

    public int getTreeHeight(){
        return this.root.height;
    }

    public int getSize(){
        return size;
    }
    

    public String getCategory() {
        return category;
    }
    
    /**
     * 
     * @param spellName 
     * @param powerLevel
     * @return the spell of the node that we found
     */
    public Spell search(String spellName, int powerLevel) {
    	return searchingHelper(root, spellName, powerLevel);
    	
    }
    
    /**
     * 
     * @param node - the node we start the search 
     * @param spellName - spell name
     * @param powerLevel - the key in the tree that we look for to travel
     * @return - the spell of the node that we found
     */
    private Spell searchingHelper(Node node, String spellName, int powerLevel) {
    	if(node == null) {
    		return null;
    	}
    	
    	if(node.spell.getName() == spellName && node.spell.getPowerLevel() == powerLevel) {
    		return node.spell;
    	}
    	if(node.spell.getPowerLevel() > powerLevel) {
    		return searchingHelper(node.left, spellName, powerLevel);
    	}
    	return searchingHelper(node.right, spellName, powerLevel);

    	
	}
    
    /**
     * this function inserts a spell to the avltree
     * @param spell
     */
	public void insert(Spell spell) {
		root = helperInserting(root, spell);
		size++;
    }
	
	/**
	 * this function inserts a spell to the avltree implementing the trees properties in recursion
	 * @param node
	 * @param spell
	 * @return the node we inserted
	 */
	public Node helperInserting(Node node, Spell spell) {
		if(node == null) {
			return new Node(spell);
		}
		if(node.spell.getPowerLevel() < spell.getPowerLevel()) {
			node.right = helperInserting(node.right, spell);
		}
		else if(node.spell.getPowerLevel() > spell.getPowerLevel()) {
			node.left = helperInserting(node.left, spell);
		}
		
	    // updating the height of the current node
	    if (node.left == null && node.right == null) {
	        node.height = 0;
	    } else if (node.left == null) {
	        node.height = node.right.height + 1;
	    } else if (node.right == null) {
	        node.height = node.left.height + 1;
	    } else {
	        node.height = Math.max(node.left.height, node.right.height) + 1;
	    }
		int balance = get_balance_of_node(node);
		
		// we will do rotations if the node becomes unbalanced
		if(balance < -1 && spell.getPowerLevel() > node.right.spell.getPowerLevel()) {
			return RotateLeft(node);
		}
		if(balance < -1 && spell.getPowerLevel() < node.right.spell.getPowerLevel()) {
			node.right = RotateRight(node.right);
			return RotateLeft(node);
		}
		if(balance > 1 && spell.getPowerLevel() < node.left.spell.getPowerLevel()) {
			return RotateRight(node);
		}
		if(balance > 1 && spell.getPowerLevel() > node.left.spell.getPowerLevel()) {
			node.left = RotateLeft(node.left);
			return RotateRight(node);
		}
		
		return node;
	}
	
	/**
	 * 
	 * @param node - the node we wish to get the height for
	 * @return - the height of the node
	 */
	public int get_balance_of_node(Node node) {
	    int leftHeight = -1;
	    if (node.left != null) {
	        leftHeight = node.left.height;
	    }
	    
	    int rightHeight = -1;
	    if (node.right != null) {
	        rightHeight = node.right.height;
	    }
	    
	    return leftHeight - rightHeight;
	}
	
	/**
	 * implement a rotation left on the node in order to keep the properties of the tree 
	 * @param node - current node we want to implement the rotation on
	 * @return - a node after the rotation and updating the heights
	 */
	public Node RotateLeft(Node node) {
		Node y = node.right;
		node.right = y.left;
		y.left = node;
		
	    // Update the height of node and y
	    if (node.left == null && node.right == null) {
	        node.height = 0;
	    } else if (node.left == null) {
	        node.height = node.right.height + 1;
	    } else if (node.right == null) {
	        node.height = node.left.height + 1;
	    } else {
	        node.height = Math.max(node.left.height, node.right.height) + 1;
	    }
	    if (y.right == null) {
	        y.height = node.height + 1;
	    } else {
	        y.height = Math.max(node.height, y.right.height) + 1;
	    }
		
		return y;
	}
	
	/**
	 * implement a rotation right on the node in order to keep the properties of the tree 
	 * @param node - current node we want to implement the rotation on
	 * @return - a node after the rotation and updating the heights
	 */
	public Node RotateRight(Node node) {
		Node y = node.left;
		node.left = y.right;
		y.right = node;
		
	    // Update the height of node and y
	    if (node.left == null && node.right == null) {
	        node.height = 0;
	    } else if (node.left == null) {
	        node.height = node.right.height + 1;
	    } else if (node.right == null) {
	        node.height = node.left.height + 1;
	    } else {
	        node.height = Math.max(node.left.height, node.right.height) + 1;
	    }

	    if (y.left == null) {
	        y.height = node.height + 1;
	    } else {
	        y.height = Math.max(node.height, y.left.height) + 1;
	    }
		
		return y;
	}
	
	/**
	 * get the top k spells of the requested tree
	 * @param k - number of spells by descending order
	 * @return - a list that holds the top k spells in order
	 */
    public List<Spell> getTopK(int k) {
    	List<Spell> spells_in_order = new ArrayList();
    	if(k == 0) {
    		return null;
    	}
    	top_k_helper(root, k, spells_in_order);
        return spells_in_order;
    }
    
    /**
     * helper function to get the top k spells
     * @param node - the current node 
     * @param k - number of spells by descending order
     * @param spells_in_order - the list we return
     * @return - a list that holds the top k spells we wish for
     */
	public List<Spell> top_k_helper(Node node, int k, List<Spell> spells_in_order) {
		if(node == null || spells_in_order.size() >= k) {
			return null;
		}
		top_k_helper(node.right, k, spells_in_order);
		if(spells_in_order.size() < k) {
			spells_in_order.add(node.spell);
		}
		else {
			return null;
		}
		if(spells_in_order.size() < k) {
			top_k_helper(node.left, k, spells_in_order);
		}
		return spells_in_order;
	}
}


