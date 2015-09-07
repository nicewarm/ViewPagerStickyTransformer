package nicewarm.com.viewpagerstickytransformer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sreay on 15/8/28.
 */
public class ViewTestItem extends LinearLayout {


    private TextView textView;
    private View rootView;
    private TextView sub_title;
    private TextView title;
    public ViewTestItem(Context context) {
        super(context);
        initView(context);
    }


    public ViewTestItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_text,this,true);
        textView = (TextView) findViewById(R.id.textView);
        rootView = findViewById(R.id.rootView);
        sub_title = (TextView) findViewById(R.id.sub_title);
        title = (TextView) findViewById(R.id.title);
    }

    public void setString(String sta){
        textView.setText(sta);
        title.setText(sta);
        sub_title.setText(sta+sta+sta);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        rootView.measure(widthMeasureSpec,widthMeasureSpec);
        textView.measure(widthMeasureSpec-200,widthMeasureSpec-200);
        setMeasuredDimension(widthMeasureSpec, widthMeasureSpec);

    }

    /*设置一定的范围值，超出范围后，使得标题彻底隐藏*/
    private float div = 0.2f;
    /*水平方向上标题位移的速度*/
    private float horiziSpeed = 1f;
    /*竖直方向上标题位移的速度*/
    private float verSpeed = 0.3f;

    public void setOffset(float position) {
        if (position > 0) {
            if (position > div) {
                /*超过范围值，彻底隐藏标题*/
                sub_title.setAlpha(0);
                title.setAlpha(0);
            } else if (position < div && position > 0) {
                title.setAlpha((div - position) / div);
                /*一级标题偏移速度比为1*/
                title.setTranslationX(position * title.getWidth() * horiziSpeed * 1);
                /*竖直方向上的偏移*/
                title.setTranslationY(-position * title.getHeight() * verSpeed);
                sub_title.setAlpha((div - position) / div);
                /*二级标题偏移速度比为2，与一级标题偏移量不同，造成错落的感觉*/
                sub_title.setTranslationX(position * sub_title.getWidth() * horiziSpeed * 2);
                /*竖直方向上的偏移*/
                sub_title.setTranslationY(-position * sub_title.getHeight() * verSpeed);
            }
        } else if (position < 0) {
            if (position < -div) {
                /*超过范围值，彻底隐藏标题*/
                sub_title.setAlpha(0);
                title.setAlpha(0);
            } else if (position > -div && position < 0) {
                title.setAlpha((-div - position) / -div);
                title.setTranslationX(position * title.getWidth() * horiziSpeed * 1);
                title.setTranslationY(position * title.getWidth() * verSpeed);
                sub_title.setAlpha((-div - position) / -div);
                sub_title.setTranslationX(position * sub_title.getWidth() * horiziSpeed * 2);
                sub_title.setTranslationY(position * sub_title.getWidth() * verSpeed);
               
            }
        } else if (position == 0) {
            /*当前页完全显示在中心，完全显示标题，偏移量为0*/
            sub_title.setAlpha(1);
            sub_title.setTranslationX(0);
            title.setAlpha(1);
            title.setTranslationX(0);
        }
    }
}
