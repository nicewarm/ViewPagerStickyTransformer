package nicewarm.com.viewpagerstickytransformer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerSlidingTabStrip tabStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.pager);
        tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        initView();
    }

    private void initView() {
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        /*设置viewpager切换动画*/
        viewPager.setPageTransformer(false, new StickyPagerTransformer());
        tabStrip.setViewPager(viewPager);
         /*游标颜色*/
        tabStrip.setIndicatorColor(Color.parseColor("#ff6666"));
        /*选中tab字体颜色*/
        tabStrip.setSelectedTextColor(Color.parseColor("#ff6666"));
        /*tab之间的分割线颜色*/
        tabStrip.setDividerColor(Color.TRANSPARENT);
        tabStrip.setTextSize(16);
        /*底线高度*/
        tabStrip.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                1, getResources().getDisplayMetrics()));
        // 游标高度
        tabStrip.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                3, getResources().getDisplayMetrics()));
        tabStrip.setShouldExpand(true);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {"AndroidBeta", "Cupcake", "Donut", "Froyo", "Gingerbread", "Honeycomb",
                "Ice Cream Sandwich", "Jelly Bean", "KitKat"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public Fragment getItem(int position) {
            return new FragmentTest();
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }
}
