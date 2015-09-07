package nicewarm.com.viewpagerstickytransformer;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager.PageTransformer;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by sreay on 15/5/25.
 */
public class StickyPagerTransformer implements PageTransformer {

    /*
    * 继承PageTransformer
    * 重写transformPage方法
    * 通过transformPage方法中的v参数，得到滑动的变化值
    * 获取transformPage方法中view的childView,设置变化
    * */

    private RecyclerView recyclerView;
    private float speed = 0.3f;


    /*在一次手势滑动中，关系到两页的变化
  * 向左滑动，关系到未滑动时处于左边的那一页和未滑动时显示的当前页
  * 向右滑动，关系到未滑动时处于右边的那一页和未滑动时显示的当前页
  * 两个页的变化都会回调到transformPage方法中
  * */
    @SuppressLint("NewApi")
    @Override
    public void transformPage(View pager, float v) {
        /*
         * -1<v<0，pager为向做滑动的页
         * v = -1，pager为完全到达左边页
         * 0<v<1,pager为向右滑动的页
         * v = 1，pager为完全到达右边页
        */
        /*position = 0，pager处于正中心，没有位移*/
        recyclerView = (RecyclerView) pager.findViewById(R.id.my_recycler_view);
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            ViewTestItem itemView = (ViewTestItem) recyclerView.getChildAt(i);
            float weight;
            if (v > 0) {
                weight = speed * i;
            } else {
                weight = speed * Math.abs(4 - i);
            }
            trans(itemView, v, weight);
        }
    }


    private void trans(ViewTestItem viewTestItem, float position, float offset) {
        float value = 0;
        if (viewTestItem != null) {
            float width = viewTestItem.getWidth();
            if (position != 0) {
                /*当position不为0时，不用区分position>0还是position<0
                * 因为每次滑动时，向右滑动位移为正，向左滑动位移为负
                * 想让item向右位移，
                * 那向左滑动时，偏移量就应该也为负值，只是比位移量绝对值小，才能出现粘滞视差的效果
                * 如果向左滑动，偏移量却为正值，那就不是粘滞的效果，而且相反方向的运动了
                * 那向右滑动时，偏移量就应该也为正值，只是比位移量绝对值小，才能出现粘滞视差的效果
                * 想让item向左位移
                * 那向左滑动时，偏移量就应该也为负值，只是比位移量绝对值大，才能出现粘滞视差的效果
                * 那向右滑动时，偏移量就应该也为正值，只是比位移量绝对值大，才能出现粘滞视差的效果
                * */
                value = (position * width * offset);
                /*设置item在X方向上位移*/
                viewTestItem.setTranslationX(value);
                viewTestItem.setOffset(position * offset);
            } else if (position == 0) {
                viewTestItem.setTranslationX(0);
                viewTestItem.setOffset(0);
            }
        }
    }
}
