package gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import structure.ImageList;

public class IconList {
	
	public static HashMap<String, BufferedImage> ICONLIST = new HashMap<String, BufferedImage>();

	static {
		try {
			ICONLIST.put("add",ImageIO.read(ImageList.class.getResource("/icons/" + "add.png")));
			ICONLIST.put("remove", ImageIO.read(ImageList.class.getResource("/icons/" + "remove.png")));
		} catch (IOException e) {}
	}

}
