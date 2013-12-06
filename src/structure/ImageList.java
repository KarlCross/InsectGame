package structure;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * All structure images.
 */
public class ImageList {

	public static BufferedImage ANTHILL;
	
	static {
		try {
			ANTHILL = ImageIO.read(ImageList.class.getResource("/buildings/" + "anthill.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
