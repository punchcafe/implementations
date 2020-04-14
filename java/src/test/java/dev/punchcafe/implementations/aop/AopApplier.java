package dev.punchcafe.implementations.aop;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class AopApplier {
    public static void main(String[] args) {

        PlainClass plainClass = new PlainClass();

        Class<?> dynamicType = new ByteBuddy()
                .subclass(PlainClass.class)
                .method(ElementMatchers.named("helloUniverse"))
                .intercept(FixedValue.value("Hello Universe!"))
                .make()
                .load(AopApplier.class.getClassLoader())
                .getLoaded();
    }
}
