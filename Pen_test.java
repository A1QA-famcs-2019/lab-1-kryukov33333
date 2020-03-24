import org.testng.Assert;
import org.testng.annotations.*;

public class Pen_test {


    private int inkContainerValue;
    private String color;
    private double sizeLetter;

    @BeforeClass
    public void setUp(){
        inkContainerValue = 1;
        sizeLetter = 1.0;
        color = "yellow";
    }

    @DataProvider(name = "colors")
    public Object[] getColors(){
        return new String[]{
                "red",
                "green",
                "blue",
        };
    }

    @Test(dataProvider = "colors")
    public void testColor(String color){
        Pen pen = new Pen(inkContainerValue, sizeLetter, color);
        String actualColor = pen.getColor();
        Assert.assertEquals(actualColor, color,
                "Error! Expected color " + color + " real color - " +
                        actualColor + ". | ");
    }

    @DataProvider(name = "inkContainerValue")
    public Object[][] getInkContainerValues(){
        return new Object[][]{
                {100, true},
                {1, true},
                {0, false},
                {-1, false},
        };
    }

    @Test(dataProvider = "inkContainerValue")
    public void testIsWork(int inkContainerValue, boolean expectedResult){
        Pen pen = new Pen (inkContainerValue);
        boolean actualResult = pen.isWork();
        Assert.assertEquals(actualResult, expectedResult, "Incorrect! inkContainerValue = " + inkContainerValue +
                " , actual result = " + actualResult + ", expect = " + expectedResult + ". | " );
    }

    @DataProvider(name = "writeWords")
    public Object[][] getInkAndLetterSize(){
        return new Object[][]{
                {4, 1.0, "word", "word"},
                {-1, 1.0, "word", ""},
                {10, 1.0, "", ""},
                {8, 2.0, "draw", "draw"},
        };
    }

    @Test(dataProvider = "writeWords")
    public void testWrite(int inkContainerValue, double sizeLetter, String wordToWrite, String expectedWord){
        Pen pen = new Pen(inkContainerValue, sizeLetter);
        String actualWord = pen.write(wordToWrite);
        Assert.assertEquals(actualWord, expectedWord, "Incorrect! Write with params: " +
                " inkContainerValue = " + inkContainerValue + ", sizeLetter = " + sizeLetter +
                ". Write - {" + wordToWrite + "}, actual - {" + actualWord + "}, excepted - {" +
                expectedWord + "}. | ");
    }

}