package exousiatech.newpracticeapp.Get_Call_Logs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import exousiatech.newpracticeapp.R;

/**
 * Created by Admin on 06-11-2017.
 */

public class Call_Logs_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<CallLogsPojo> list;

    public Call_Logs_Adapter(Context context, List<CallLogsPojo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.call_logs_view,parent,false);
        return new Call_logs_View(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Call_logs_View){
            ((Call_logs_View) holder).phoneNumber.setText(list.get(position).getPhoneNumber());
            ((Call_logs_View) holder).time.setText(list.get(position).getDate());
            ((Call_logs_View) holder).type.setText(list.get(position).getType());
            ((Call_logs_View) holder).duration.setText(list.get(position).getDuration());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Call_logs_View extends RecyclerView.ViewHolder{
        TextView phoneNumber,type,time,duration;

        public Call_logs_View(View itemView) {
            super(itemView);
            phoneNumber = (TextView)itemView.findViewById(R.id.phoneNumber);
            type = (TextView)itemView.findViewById(R.id.type);
            time = (TextView)itemView.findViewById(R.id.time);
            duration = (TextView)itemView.findViewById(R.id.duration);
        }
    }
}
