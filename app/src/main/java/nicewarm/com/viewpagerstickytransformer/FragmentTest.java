package nicewarm.com.viewpagerstickytransformer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sreay on 15/8/27.
 */
public class FragmentTest extends Fragment {


    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private MyAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragement,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*指定为两列*/
        layoutManager  =new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        String[] myDataset = {"AndroidBeta", "Cupcake", "Donut", "Froyo", "Gingerbread", "Honeycomb",
                "Ice Cream Sandwich", "Jelly Bean", "KitKat"};
        adapter = new MyAdapter(myDataset);
        recyclerView.setAdapter(adapter);
    }
}
