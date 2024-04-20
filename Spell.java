public class Spell {
    private String name;
    private String category;
    private int powerLevel;
    private String words;

    public Spell(String name, String category, int powerLevel, String words) {
    	this.name = name;
    	this.category = category;
    	this.powerLevel = powerLevel;
    	this.words = words;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    @Override
    public String toString() {
        return name + " (" + category + ") - Power Level: " + powerLevel + ", to cast say: " + words;
    }
}
