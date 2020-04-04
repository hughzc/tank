package test;

import com.zc.tank.Audio;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AudioTest {
    @Test
    public void audioTest(){
        try {
            Audio a = new Audio("audio/explode.wav");
            assertNotNull(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
