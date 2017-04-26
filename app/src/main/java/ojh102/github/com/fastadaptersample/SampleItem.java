package ojh102.github.com.fastadaptersample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

/**
 * Created by OhJaeHwan on 2017-04-26.
 */

public class SampleItem extends AbstractItem<SampleItem, SampleItem.ViewHolder> {

    private String title;
    private String desc;

    public SampleItem(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    @Override
    public int getType() {
        return R.id.sample_item_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.view_sample;
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public void bindView(ViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);

        holder.tvTitle.setText(title);
        holder.tvDesc.setText(desc);
    }

    @Override
    public void unbindView(ViewHolder holder) {
        super.unbindView(holder);

        holder.tvTitle.setText(null);
        holder.tvDesc.setText(null);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvTitle;
        protected TextView tvDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.tv_title);
            tvDesc = (TextView)itemView.findViewById(R.id.tv_desc);
        }
    }
}
