public interface PrintMethods {
    default void print(Object arg){
        System.out.print(arg);
    }
    default void println(Object arg){
        System.out.println(arg);
    }
}
