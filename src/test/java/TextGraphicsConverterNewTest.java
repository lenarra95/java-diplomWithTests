import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import ru.netology.graphics.image.BadImageSizeException;
import ru.netology.graphics.image.TextGraphicsConverterNew;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TextGraphicsConverterNewTest {

    TextGraphicsConverterNew converter;

    @BeforeAll
    public void initTextGraphicsConverterNew () {
        converter = new TextGraphicsConverterNew();
    }

    @Test
    public void negativeTestCheckRatio () {
        converter.setMaxRatio(1);
        converter.setHeight(100);
        converter.setWidth(200);
        Assertions.assertThrows(BadImageSizeException.class, converter::checkMaxRatio);
    }

    @Test
    public void positiveTestCheckMaxRatio () throws BadImageSizeException {
        converter.setMaxRatio(10);
        converter.setHeight(500);
        converter.setWidth(400);
        converter.checkMaxRatio();
    }

    @Test
    public void positiveTestCompressImg () {
        int height = 500;
        int width = 400;
        converter.setHeight(height);
        converter.setWidth(width);
        converter.setMaxHeight(200);
        converter.setMaxWidth(200);
        converter.compressImg();
        Assertions.assertNotEquals(height, converter.getHeight());
        Assertions.assertNotEquals(width, converter.getWidth());
    }

    @Test
    public void negativeTestCompressImg () {
        int height = 200;
        int width = 180;
        converter.setHeight(height);
        converter.setWidth(width);
        converter.setMaxHeight(200);
        converter.setMaxWidth(200);
        converter.compressImg();
        Assertions.assertEquals(height, converter.getHeight());
        Assertions.assertEquals(width, converter.getWidth());
    }
}
