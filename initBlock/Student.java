package initBlock;

class Student {
    String studentID; // Every student needs a unique ID
    String university;
    String major;

    // INITIALIZER BLOCK
    {
        university = "Goa University";
        // Generating a random ID automatically so we never forget!
        studentID = "GU-" + (int) (Math.random() * 10000);
        System.out.println("Common Setup: University assigned and ID [" + studentID + "] generated.");
    }

    public Student(String major) {
        this.major = major;
        System.out.println("Constructor 1: Student is in " + major);
    }

    public Student() {
        this.major = "Undeclared";
        System.out.println("Constructor 2: Student is Undeclared");
    }

    public static void main(String[] args) {
        Student s1 = new Student("MCA");
        System.out.println("Student Info: " + s1.studentID + " | " + s1.major);

        System.out.println("----------------");

        Student s2 = new Student();
        System.out.println("Student Info: " + s2.studentID + " | " + s2.major);
    }
}

/*
 * Why we use it? If you didn't have this block, you would have to manually
 * type university = "Goa University"; inside both constructors.
 * What if you had 10 different constructors? You'd have to type it 10 times.
 * If the university name changed to "GU", you'd have to find and change it in
 * 10 different places.
 * The Solution (With Initializer Block) You write the "Shared Stuff" once in
 * the
 * block. Java guarantees it will run before any constructor code starts.If you
 * didn't have this block, you would have to manually type university =
 * "Goa University"; inside both constructors.
 * What if you had 10 different constructors? You'd have to type it 10 times.
 * 
 * If the university name changed to "GU", you'd have to find and change it in
 * 10 different places.
 * 
 * The Solution (With Initializer Block) You write the "Shared Stuff" once in
 * the
 * block. Java guarantees it will run before any constructor code starts.
 * 
 * 3. Use Case: The "Tracking ID"
 * The best real-world use case is for Automatic IDs.
 * If every object in your program needs a unique ID or a timestamp when it is
 * born, you put that logic in the Initializer Block. This way, you never forget
 * to assign an ID, regardless of how the object was created.
 * 
 * 
 * 
 */