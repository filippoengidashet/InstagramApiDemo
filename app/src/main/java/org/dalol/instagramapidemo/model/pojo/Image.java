package org.dalol.instagramapidemo.model.pojo;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 2/21/2016
 */
public class Image {

    private String url;
    private int width, height;
    private String tags[];

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {

        String hashTag = "";

        for (String tag : tags) {
            hashTag +=  tag;
        }
        return hashTag;
    }
}
