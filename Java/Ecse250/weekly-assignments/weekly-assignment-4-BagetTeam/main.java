import animals.mammals.Cat;
import animals.mammals.Dog;

public class main {
    public static void main(String[] args) {
        Cat defaultCat = new Cat();
        System.out.println("Default Cat: ");
        System.out.println("Name: " + defaultCat.getName());
        System.out.println("Color: " + defaultCat.getColor());
        System.out.println("Breed: " + defaultCat.getBreed());
        System.out.println("Sound: " + defaultCat.getSound());

        Dog defaultDog = new Dog();
        System.out.println("\nDefault Dog: ");
        System.out.println("Name: " + defaultDog.getName());
        System.out.println("Color: " + defaultDog.getColor());
        System.out.println("Breed: " + defaultDog.getBreed());
        System.out.println("Sound: " + defaultDog.getSound());

        // Case 2: Cat with all field values, mood = false
        Cat specificCat1 = new Cat(false, "Whiskers", "White", "Persian");
        System.out.println("\nSpecific Cat 1: ");
        System.out.println("Name: " + specificCat1.getName());
        System.out.println("Color: " + specificCat1.getColor());
        System.out.println("Breed: " + specificCat1.getBreed());
        System.out.println("Sound: " + specificCat1.getSound());
        specificCat1.meow();

        // Case 3: Cat with all field values, mood = true
        Cat specificCat2 = new Cat(true, "Shadow", "Black", "Siamese");
        System.out.println("\nSpecific Cat 2: ");
        System.out.println("Name: " + specificCat2.getName());
        System.out.println("Color: " + specificCat2.getColor());
        System.out.println("Breed: " + specificCat2.getBreed());
        System.out.println("Sound: " + specificCat2.getSound());
        specificCat2.meow();

        // Case 4: Dog with all field values, energy <= 5
        Dog specificDog1 = new Dog("Buddy", "Brown", "Labrador", 4);
        System.out.println("\nSpecific Dog 1: ");
        System.out.println("Name: " + specificDog1.getName());
        System.out.println("Color: " + specificDog1.getColor());
        System.out.println("Breed: " + specificDog1.getBreed());
        System.out.println("Sound: " + specificDog1.getSound());
        specificDog1.bark();

        // Case 5: Dog with all field values, energy > 5
        Dog specificDog2 = new Dog("Max", "Golden", "Retriever", 6);
        System.out.println("\nSpecific Dog 2: ");
        System.out.println("Name: " + specificDog2.getName());
        System.out.println("Color: " + specificDog2.getColor());
        System.out.println("Breed: " + specificDog2.getBreed());
        System.out.println("Sound: " + specificDog2.getSound());
        specificDog2.bark();

    }
}
