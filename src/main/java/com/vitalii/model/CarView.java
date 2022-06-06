package com.vitalii.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CarView {

    private final Image image = new Image("car2_1.png");
    private final ImageView imageView = new ImageView(image);

    public CarView() {
        imageView.setFitHeight(37.375);
        imageView.setFitWidth(75);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
