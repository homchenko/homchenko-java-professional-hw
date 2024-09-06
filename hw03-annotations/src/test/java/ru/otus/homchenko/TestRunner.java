package ru.otus.homchenko;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public static void runTests(String className) {
        try {
            Class<?> testClass = Class.forName(className);
            Method[] methods = testClass.getDeclaredMethods();

            List<Method> beforeMethods = new ArrayList<>();
            List<Method> afterMethods = new ArrayList<>();
            List<Method> testMethods = new ArrayList<>();

            distributeMethods(methods, beforeMethods, afterMethods, testMethods);

            int totalTests = testMethods.size();
            int passedTests = 0;
            int failedTests = 0;

            for (Method testMethod : testMethods) {
                Object testInstance = testClass.getDeclaredConstructor().newInstance();
                boolean testPassed = executeTest(testInstance, beforeMethods, afterMethods, testMethod);

                if (testPassed) {
                    passedTests++;
                } else {
                    failedTests++;
                }
            }

            printResults(totalTests, passedTests, failedTests);

        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка: класс '" + className + "' не найден.");
        } catch (Exception e) {
            System.out.println("Произошла ошибка при выполнении тестов: " + e.getMessage());
        }
    }

    private static void distributeMethods(Method[] methods, List<Method> beforeMethods,
                                          List<Method> afterMethods, List<Method> testMethods) {
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            } else if (method.isAnnotationPresent(Before.class)) {
                beforeMethods.add(method);
            } else if (method.isAnnotationPresent(After.class)) {
                afterMethods.add(method);
            }
        }
    }

    private static boolean executeTest(Object testInstance, List<Method> beforeMethods,
                                       List<Method> afterMethods, Method testMethod) throws Exception {
        boolean testPassed = true;

        try {
            invokeMethods(beforeMethods, testInstance);
            testMethod.invoke(testInstance);
        } catch (Exception e) {
            System.out.println("Исключение в тесте " + testMethod.getName() + ": " + e.getCause());
            testPassed = false;
        } finally {
            invokeMethods(afterMethods, testInstance);
        }

        return testPassed;
    }

    private static void printResults(int totalTests, int passedTests, int failedTests) {
        System.out.println("Всего тестов: " + totalTests);
        System.out.println("Пройденные тесты: " + passedTests);
        System.out.println("Не пройденные тесты: " + failedTests);
    }

    private static void invokeMethods(List<Method> methods, Object testInstance) throws Exception {
        for (Method method : methods) {
            method.invoke(testInstance);
        }
    }
}
