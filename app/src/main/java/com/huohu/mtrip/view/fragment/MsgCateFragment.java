package com.huohu.mtrip.view.fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.huohu.mtrip.R;
import com.huohu.mtrip.model.data.MsgData;
import com.huohu.mtrip.model.entity.MsgInfo;
import com.huohu.mtrip.model.key.FragKey;
import com.huohu.mtrip.model.net.BaseSubscriber;
import com.huohu.mtrip.util.DateUtil;
import com.huohu.mtrip.util.Utils;
import com.huohu.mtrip.util.ViewUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class MsgCateFragment extends PageImpBaseFragment {
    @Override
    protected void initData() {

    }

    @Override
    protected void afterViewCreate() {

        backEnable(true);
        setTitle("消息");

        FrameLayout contentLayout = getContentLayout();
        contentLayout.removeAllViews();
        final LinearLayout layout= new LinearLayout(getContext());
        contentLayout.addView(layout);

        View view2 = LayoutInflater.from(getContext()).inflate(R.layout.msg_cate_item_layout, null);
        ImageView imagew = (ImageView) view2.findViewById(R.id.image);
        imagew.setImageResource(R.mipmap.msg_all);
        TextView title = (TextView) view2.findViewById(R.id.msg_title);
        TextView content = (TextView) view2.findViewById(R.id.msg_content);
        TextView timeTv = (TextView) view2.findViewById(R.id.msg_time);
        imagew.setImageResource(R.mipmap.msg_system_img);
        title.setText("全部消息");
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAllClick();
            }
        });
        ViewUtil.setGone(content);
        ViewUtil.setGone(timeTv);
        layout.addView(view2);

        MsgData.getInstance().getUnreadMsgList().subscribe(new BaseSubscriber<List<MsgInfo>>() {
            @Override
            public void onNext(List<MsgInfo> msgDatas) {
                layout.setOrientation(LinearLayout.VERTICAL);
                if (msgDatas.size() > 0) {
                    MsgInfo data = msgDatas.get(0);
                    View view1 = LayoutInflater.from(getContext()).inflate(R.layout.msg_cate_item_layout, null);
                    ImageView imagew = (ImageView) view1.findViewById(R.id.image);
                    TextView title = (TextView) view1.findViewById(R.id.msg_title);
                    TextView content = (TextView) view1.findViewById(R.id.msg_content);
                    TextView timeTv = (TextView) view1.findViewById(R.id.msg_time);
                    imagew.setImageResource(R.mipmap.msg_unread);
                    title.setText("未读消息");
                    content.setText(Html.fromHtml(data.getContent()));
                    timeTv.setText( DateUtil.getTimeDiffDayCurrent(Utils.toLong(data.getAddtime())));
                    view1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onUnreadClick();
                        }
                    });
                    layout.addView(view1,0);
                }


            }
        });

    }


    protected  void onUnreadClick() {
        Map<String,Object> m = new HashMap<>();
        m.put("type",MsgData.NEW_MSG);
        m.put("title","未读消息");
        gotoFragment(FragKey.msg_list_fragment, JSONObject.toJSONString(m));
    }
    protected  void onAllClick() {
        Map<String,Object> m = new HashMap<>();
        m.put("type",MsgData.ALL_MSG);
        m.put("title","全部消息");
        gotoFragment(FragKey.msg_list_fragment,JSONObject.toJSONString(m));

    }
    @Override
    public void refreshView() {

    }
}
