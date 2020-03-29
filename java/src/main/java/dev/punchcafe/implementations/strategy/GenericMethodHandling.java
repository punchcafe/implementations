package dev.punchcafe.implementations.strategy;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * This class is an example on setting and getting attributes outside of a strategy class,
 * when the operation can be performed generically by a client. The following example demonstrates
 * A program which uses strategy provided method refferences to increment two unrelated attributes
 * generically.
 *
 * tl:dr : When we want to do a generic operation, like set the attribute of various objects of
 * different classes, the strategy pattern can be used to provide those setters, so that the client
 * can apply the change to each.
 */
public class GenericMethodHandling {

    public static SomeClassStrategy SOME_CLASS_STRATEGY = new SomeClassStrategy();
    public static SomeOtherClassStrategy SOME_OTHER_CLASS_STRATEGY = new SomeOtherClassStrategy();
    public static Map<Class<?>, Strategy> STRATEGY_MAP = Map.of(
            SomeClass.class, SOME_CLASS_STRATEGY,
            SomeOtherClass.class, SOME_OTHER_CLASS_STRATEGY
            );

    public static void main(String[] args) {

        SomeClass someClass = new SomeClass();
        SomeOtherClass someOtherClass = new SomeOtherClass();
        List<Object> objects = List.of(someClass, someOtherClass);

        someClass.setSomeNumber(3);
        someOtherClass.setSomeDifferentNumber(4);

        System.out.println(String.format("Initial value of first object: %d", someClass.getSomeNumber()));
        System.out.println(String.format("Initial value of second object: %d", someOtherClass.getSomeDifferentNumber()));
        System.out.println("*****");

        for(Object object : objects ){
            Strategy strategy = STRATEGY_MAP.get(object.getClass());
            Supplier<Integer> getter = strategy.getGetter(object);
            Consumer<Integer> setter = strategy.getSetter(object);
            setter.accept(getter.get() + 1);
        }

        System.out.println(String.format("Updated value of first object: %d", someClass.getSomeNumber()));
        System.out.println(String.format("Updated value of first object: %d", someOtherClass.getSomeDifferentNumber()));

    }

    interface Strategy {
        Consumer<Integer> getSetter(Object obj);
        Supplier<Integer> getGetter(Object obj);
    }

    static class SomeClass {
        private Integer someNumber;

        public Integer getSomeNumber() {
            return someNumber;
        }

        public void setSomeNumber(Integer someNumber) {
            this.someNumber = someNumber;
        }
    }

    static class SomeOtherClass {
        private Integer someDifferentNumber;

        public void setSomeDifferentNumber(Integer integer){
            this.someDifferentNumber = integer;
        }

        public Integer getSomeDifferentNumber(){
            return someDifferentNumber;
        }
    }

    static class SomeClassStrategy implements Strategy {
        public Consumer<Integer> getSetter(Object obj){
            if(!(obj instanceof SomeClass)){
                throw new IllegalArgumentException("Must be of type SomeClass");
            }
            return ((SomeClass) obj)::setSomeNumber;
        }

        public Supplier<Integer> getGetter(Object obj){
            if(!(obj instanceof SomeClass)){
                throw new IllegalArgumentException("Must be of type SomeClass");
            }
            return ((SomeClass) obj)::getSomeNumber;
        }
    }

    static class SomeOtherClassStrategy implements Strategy {

        public Consumer<Integer> getSetter(Object obj){
            if(!(obj instanceof SomeOtherClass)){
                throw new IllegalArgumentException("Must be of type SomeOtherClass");
            }
            return ((SomeOtherClass) obj)::setSomeDifferentNumber;
        }

        public Supplier<Integer> getGetter(Object obj){
            if(!(obj instanceof SomeOtherClass)){
                throw new IllegalArgumentException("Must be of type SomeClass");
            }
            return ((SomeOtherClass) obj)::getSomeDifferentNumber;
        }
    }

}
