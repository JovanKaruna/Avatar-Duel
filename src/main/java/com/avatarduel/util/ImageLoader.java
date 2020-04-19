package com.avatarduel.util;

import com.avatarduel.Paths;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

// Utility Pattern
public class ImageLoader {
    private ImageLoader(){
        throw new AssertionError("This is a utility class!");
    }

    /**
     * @param image TODO
     * @param path
     */
    public static void setImage(ImageView image, String path) {
        try {
            image.setImage(ImageLoader.loadImageFromPath(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param path TODO
     * @return
     * @throws FileNotFoundException
     */
    private static Image loadImageFromPath(String path) throws FileNotFoundException {
        return new Image(new FileInputStream(Paths.resourceFolder + Paths.imageFolder + path));
    }
}
