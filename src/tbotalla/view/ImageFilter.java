package tbotalla.view;

import java.io.File;
import javax.swing.filechooser.FileFilter;
import tbotalla.model.Utils;

public class ImageFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		String extension = Utils.getExtension(f);
		if (extension != null) {
			if (extension.equals(Utils.jpeg) || extension.equals(Utils.jpg) || extension.equals(Utils.png)) {
//				System.out.println("Formato de imagen OK");
				return true;
			} else {
//				System.out.println("Formato de imagen invalido");
				return false;
			}
		}
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
