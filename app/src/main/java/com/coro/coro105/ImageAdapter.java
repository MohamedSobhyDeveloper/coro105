package com.coro.coro105;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ImageAdapter extends PagerAdapter {
    private Context mContext;
    private LayoutInflater layoutInflater;
    private int[] mImageIds=new int[]{R.drawable.sliderone,R.drawable.slidertwo,R.drawable.sliderthree,R.drawable.sliderfour,R.drawable.sliderfive};

  public ImageAdapter(Context context){

        mContext=context;
    }



    @Override
    public int getCount() {
        return mImageIds.length;
    }

    @Override
    public boolean isViewFromObject(View view,Object object) {
        return view==object;
    }


    @Override
    public Object instantiateItem( ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.custom_layout,null);
        ImageView imageView=(ImageView)view.findViewById(R.id.custom_imageview);
        imageView.setImageResource(mImageIds[position]);

        ViewPager viewPager=(ViewPager)container;
        viewPager.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem( ViewGroup container, int position, Object object) {

        ViewPager viewPager=(ViewPager)container;
        View view=(View)object;
        viewPager.removeView(view);
    }
}
