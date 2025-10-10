
public class HomeWork {
    public static void main(String[] args) {

        // 1. Declare an int primitive variable and an Integer wrapper variable
        int primitiveInt = 10;
        Integer wrapperInteger;

        // 2. Assign the int value to the Integer variable (autoboxing)
        wrapperInteger = primitiveInt;
        // 3. Declare another Integer wrapper variable and assign it a value
        Integer anotherWrapper = 25;

        // 4. Assign the Integer value to an int variable (unboxing)
        int anotherPrimitive = anotherWrapper;

        // Print the results
        System.out.println("Primitive int: " + primitiveInt);
        System.out.println("Wrapper Integer (after autoboxing): " + wrapperInteger);
        System.out.println("Another Wrapper Integer: " + anotherWrapper);
        System.out.println("Another Primitive int (after unboxing): " + anotherPrimitive);
    }
}
