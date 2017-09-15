package com.huohu.mtrip.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huohu.mtrip.R;
import com.huohu.mtrip.model.key.FragKey;
import com.huohu.mtrip.util.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class IntroduceFragment extends PageImpBaseFragment {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.content)
    TextView content;
    Unbinder unbinder;

    @Override
    protected void initData() {

    }

    @Override
    protected void afterViewCreate() {
        setContentLayout(R.layout.fragment_introduce_layout);
        ButterKnife.bind(this, getContentLayout());
        backEnable(true);
        setTitle("乐园介绍");
        setRightImageTips(R.mipmap.title_msg);

        initContent();
    }

    private void initContent() {
        ImageUtils.getInstance().dispalyFromAssets("intro.jpg",image);
        content.setText("  园中的游乐项目多种多样，如原始社会模拟型、未来世界幻想型、大型惊险项目、智力比赛项目、经典射击等。有的游乐园项目齐全，有的以一个或数个项目为主。世界上最负盛名的游乐园是1958年在美国加利福尼亚州洛杉矶市建造的迪士尼乐园。它是米老鼠动画片制片人迪士尼（W.Disney）一生的杰作，每年吸引数千万旅游者。以后，美国南方佛罗里达半岛又修建了同样的一座。1983年，第三处以“迪士尼”命名的大型游乐园在日本东京城附近落成。近年中国大城市中也出现了一些游乐园。今后随着国内外旅游业的发展，游乐园建设将会日臻扩大和完善。游乐场中有很多深受年轻人喜爱的过山车以及各种游乐设备，是释放压力、感受刺激的好去处！");
    }
    @Override
    protected void onRightTipViewClick() {
        gotoFragment(FragKey.msg_cate_fragment);
    }

    @Override
    public void refreshView() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
