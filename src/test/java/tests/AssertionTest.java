package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AssertionTest {
    public static void main(String[] args) {

        Assert.assertEquals(myCalc(5,5), 10);
    }

    public static int myCalc(int a, int b){
        return a+b;
    }

    public static boolean myValue(){
        return true;
    }

    public static int myTest(){
        return 10/0;
    }


    @Test
    public void testCalc(){
        Assert.assertEquals(myCalc(5,5), 10);

    }

    @Test
    public void testMyValue(){
        Assert.assertTrue(myValue());
    }

    @Test
    public void failTest() {
       int actualResult = someFunction();
       int expectedResult = 10;
       Assert.assertEquals(actualResult, expectedResult, "my comment");

        Assert.fail("The test is fail...");
    }

    private static int someFunction() {
    return 5;
    }

//    @Test
//    public void testMyTest(){
//        Assert.assertThrows(ArithmeticException.class, ()-> myTest()); // лямбда??? - это простая запись
//    }



//    @Test
//    public void testDevideByZero(){
//        Assert.assertThrows(ArithmeticException.class, new Runnable() {
//            // runnable это интерфейс, у которого только
//            // один метод есть, который нужно имплементировать - это run
//            @Override
//            public void run() {
//              myTest();
//            }
//        });
    }


