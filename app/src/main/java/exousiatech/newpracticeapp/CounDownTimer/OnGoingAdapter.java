package exousiatech.newpracticeapp.CounDownTimer;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.List;
import exousiatech.newpracticeapp.Modals.BlogResponseData;
import exousiatech.newpracticeapp.R;
import exousiatech.newpracticeapp.Utils.CommonMethods;

/**
 * Created by Admin on 03-11-2017.
 */

public class OnGoingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<BlogResponseData> list;
    Handler handler;
    Runnable runnable;

    public OnGoingAdapter(Context context, List<BlogResponseData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 1) {
            view = LayoutInflater.from(context).inflate(R.layout.timer_view, parent, false);
            return new OnGoingView(view);
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.custom_loading_layout, parent, false);
            return new CustomViewHolderLoading(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position) == null) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OnGoingView){
            CommonMethods.countDownStart(handler,runnable,((OnGoingView) holder).tvDay,((OnGoingView) holder).tvHour,((OnGoingView) holder).tvMinute,
                    ((OnGoingView) holder).tvSecond,list.get(position).getIco_end());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class OnGoingView extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvDay, tvHour, tvMinute, tvSecond, tvEvent;
        LinearLayout linearLayout1, linearLayout2;
        private Handler handler;
        private Runnable runnable;

        public OnGoingView(View itemView) {
            super(itemView);
            linearLayout1 = (LinearLayout)itemView.findViewById(R.id.ll1);
            linearLayout2 = (LinearLayout)itemView.findViewById(R.id.ll2);
            tvDay = (TextView)itemView.findViewById(R.id.txtTimerDay);
            tvHour = (TextView)itemView.findViewById(R.id.txtTimerHour);
            tvMinute = (TextView)itemView.findViewById(R.id.txtTimerMinute);
            tvSecond = (TextView)itemView.findViewById(R.id.txtTimerSecond);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
//                case R.id.mainLayout:
//                    Intent intent = new Intent(context, OnGoingDetails.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                    intent.putExtra(MyConstants.Data,list.get(getAdapterPosition()));
//                    context.startActivity(intent);
//                    break;
            }
        }
    }
    public class CustomViewHolderLoading extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        public CustomViewHolderLoading(View itemView) {
            super(itemView);
            progressBar = (ProgressBar)itemView.findViewById(R.id.progress_bar);
            progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(context,R.color.black), PorterDuff.Mode.MULTIPLY);
        }
    }
}
