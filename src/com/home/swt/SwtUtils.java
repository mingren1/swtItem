package com.home.swt;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

public class SwtUtils {

	public static Image getimage(String path,Display display) {
		Image image1 = new Image(display, path);
		Rectangle bounds = image1.getBounds();
		Image image = new Image(display, 20, 20);
		GC gc = new GC(image); 
		gc.drawImage(image1, 0, 0, bounds.width, bounds.height, 0, 0, 20, 20);
		gc.dispose();
		return image;
	}
}
