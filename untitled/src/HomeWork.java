
public class HomeWork {
    public static void main(String[] args) {
        // 1. Declare an int primitive variable and an Integer wrapper variable
        int primitiveInt = 10;
        Integer wrapperInt;

        // 2. Assign the int value to the Integer variable (autoboxing)
        wrapperInt = primitiveInt; 

        // 3. Declare another Integer wrapper variable and assign it a value
        Integer anotherWrapper = 25;

        // 4. Assign the Integer value to an int variable (unboxing)
        int anotherPrimitive = anotherWrapper;

        System.out.println("Primitive int value: " + primitiveInt);
        System.out.println("Wrapper Integer value (autoboxed): " + wrapperInt);
        System.out.println("Another Wrapper Integer value: " + anotherWrapper);
        System.out.println("Another Primitive int value (unboxed): " + anotherPrimitive);
    }
}

