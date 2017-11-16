package exousiatech.newpracticeapp.CounDownTimer;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import exousiatech.newpracticeapp.Modals.BlogResponseData;
import exousiatech.newpracticeapp.Modals.HomeResponse;
import exousiatech.newpracticeapp.R;
import exousiatech.newpracticeapp.Utils.CommonMethods;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CounDownTimerActivity extends AppCompatActivity {
    String TAG = "CommonMethods";
    boolean retrofitLoading = false, last = true, isRefresh = false;
    List<BlogResponseData> list;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    OnGoingAdapter adapter;
    int dataLimit = 5;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coun_down_timer);
        list = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        layoutManager = new LinearLayoutManager(CounDownTimerActivity.this,LinearLayoutManager.VERTICAL,false);
        adapter = new OnGoingAdapter(CounDownTimerActivity.this,list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        demoRetrofit();
    }

    public void demoRetrofit() {
        retrofitLoading = true;
        Call<HomeResponse> call = null;
        if (isRefresh) {
            call = CommonMethods.getRetrofit().onGoing(dataLimit, 0);
        } else {
            if (list.size() > 0) {
                Log.e(TAG, "Size 0 se jyada h " + list.size());
                call = CommonMethods.getRetrofit().onGoing(dataLimit, list.size() - 1);
            } else {
                Log.e(TAG, "Size 0 h " + list.size());
                call = CommonMethods.getRetrofit().onGoing(dataLimit, 0);
            }
        }
        if (call != null) {
            call.enqueue(new Callback<HomeResponse>() {
                @Override
                public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                    swipeRefreshLayout.setRefreshing(false);
                    if (response.body() != null) {
                        Log.e(TAG, "Response khaali nhi h ");
                        switch (response.body().getStatus()) {
                            case 1:
                                if (isRefresh) {
                                    adapter.notifyItemRangeRemoved(0, list.size());
                                    list.clear();
                                    isRefresh = false;
                                }
                                if (list.size() > 0) {
                                    Log.e(TAG, "Size 0 se jyada h " + list.size());
                                    list.remove(list.size() - 1);
                                    adapter.notifyItemRemoved(list.size());
                                }
                                list.addAll(response.body().getResponse());
                                Log.e(TAG, "Affter adding list size  " + list.size());
                                adapter.notifyItemRangeChanged(list.size() - response.body().getResponse().size(), response.body().getResponse().size());
                                if (response.body().getResponse().size() < dataLimit) {
                                   Log.e(TAG,"last chal gya");
                                    Log.e(TAG, "Size 10 se kmmmm h " + list.size());
                                    last = false;
                                } else {
                                    Log.e(TAG, "Size 10 se jyada h " + list.size());
                                    last = true;
                                }
                                break;
                        }
                    } else {
                        Log.e(TAG,"Data khali hai ");
                    }
                    retrofitLoading = false;
                }

                @Override
                public void onFailure(Call<HomeResponse> call, Throwable t) {
                   Log.e(TAG,"Failure " + t.getMessage());
                    retrofitLoading = false;
                }
            });
        }
    }

    public void inItScroll() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                getMoreData();
            }
        });
    }

    public void getMoreData() {
        int visible = layoutManager.getChildCount();
        int total = layoutManager.getItemCount();
        int firstVisible = layoutManager.findFirstVisibleItemPosition();
        if (visible + firstVisible >= total && !retrofitLoading) {
            if (last) {
                try {
                    insertFakeEntry();
                    demoRetrofit();
                } catch (Exception e) {
                    Log.e("exception", e.getMessage());
                }
            }
        }
    }

    private void insertFakeEntry() {
        try {
            Log.e(TAG, "fake entry ke ander aaya ");
            list.add(null);
            adapter.notifyItemInserted(list.size() - 1);
        } catch (Exception e) {
            Log.e("exception", e.getMessage());
        }
    }
}
