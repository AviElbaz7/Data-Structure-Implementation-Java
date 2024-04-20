public class SpellSimple {
    private String name;
    private String words;

    SpellSimple(String name, String words) {
    	this.name = name;
    	this.words = words;
    }

    public String getName(){
        return name;
    }

    public String getWords(){
        return words;
    }
}