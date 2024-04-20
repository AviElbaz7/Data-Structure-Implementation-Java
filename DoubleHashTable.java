public class DoubleHashTable {
    private SpellSimple[] table;
    private int capacity;
    private int size;
    private int steps=0;
    
    /**
     * creating an array of SpellSimple objects in the size of capacity
     * @param capacity - the capacity of the 
     */
    public DoubleHashTable(int capacity) {
    	this.capacity = capacity;
    	this.table = new SpellSimple[capacity];
    	this.size = 0;
    }
    
    /**
     * 
     * @param spell - the spell we wish to insert to the table
     * @return a boolean that returns True if we succeed to insert or False if not.
     */
    public boolean put(SpellSimple spell) {
    	if(size == capacity) {
    		return false;
    	}
    	int first_hash = hash1(spell.getName());
    	int second_hash = hash2(spell.getName());
    	int i = 0;
    	int index_table = (first_hash + i*second_hash) % capacity;
    	
    	
    	// we will use linear double hashing in order to insert to the table
    	while(table[index_table] != null) {
    		i++;
    		index_table = (first_hash + i * second_hash) % capacity;
    	
    	if(i > capacity) {
    		return false;
    		}
    	}
    	table[index_table] = spell;
    	size++;
    	steps = i;
    	return true;
    }
    
    /**
     * 
     * @param name - the name of the spell
     * @return - returns the “words” to cast the spell
     */
    public String getCastWords(String name) {
    	int i = 0;
    	int index_name = (hash1(name) + i*hash2(name)) % capacity;
    	
    	while(table[index_name].getName() != name) {
    		i++;
    		index_name = (index_name + i*hash2(name)) % capacity;
    	if(i > capacity) {
    		return null;
    		}
    	}
    	steps = i;
    	
        return table[index_name].getWords();
    }

    public int getSize() {
        return size;
    }

    public int getLastSteps() {
    	return steps; 
    }
    
    /**
     * hash function by the requested function
     * @param name - the "key" to find the index in the table
     * @return - an int the represents the index in the table
     */
    private int hash1(String name) {
    	int hash = 0;
    	for(char ch: name.toCharArray()) {
    		hash = hash + ch * 31;
    	}
        return hash % this.capacity;
    }
    
    /**
     * hash function by the requested function
     * @param name - the "key" to find the index in the table
     * @return - an int the represents the index in the table
     */
    private int hash2(String name) {
    	int hash = 0;
    	for(char ch: name.toCharArray()) {
    		hash = hash + ch * 13;
    	}
        return (1 + hash % (this.capacity-2));
    }
}