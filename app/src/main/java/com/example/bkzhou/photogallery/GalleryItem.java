package com.example.bkzhou.photogallery;

import java.util.List;

/**
 * Created by bkzhou on 15-10-22.
 */
public class GalleryItem {

    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data{

        private String image_url ;

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }


}
