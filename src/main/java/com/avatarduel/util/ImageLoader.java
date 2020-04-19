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
     * Set image of one card
     * @param image image of one card
     * @param path path of card's image
     */
    public static void setImage(ImageView image, String path) {
        try {
            image.setImage(ImageLoader.loadImageFromPath(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Load image of one file and return it
     * @param path path of card's image
     * @return image of one card
     * @throws FileNotFoundException exception if the file is not found
     */
    private static Image loadImageFromPath(String path) throws FileNotFoundException {
        return new Image(new FileInputStream(Paths.resourceFolder + Paths.imageFolder + path));
    }
}
