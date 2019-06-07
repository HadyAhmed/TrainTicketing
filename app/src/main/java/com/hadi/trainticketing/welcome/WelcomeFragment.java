package com.hadi.trainticketing.welcome;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.FragmentWelcomeBinding;
import com.hadi.trainticketing.datasource.database.StaticDataSource;
import com.rd.animation.type.AnimationType;

import java.util.List;

/**
 * @author Hady Ahmed
 * @version 1.0
 * this is the onBoarding screen <b>Landing Page</b> that show up the app features for the user
 */
public class WelcomeFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private FragmentWelcomeBinding welcomeBinding;
    private WelcomeContent welcomeContent = new WelcomeContent(StaticDataSource.getContentDescription(), StaticDataSource.getContentResources());
    private Context context;

    private OnStartFragmentListener fragmentListener;

    public WelcomeFragment() {

    }

    public interface OnStartFragmentListener {
        void navigateToAccountRuleChoose();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        try {
            fragmentListener = (OnStartFragmentListener) context;
        } catch (ClassCastException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        welcomeBinding = FragmentWelcomeBinding.inflate(inflater, container, false);

        // setup view pager indicator
        welcomeBinding.pageIndicatorView.setAnimationDuration(2000);
        welcomeBinding.pageIndicatorView.setAnimationType(AnimationType.WORM);
        welcomeBinding.pageIndicatorView.setCount(3);

        // setup view pager
        welcomeBinding.viewPager.setAdapter(new ViewPagerAdapter(welcomeContent, context));
        welcomeBinding.viewPager.addOnPageChangeListener(this);

        // click listener for the get start button on the landing page
        welcomeBinding.startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentListener.navigateToAccountRuleChoose();
            }
        });

        return welcomeBinding.getRoot();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //
    }

    @Override
    public void onPageSelected(int position) {
        welcomeBinding.pageIndicatorView.setSelection(position);

        if (position == welcomeContent.getListSize() - 1) {
            welcomeBinding.startBtn.setText("GET STARTED!");
        } else {
            welcomeBinding.startBtn.setText("Skip");
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //
    }
}

/**
 * this blueprint holds the content data for the user to show up text view
 * and image resources
 */
class WelcomeContent {
    private List<String> contentDescription;
    private List<Integer> contentDrawableResources;

    WelcomeContent(List<String> contentDescription, List<Integer> contentDrawableResources) {
        this.contentDescription = contentDescription;
        this.contentDrawableResources = contentDrawableResources;
    }

    List<Integer> getContentDrawableResources() {
        return contentDrawableResources;
    }

    List<String> getContentDescription() {
        return contentDescription;
    }

    int getListSize() {
        return contentDescription.size();
    }
}

/**
 * adapter for the view pager that handles movements and insert content
 */
class ViewPagerAdapter extends PagerAdapter {
    private WelcomeContent welcomeContent;
    private Context context;

    ViewPagerAdapter(WelcomeContent welcomeContent, Context context) {
        this.welcomeContent = welcomeContent;
        this.context = context;
    }

    @Override
    public int getCount() {
        return welcomeContent.getListSize();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.welcome_page, container, false);
        TextView bodyTextView = rootView.findViewById(R.id.contentBody);
        ImageView contentImage = rootView.findViewById(R.id.contentImage);

        bodyTextView.setText(welcomeContent.getContentDescription().get(position));
        contentImage.setImageResource(welcomeContent.getContentDrawableResources().get(position));

        container.addView(rootView);
        return rootView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
