package ojh102.github.com.fastadaptersample;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItemAdapter;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.mikepenz.fastadapter.listeners.ClickEventHook;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        final RecyclerView rvSample = (RecyclerView) findViewById(R.id.rv_sample);
        final FastItemAdapter<SampleItem> fastAdapter = new FastItemAdapter<>();
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        rvSample.setLayoutManager(linearLayoutManager);
        rvSample.setAdapter(fastAdapter);

        fastAdapter.add(getDummy());

        fastAdapter.withSelectable(true);
        fastAdapter.withOnClickListener(new FastAdapter.OnClickListener<SampleItem>() {
            @Override
            public boolean onClick(View v, IAdapter<SampleItem> adapter, SampleItem item, int position) {
                toast("view click - position : " + position + ", title : " + item.getTitle() + ", desc : " + item.getDesc());
                return true;
            }
        });
        fastAdapter.withItemEvent(new ClickEventHook<SampleItem>() {

            @Nullable
            @Override
            public View onBind(@NonNull RecyclerView.ViewHolder viewHolder) {

                if (viewHolder instanceof SampleItem.ViewHolder) {
                    return ((SampleItem.ViewHolder) viewHolder).tvTitle;
                }

                return null;
            }

            @Override
            public void onClick(View v, int position, FastAdapter<SampleItem> fastAdapter, SampleItem item) {
                toast("title click - position : " + position + ", title : " + item.getTitle() + ", desc : " + item.getDesc());
            }
        });
        fastAdapter.withItemEvent(new ClickEventHook<SampleItem>() {

            @Nullable
            @Override
            public View onBind(@NonNull RecyclerView.ViewHolder viewHolder) {

                if (viewHolder instanceof SampleItem.ViewHolder) {
                    return ((SampleItem.ViewHolder) viewHolder).tvDesc;
                }

                return null;
            }

            @Override
            public void onClick(View v, int position, FastAdapter<SampleItem> fastAdapter, SampleItem item) {
                toast("desc click - position : " + position + ", title : " + item.getTitle() + ", desc : " + item.getDesc());
            }
        });

        fastAdapter.filter("filterFive");
        fastAdapter.withFilterPredicate(new IItemAdapter.Predicate<SampleItem>() {
            @Override
            public boolean filter(SampleItem item, CharSequence constraint) {
                String title = item.getTitle();
                return title.endsWith("5");
            }
        });

    }

    private List<SampleItem> getDummy() {

        final List<SampleItem> dummyList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            dummyList.add(new SampleItem("title" + i, "desc" + i));
        }

        return dummyList;
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
