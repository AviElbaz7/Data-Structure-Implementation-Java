import java.util.LinkedList;
import java.util.List;

public class HashAVLSpellTable {
    private LinkedList<AVLTree> buckets[];
    private int tableSize;
    private int numSpells;
    
    /**
     * creates a new instance of the class with the specified size,
     * inserts a linked list to each bucket in the table
     * @param size - the size of the hash table (number of buckets)
     */
    public HashAVLSpellTable(int size) {
    	this.tableSize = size;
    	this.buckets = new LinkedList[tableSize];
    	this.numSpells = 0;
    	for(int i=0; i<tableSize; i++) {
    		buckets[i] = new LinkedList<AVLTree> ();
    		}
        }
    
    /**
     * hash function by the requested function
     * @param category - the "key" to find the index in the table
     * @return - an int the represents the index in the table
     */
    private int hash(String category) {
    	int hash1 = 0;
    	for(char ch: category.toCharArray()) {
    		hash1 = hash1 + ch;
    	}
        return (hash1 % tableSize);
    }

    /**
     * the function add's a spell to the table in the correct index and inserts it to the tree in the linked list
     * @param s - the spell we want to insert
     */
    public void addSpell(Spell s) {
    	int hash1 = hash(s.getCategory());
    	if(buckets[hash1] == null) {
    		AVLTree avltree = new AVLTree(s);
    		buckets[hash1].add(avltree);
    		numSpells++;
    		return;
    	}
    	else {
    		for(AVLTree tree: buckets[hash1]) {
    			if(tree.getCategory().equals(s.getCategory())) {
    				tree.insert(s);
    				numSpells++;
    				return;
    			}
    		}
    		AVLTree avltree = new AVLTree(s);
    		buckets[hash1].addFirst(avltree);
    	}
    	numSpells++;
    	
    }

    /**
     * search for a spell by category, spell name , and powerLevel
     * @param category
     * @param spellName
     * @param powerLevel
     * @return - the spell we found
     */
    public Spell searchSpell(String category, String spellName, int powerLevel) {
    	int hash1 = hash(category);
    	if(buckets[hash1] == null) {
    		return null;
    	}
    	for(AVLTree tree: buckets[hash1]) {
    		if(tree.getCategory().equals(category)) {
    			return tree.search(spellName, powerLevel);
    		}
    	}
        return null;
    }

    public int getNumberSpells(){
        return numSpells;
    }
    
    /**
     * getter for the number of spells that exist for the category
     * @param category
     * @return - number of spells that exist for the category
     */
    public int getNumberSpells(String category){
    	int hash1 = hash(category);
    	if(buckets[hash1] == null) {
    		return 0;
    	}
    	for(AVLTree tree: buckets[hash1]) {
    		if(tree.getCategory().equals(category)) {
    			return tree.getSize();
    		}
    	}
        return 0;
    }
    
    /**
     * create a list that holds the k top spells (based on power level) in the tree
     * @param category
     * @param k
     * @return - the top k spells (based on power level) for the input category
     */
    public List<Spell> getTopK(String category, int k) {
    	int hash1 = hash(category);
    	if(buckets[hash1] == null) {
    		return null;
    	}
    	for(AVLTree tree: buckets[hash1]) {
    		if(tree.getCategory().equals(category)) {
    			return tree.getTopK(k);
    		}
    	}
    	return null;
    }
}
