import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class TestRunner {

    public static void runTests(String className) {
        try {
            Class<?> testClass = Class.forName(className);
            Method[] methods = testClass.getDeclaredMethods();

            int totalTests = 0;
            int passedTests = 0;
            int failedTests = 0;

            for (Method method : methods) {
                if (method.isAnnotationPresent(Test.class)) {
                    totalTests++;
                    Object testInstance = testClass.getDeclaredConstructor().newInstance();

                    try {
                        invokeAnnotatedMethods(testClass, Before.class, testInstance);
                        method.invoke(testInstance);
                        invokeAnnotatedMethods(testClass, After.class, testInstance);
                        passedTests++;
                    } catch (Exception e) {
                        System.out.println("Exception in test " + method.getName() + ": " + e.getCause());
                        failedTests++;
                    }
                }
            }

            System.out.println("Total tests: " + totalTests);
            System.out.println("Passed tests: " + passedTests);
            System.out.println("Failed tests: " + failedTests);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void invokeAnnotatedMethods(Class<?> testClass, Class<? extends Annotation> annotationClass, Object testInstance) throws Exception {
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                method.invoke(testInstance);
            }
        }
    }

}
