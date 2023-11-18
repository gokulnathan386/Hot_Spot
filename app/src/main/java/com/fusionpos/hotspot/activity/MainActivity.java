package com.fusionpos.hotspot.activity;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fusionpos.hotspot.R;
import com.fusionpos.hotspot.adapter.DashboardAdapter;
import com.fusionpos.hotspot.model.dashboard;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //  CardView home, orderonline, review, login, booktable, contactus;
    private Context mContext = MainActivity.this;

    private RecyclerView recyclerView;
    private DashboardAdapter adapter;
    private List<dashboard> dashboardList;

    RelativeLayout rlTop;
    AppBarLayout Appbar;
    CollapsingToolbarLayout CoolToolbar;


    boolean ExpandedActionBar = true;


    FloatingActionButton fab;
    TextView privacypolicy, teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        privacypolicy = findViewById(R.id.privacypolicy);
        teams = findViewById(R.id.teams);

       /* Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        privacypolicy.startAnimation(anim);*/

        privacypolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Extras.class);
                intent.putExtra("extraurl", getString(R.string.privacyurl));
                startActivity(intent);
            }
        });
        teams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Extras.class);
                intent.putExtra("extraurl", getString(R.string.termsurl));
                startActivity(intent);
            }
        });
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

        rlTop = (RelativeLayout) findViewById(R.id.rltop);
        Appbar = (AppBarLayout) findViewById(R.id.appbar);
        CoolToolbar = (CollapsingToolbarLayout) findViewById(R.id.ctolbar);


        Appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (Math.abs(verticalOffset) > 200) {
                    ExpandedActionBar = false;
                    CoolToolbar.setTitle("Spice Corner");
                    rlTop.setVisibility(View.GONE);
                    invalidateOptionsMenu();
                } else {
                    ExpandedActionBar = true;
                    CoolToolbar.setTitle("");
                    rlTop.setVisibility(View.VISIBLE);
                    invalidateOptionsMenu();
                }
            }
        });

        ImageView imgProfile = (ImageView) findViewById(R.id.profile_image);
        Glide.with(mContext)
                .load(R.drawable.applogo)
                .apply(RequestOptions.circleCropTransform())
                .into(imgProfile);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        dashboardList = new ArrayList<>();
        adapter = new DashboardAdapter(mContext, dashboardList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        getMenu();

    }


    private void getMenu() {


        dashboardList.add(new dashboard(R.drawable.shoppingcart, "Order Online"));
        dashboardList.add(new dashboard(R.drawable.ic_baseline_notifications_active_24, "Notifications"));
        dashboardList.add(new dashboard(R.drawable.review, "Reviews"));
        dashboardList.add(new dashboard(R.drawable.login, "Login"));
        dashboardList.add(new dashboard(R.drawable.allergy, "Food Allergy"));
        dashboardList.add(new dashboard(R.drawable.contactvcimg, "Contact Us"));


        adapter.notifyDataSetChanged();
    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    public int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}


