package com.davenotdavid.circularscrollingsample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements WearableListView.ClickListener {

    private static final String[] mElements =
            {"3X3", "4X4", "5X5", "6X6", "7X7", "8X8", "9X9", "10X10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WearableListView listView = (WearableListView) findViewById(R.id.wearable_list);

        listView.setAdapter(new Adapter(this, mElements));
        listView.setClickListener(this);

        if (savedInstanceState == null) {
            listView.scrollToPosition(Integer.MAX_VALUE / 2);
        }
    }

    @Override
    public void onClick(WearableListView.ViewHolder v) {
        Integer tag = (Integer) v.itemView.getTag();

        Toast toast = null;
        if (tag.equals(0)) {
            toast = Toast.makeText(this, "3X3 clicked", Toast.LENGTH_SHORT);
        } else if (tag.equals(1)) {
            toast = Toast.makeText(this, "4X4 clicked", Toast.LENGTH_SHORT);
        } else if (tag.equals(2)) {
            toast = Toast.makeText(this, "5X5 clicked", Toast.LENGTH_SHORT);
        } else if (tag.equals(3)) {
            toast = Toast.makeText(this, "6X6 clicked", Toast.LENGTH_SHORT);
        } else if (tag.equals(4)) {
            toast = Toast.makeText(this, "7X7 clicked", Toast.LENGTH_SHORT);
        } else if (tag.equals(5)) {
            toast = Toast.makeText(this, "8X8 clicked", Toast.LENGTH_SHORT);
        } else if (tag.equals(6)) {
            toast = Toast.makeText(this, "9X9 clicked", Toast.LENGTH_SHORT);
        } else if (tag.equals(7)) {
            toast = Toast.makeText(this, "10X10 clicked", Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    @Override
    public void onTopEmptyRegionClick() {}

    private static final class Adapter extends WearableListView.Adapter {
        private String[] mDataset;
        private final Context mContext;
        private final LayoutInflater mInflater;

        public Adapter(Context context, String[] dataset) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
            mDataset = dataset;
        }

        public static class ItemViewHolder extends WearableListView.ViewHolder {
            private TextView textView;

            public ItemViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.name);
            }
        }

        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(mInflater.inflate(R.layout.list_item, null));
        }

        @Override
        public void onBindViewHolder(WearableListView.ViewHolder holder, int position) {
            position = position % mElements.length;

            ItemViewHolder itemHolder = (ItemViewHolder) holder;
            TextView view = itemHolder.textView;
            view.setText(mDataset[position]);
            holder.itemView.setTag(position);
        }

        @Override
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }
    }
}
