package com.ajzamora.heavenbaked.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

public class AvatarUtils {
    private static final String RES_TYPE_DRAWABLE = "drawable";
    public static final String COMMON_BAKER_NAME = "ic_baker";
    private static final int NUM_BAKER_DRAWABLE = 9;

    public static class BakerAvatarPicker {
        private Context mContext;
        private Drawable[] bakerDrawables = new Drawable[NUM_BAKER_DRAWABLE];
        private int[] drawableIntSuffix = new int[NUM_BAKER_DRAWABLE];
        private int currentIndex;

        public BakerAvatarPicker(Context context) {
            mContext = context;
            initializeData();
        }

        private void initializeData() {
            currentIndex = 0;
            for (int i = 0; i < NUM_BAKER_DRAWABLE; i++) {
                bakerDrawables[i] = getContextBakerAvatar(i);
                drawableIntSuffix[i] = i;
            }
        }

        private int getRandomBakerDrawableIndex() {
            int nextIndex = currentIndex + 1;
            if (nextIndex < NUM_BAKER_DRAWABLE) {
                int randomIndex = getRandomNumWithinRange(nextIndex, NUM_BAKER_DRAWABLE);
                swapIntArray(drawableIntSuffix, currentIndex, randomIndex);
                return drawableIntSuffix[currentIndex++];
            }
            currentIndex = 1;
            return drawableIntSuffix[nextIndex];
        }

        private void swapIntArray(int[] arr, int i, int j) {
            if (i == j) return;
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }

        private int getRandomNumWithinRange(int min, int max) {
            return (int) ((Math.random() * (max - min)) + min);
        }

        private Drawable getContextBakerAvatar(int intSuffix) {
            String s = COMMON_BAKER_NAME;
            Resources resources = mContext.getResources();
            final int resourceId = resources.getIdentifier(s.concat(String.valueOf(intSuffix)), RES_TYPE_DRAWABLE,
                    mContext.getPackageName());
            return ContextCompat.getDrawable(mContext, resourceId);
        }

        public Drawable getRandomContextAvatar(String commonName) {
            int bakerIndex = getRandomBakerDrawableIndex();
            return bakerDrawables[bakerIndex];
        }
    }
}

