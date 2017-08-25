package com.huohu.mtrip.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.huohu.mtrip.R;
import com.huohu.mtrip.model.entity.NormalItem;
import com.huohu.mtrip.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/22.
 */

public class SingleSelectAdapter extends RecyclerView.Adapter<SingleSelectAdapter.ViewHolder> {

    protected List<NormalItem> data = new ArrayList<>();
    protected LayoutInflater inflater;
    protected Context context;
    private  String selectId;

    public List<NormalItem> getDatas() {
        return data;
    }


    public SingleSelectAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public SingleSelectAdapter(List<NormalItem> data, Context context) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }



    public String getSelectListId () {
        return selectId;
    }

    public void updateData(List<NormalItem> List) {
        this.data = List;
        this.notifyDataSetChanged();
    }
    public SingleSelectAdapter initDatass(List<NormalItem> data) {
        this.data = data;
        this.notifyDataSetChanged();
        return this;
    }

    public SingleSelectAdapter initDatass(List<NormalItem> data, String selectId) {
        this.selectId = selectId;
        this.data = data;
        this.notifyDataSetChanged();
        return this;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public void refreshView() {
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.list_item_single_sel, parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final NormalItem item = data.get(position);
        holder.name.setText(item.getName());
        holder.view.setTag(item.getId());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkBox.isChecked()) {
                    holder.checkBox.setChecked(false);
                    selectId   = null;
                } else {
                    holder.checkBox.setChecked(true);
                    selectId = Utils.toString(v.getTag());
                }
                refreshView();
            }
        });
        holder.checkBox.setChecked(!TextUtils.isEmpty(selectId) && selectId.equals(item.getId()));

    }




    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }



    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.checkbox)
        CheckBox checkBox;
        @BindView(R.id.name)
        TextView name;

        View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }
}
