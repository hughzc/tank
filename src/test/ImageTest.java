package test;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class ImageTest {
    @Test
    public void test(){
//        fail("Not");
        //单元测试检验是否为空
        try {
            BufferedImage image = ImageIO.read(new File("C:\\Code\\tank\\src\\images\\bulletD.gif"));
            //判断条件是否成立
            assertNotNull(image);
//            System.out.println(ImageTest.class.getClassLoader().getResource("images/bulletD.gif"));
            BufferedImage image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            assertNotNull(image2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
