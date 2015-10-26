package com.example.bkzhou.photogallery;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bkzhou on 15-10-22.
 */
public class PhotoGalleryFragment extends Fragment {
    private static final String TAG   = "PhotoGalleryFragment";
    GridView mGridView;
    List<GalleryItem.Data> mItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchItemsTask().execute();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photo_gallery,container,false);
        mGridView = (GridView) v.findViewById(R.id.gridView);
        setupAdapter();
        return v;
    }
    private class FetchItemsTask extends AsyncTask<Void,Void,GalleryItem>{

        @Override
        protected GalleryItem doInBackground(Void... voids) {
            return  new FlickrFetchr().fetchItems();
        }

        @Override
        protected void onPostExecute(GalleryItem galleryItem) {
            mItems = galleryItem.getData();
            setupAdapter();
        }
    }
    void setupAdapter() {
        if (getActivity() == null || mGridView == null) return;

        if (mItems != null) {
            mGridView.setAdapter(new ArrayAdapter<GalleryItem.Data>(getActivity(),
                    android.R.layout.simple_gallery_item, mItems));
            mGridView.setAdapter(new GalleryItemAdapter(mItems));
        } else {
            mGridView.setAdapter(null);
        }
    }
    private class GalleryItemAdapter extends ArrayAdapter<GalleryItem.Data>{

        public GalleryItemAdapter(List<GalleryItem.Data> items) {
            super(getActivity(),0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.gallery_item,parent,false);

            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.gallery_item_imageView);
            imageView.setImageResource(R.mipmap.ic_launcher);
            return convertView;
        }
    }


}
