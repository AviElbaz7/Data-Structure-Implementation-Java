/**
 * This is a testing framework. Use it extensively to verify that your code is working
 * properly.
 */
public class Tester {

    private static boolean testPassed = true;
    private static int testNum = 0;

    /**
     * This entry function will test all classes created in this assignment.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        // Each function here should test a different property. you should test here every class you created.
    	

        //Hash table
        testPart_1_hashtable();
        testAVLTree();
        testPart_2_hashtable();

        // Notifying the user that the code have passed all tests.
        if (testPassed) {
            System.out.println("All " + testNum + " tests passed!");
        }
    }

    /**
     * This utility function will count the number of times it was invoked.
     * In addition, if a test fails the function will print the error message.
     * @param exp The actual test condition
     * @param msg An error message, will be printed to the screen in case the test fails.
     */
    private static void test(boolean exp, String msg) {
        testNum++;

        if (!exp) {
            testPassed = false;
            System.out.println("Test " + testNum + " failed: "  + msg);
        }
    }


    /**
     * Checks the first part of the work, about double hashing in linear prob.
     */
    private static void testPart_1_hashtable() {

    	DoubleHashTable table = new DoubleHashTable(15);
        // Add some spells to the table
        table.put(new SpellSimple("expeliamos", "blablabla"));
        table.put(new SpellSimple("Expecto universe", "where is voldomert?"));
        
        // Get the size of the table
        int size = table.getSize();

        test(table.getCastWords("expeliamos") == "blablabla" , "The words for expeliamos should be blablabla");
        test(table.getSize() == 2 , "The size of the table should be 2");
        
        // Add some spells to the table
        table.put(new SpellSimple("new era", "de vinchy"));
        test(table.getCastWords("new era") == "de vinchy" , "The words for new era should be de vinchy");
        
        DoubleHashTable table2 = new DoubleHashTable(10);
        // Add some spells to the table
        table2.put(new SpellSimple("Abra", "Avada"));
        table2.put(new SpellSimple("Expect", "sike"));
        table2.put(new SpellSimple("Wi Levioa", "stand up"));
        table2.put(new SpellSimple("Shaz", "in the air"));
        
        test(table2.getCastWords("Abra") == "Avada" , "The words for Abra should be Avada");
        test(table2.getCastWords("Expect") == "sike" , "The words for Expect should be sike");
        test(table2.getCastWords("Wi Levioa") == "stand up" , "The words for Wi Levioa should be stand up");

        test(table2.getSize() == 4 , "The size of the table should be 4");
        
        
    }
    
    
    private static void testAVLTree() {
    	// Adding spells
    	Spell spell_1 = new Spell("fiebal", "fire", 20, "firl!");
		AVLTree Tree = new AVLTree(spell_1);
        Tree.insert(new Spell("light it", "fire", 9, "light up")); 
        Tree.insert(new Spell("wishing", "fire", 17, "littel ball"));
        Tree.insert(new Spell("burnod", "ice", 5, "make things cold"));
        Tree.insert(new Spell("wawawa", "fire", 72, "big ball!"));
        

        test(Tree.getSize() == 5, "Tree size should be '5', got: " + Tree.getSize());
        test(Tree.getTreeHeight() == 2, "Tree height should be '2', got: " + Tree.getTreeHeight());
        test(Tree.getCategory().equals("fire"), "Category should be 'fire', got: " + Tree.getCategory());
        test(Tree.getTopK(2).toString().equals("[wawawa (fire) - Power Level: 72, to cast say: big ball!, fiebal (fire) - Power Level: 20, to cast say: firl!]"), "The getTopK function should return '[fiebal (fire) - Power Level: 20, to cast say: firl!, wawawa (fire) - Power Level: 72, to cast say: big ball!]', got: " + Tree.getTopK(2));
        test(Tree.getTopK(3).toString().equals("[wawawa (fire) - Power Level: 72, to cast say: big ball!, fiebal (fire) - Power Level: 20, to cast say: firl!, wishing (fire) - Power Level: 17, to cast say: littel ball]"), "The getTopK function should return '[wawawa (fire) - Power Level: 72, to cast say: big ball!, fiebal (fire) - Power Level: 20, to cast say: firl!, wishing (fire) - Power Level: 17, to cast say: littel ball]' got: " + Tree.getTopK(3));
        test(Tree.search("wishing", 17).toString().equals("wishing (fire) - Power Level: 17, to cast say: littel ball"), "The search function should return the spell 'wishing (fire) - Power Level: 17, to cast say: littel ball', got: " + Tree.search("wish", 9));
        test(Tree.search("burn", 90) == null, "The spell doensn't exist in the tree, should return 'null', got: " + Tree.search("burnod", 90));
        
        // Adding more spells
        Tree.insert(new Spell("Allm", "fire", 19, "ShaBang!!"));
        Tree.insert(new Spell("Delen", "fire", 26, "Plan iver"));
        Tree.insert(new Spell("spector", "fire", 98, "a lot of fire balls"));
        
        test(Tree.getSize() == 8, "Tree size should be '8', got: " + Tree.getSize());
        test(Tree.getTreeHeight() == 3, "Tree height should be '3', got: " + Tree.getTreeHeight());
        test(Tree.getTopK(4).toString().equals("[spector (fire) - Power Level: 98, to cast say: a lot of fire balls, wawawa (fire) - Power Level: 72, to cast say: big ball!, Delen (fire) - Power Level: 26, to cast say: Plan iver, fiebal (fire) - Power Level: 20, to cast say: firl!]"), "The getTopK function should return '[spector (fire) - Power Level: 98, to cast say: a lot of fire balls, wawawa (fire) - Power Level: 72, to cast say: big ball!, Delen (fire) - Power Level: 26, to cast say: Plan iver, fiebal (fire) - Power Level: 20, to cast say: firl!]', got: " + Tree.getTopK(4));
        test(Tree.getTopK(3).toString().equals("[spector (fire) - Power Level: 98, to cast say: a lot of fire balls, wawawa (fire) - Power Level: 72, to cast say: big ball!, Delen (fire) - Power Level: 26, to cast say: Plan iver]"), "The getTopK function should return '[spector (fire) - Power Level: 98, to cast say: a lot of fire balls, wawawa (fire) - Power Level: 72, to cast say: big ball!, Delen (fire) - Power Level: 26, to cast say: Plan iver]', got: " + Tree.getTopK(3));
        

    }
    
    
    /**
     * Checks the second part of the work, about double hashing with linked list.
     */
    private static void testPart_2_hashtable() {

        // create a new hash AVL spell table
        HashAVLSpellTable table = new HashAVLSpellTable(14);
        
        // creating some spells
        Spell spell1 = new Spell("firerocket", "fire", 71, "fire fire!");
        Spell spell2 = new Spell("frosting", "ice", 18, "freeze weeze");
        Spell spell3 = new Spell("thunderbolt", "lightning", 52, "I`m going");
        Spell spell4 = new Spell("poison fast", "poison", 44, "klakla");
        Spell spell5 = new Spell("shock freeze", "ice", 45, "go go go!");
        
        table.addSpell(spell1);
        table.addSpell(spell2);
        table.addSpell(spell3);
        table.addSpell(spell4);
        table.addSpell(spell5);
        
        Spell searchedSpell = table.searchSpell("fire", "firerocket", 71);
        test(table.searchSpell("fire", "firerocket", 71) == searchedSpell, "The Spell should be Spell(firerocket, fire, 71, fire fire)");
        test(table.getNumberSpells() == 5 , "number of Spells should be 5");
        
        table.addSpell(new Spell("light", "lightning", 11, "light bolt"));
        test(table.getNumberSpells() == 6 , "number of Spells should be 6");
        
        // add more spells to an existing category
        table.addSpell(new Spell("flamethrower min", "fire", 6, "foo"));
        table.addSpell(new Spell("flamethrower", "fire", 8, "foo better"));
        table.addSpell(new Spell("fireball II", "fire", 12,"fireball!!"));
        table.addSpell(new Spell("flamethrower II", "fire", 15, "foooooooo!"));
        table.addSpell(new Spell("shockwave II", "lightning", 10,"be useful pikachu."));
        table.addSpell(new Spell("frost nova", "ice", 4, "chill dude"));
        
        test(table.getNumberSpells("fire") == 5 , "number of Spells should be 5");
        test(table.getNumberSpells("ice") == 3 , "number of Spells should be 3");
        test(table.getNumberSpells("lightning") == 3 , "number of Spells should be 3");
        
        
    }
 

}
