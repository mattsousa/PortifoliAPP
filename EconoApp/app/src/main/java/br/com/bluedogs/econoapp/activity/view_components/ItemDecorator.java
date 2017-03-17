package br.com.bluedogs.econoapp.activity.view_components;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Matheus on 17/03/2017.
 */

public class ItemDecorator extends RecyclerView.ItemDecoration {

    private final int verticalSpace;

    public ItemDecorator(int verticalSpace) {
        this.verticalSpace = verticalSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = verticalSpace;
    }

}
