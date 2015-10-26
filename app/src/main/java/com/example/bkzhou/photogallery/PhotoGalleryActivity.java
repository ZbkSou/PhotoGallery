package com.example.bkzhou.photogallery;

import android.support.v4.app.Fragment;

/**
 * Created by bkzhou on 15-10-22.
 */
public class PhotoGalleryActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new PhotoGalleryFragment();
    }
}
