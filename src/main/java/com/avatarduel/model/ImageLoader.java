package com.avatarduel.model;

import com.avatarduel.Paths;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageLoader {
    private ImageLoader(){
        throw new AssertionError("This is a utility class!");
    }

    public static void setImage(ImageView image, String path) {
        try {
            image.setImage(ImageLoader.loadImageFromPath(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Image loadImageFromPath(String path) throws FileNotFoundException {
        return new Image(new FileInputStream(Paths.RESOURCE_PATH + Paths.IMAGE_PATH + path));
    }
}
