package com.heyrent.app.renter.view.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.heyrent.app.renter.R;
import com.heyrent.app.renter.base.BaseActivity;
import com.heyrent.app.renter.base.BaseFragment;
import com.heyrent.app.renter.widget.SlideViewPager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainContract.View {
    @BindView(R.id.vp_main)
    SlideViewPager mViewPager;
    @BindView(R.id.ll_main_item_discovery)
    View mDiscoveryItem;
    @BindView(R.id.ll_main_item_collection)
    View mCollectionItem;
    @BindView(R.id.ll_main_item_workbench)
    View mWorkbenchItem;
    @BindView(R.id.ll_main_item_messages)
    View mMessageItem;
    @BindView(R.id.ll_main_item_me)
    View mMeItem;

    @Inject
    MainPresenter mPresenter;
    private List<BaseFragment> mFragmentList;

    @Override
    protected int layout() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean needStatusBar() {
        return false;
    }

    @Override
    protected void initView() {
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setCurrentItem(0);
        switchFragment(0);
    }

    @Override
    protected void initData() {
        getLifecycle().addObserver(mPresenter);
        mPresenter.takeView(this);

        mFragmentList = new ArrayList<>();
        mFragmentList.add(new DiscoveryFragment());
        mFragmentList.add(new CollectionFragment());
        mFragmentList.add(new WorkbenchFragment());
        mFragmentList.add(new MessageFragment());
        mFragmentList.add(new MeFragment());

    }

    private void switchFragment(int position) {
        if (position > mFragmentList.size() || position < 0) {
            return;
        }
        mViewPager.setCurrentItem(position);
        mDiscoveryItem.setSelected(position == 0);
        mCollectionItem.setSelected(position == 1);
        mWorkbenchItem.setSelected(position == 2);
        mMessageItem.setSelected(position == 3);
        mMeItem.setSelected(position == 4);
    }

    @OnClick({R.id.ll_main_item_discovery, R.id.ll_main_item_collection, R.id.ll_main_item_workbench,
            R.id.ll_main_item_messages, R.id.ll_main_item_me})
    void handleClick(View view) {
        switch (view.getId()) {
            case R.id.ll_main_item_discovery:
                switchFragment(0);
                break;
            case R.id.ll_main_item_collection:
                switchFragment(1);
                break;
            case R.id.ll_main_item_workbench:
                switchFragment(2);
                break;
            case R.id.ll_main_item_messages:
                switchFragment(3);
                break;
            case R.id.ll_main_item_me:
                switchFragment(4);
                break;
        }
    }

    private FragmentPagerAdapter viewPagerAdapter =
            new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return mFragmentList == null ? null : mFragmentList.get(position);
                }

                @Override
                public int getCount() {
                    return mFragmentList == null ? 0 : mFragmentList.size();
                }
            };

}
