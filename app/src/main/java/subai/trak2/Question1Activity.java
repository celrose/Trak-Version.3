package subai.trak2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Question1Activity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private TextView help_bar_view;
    //private LinearLayout dotsLayout;
    //private TextView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext;
    private Toolbar q1_toolbar;
    private int curr = 0;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
        this.finish();
        //super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question1);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        q1_toolbar = (Toolbar) findViewById(R.id.toolbar_help1);
        setSupportActionBar(q1_toolbar);

        q1_toolbar.setNavigationIcon(R.drawable.ic_prev);
        q1_toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        viewPager = (ViewPager) findViewById(R.id.q1_view_pager);
        help_bar_view = (TextView) findViewById(R.id.text_help);
        //dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.prev_button);
        btnNext = (Button) findViewById(R.id.next_button);

        layouts = new int[]{
                R.layout.answer1_1,
                R.layout.answer1_2,
                R.layout.answer1_3};

        // adding bottom dots
        //addBottomDots(0);

        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        if (curr == layouts.length - 1) {
            btnNext.setVisibility(View.GONE);
            btnSkip.setVisibility(View.VISIBLE);

//            btnSkip.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    viewPager.arrowScroll(View.FOCUS_LEFT);
//                }
//            });
        } else {
            if (curr == 0) {
                btnNext.setVisibility(View.VISIBLE);
                btnSkip.setVisibility(View.GONE);

//                btnNext.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        viewPager.arrowScroll(View.FOCUS_RIGHT);
//                    }
//                });
            } else {
                btnNext.setVisibility(View.VISIBLE);
                btnSkip.setVisibility(View.VISIBLE);

//                btnSkip.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        viewPager.arrowScroll(View.FOCUS_LEFT);
//                    }
//                });
//
//                btnNext.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        viewPager.arrowScroll(View.FOCUS_RIGHT);
//                    }
//                });
            }
        }

    }

    public void btnSkipClick1(View v) {
        viewPager.arrowScroll(View.FOCUS_LEFT);
    }

    public void btnNextClick1(View v) {
        viewPager.arrowScroll(View.FOCUS_RIGHT);
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            curr = position;
            if (position == layouts.length - 1) {
                btnNext.setVisibility(View.GONE);
                btnSkip.setVisibility(View.VISIBLE);
            } else {
                if (position == 0) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnSkip.setVisibility(View.GONE);
                } else {
                    btnNext.setVisibility(View.VISIBLE);
                    btnSkip.setVisibility(View.VISIBLE);
                }
            }
//            if (position == 0) {
//                btnSkip.setVisibility(View.GONE);
//            }
//            addBottomDots(position);
//            // changing the next button text 'NEXT' / 'GOT IT'
//            if (position == layouts.length - 1) {
//                // last page. make button text to GOT IT
//                btnNext.setText(getString(R.string.start));
//                btnSkip.setVisibility(View.GONE);
//            } else {
//                // still pages are left
//                btnNext.setText(getString(R.string.next));
//                btnSkip.setVisibility(View.VISIBLE);
//            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };


    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    public class ViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;


        public ViewPagerAdapter() {

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
