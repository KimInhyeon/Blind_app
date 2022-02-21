package com.ksinfo.tomodomo.controller.common;

public final class ViewPagerItem {
    private final int imageID;
    private final String heading;
    private final String description;

    public ViewPagerItem(int imageID, String heading, String description) {
        this.imageID = imageID;
        this.heading = heading;
        this.description = description;
    }

    public int getImageID() {
        return imageID;
    }

    public String getHeading() {
        return heading;
    }

    public String getDescription() {
        return description;
    }
}