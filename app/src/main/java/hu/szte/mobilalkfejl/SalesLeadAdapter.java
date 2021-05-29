package hu.szte.mobilalkfejl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hu.szte.mobilalkfejl.dao.SalesLeadDAO;
import hu.szte.mobilalkfejl.dao.SalesLeadDAOImpl;
import hu.szte.mobilalkfejl.model.SalesLead;

public class SalesLeadAdapter extends RecyclerView.Adapter<SalesLeadAdapter.ViewHolder> implements Filterable {

    private SalesLeadDAO salesLeadDAO;
    private List<SalesLead> mSalesLeadData;
    private List<SalesLead> mSalesLeadDataAll;
    private Context mContext;
    private NotificationHelper notificationHelper;
    private int lastPosition = -1;

    public SalesLeadAdapter(Context context, List<SalesLead> itemsData) {
        this.mSalesLeadData = itemsData;
        this.mSalesLeadDataAll = itemsData;
        this.mContext = context;
        this.salesLeadDAO =  new SalesLeadDAOImpl(context);
        this.notificationHelper = new NotificationHelper(context);
    }

    @Override
    public Filter getFilter() {
        return shoppingFilter;
    }

    private Filter shoppingFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<SalesLead> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if(charSequence == null || charSequence.length() == 0) {
                results.count = mSalesLeadDataAll.size();
                results.values = mSalesLeadDataAll;
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(SalesLead item : mSalesLeadDataAll) {
                    if(item.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mSalesLeadData = (ArrayList)filterResults.values;
            notifyDataSetChanged();
        }
    };

    @NonNull
    @Override
    public SalesLeadAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.sales_lead_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SalesLeadAdapter.ViewHolder holder, int position) {
        SalesLead currentItem = mSalesLeadData.get(position);
        holder.bindTo(currentItem);

        if(holder.getAdapterPosition() > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_row);
            holder.itemView.startAnimation(animation);
            lastPosition = holder.getAdapterPosition();
        }

        holder.deleteButton.setOnClickListener(view -> {
            mSalesLeadData.remove(currentItem);
            salesLeadDAO.removeSalesLead(currentItem.getId());
            notificationHelper.send(currentItem.getName() + " has been removed!");
            notifyDataSetChanged();
        });

        holder.modifyButton.setOnClickListener(view -> {
            Intent changeSalesLead = new Intent(mContext, ModifyActivity.class);
            changeSalesLead.putExtra("id", currentItem.getId());
            changeSalesLead.putExtra("name", currentItem.getName());
            changeSalesLead.putExtra("type", currentItem.getType());
            changeSalesLead.putExtra("rating", currentItem.getRating());
            changeSalesLead.putExtra("priority", currentItem.getPriority().toString());
            changeSalesLead.putExtra("status", currentItem.getStatus().toString());
            mContext.startActivity(changeSalesLead);
        });
    }

    @Override
    public int getItemCount() {
        return mSalesLeadData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitleText;
        private TextView mTypeText;
        private TextView mStatusText;
        private TextView mPriorityText;
        private RatingBar mRating;
        private Button deleteButton;
        private Button modifyButton;
        ViewHolder(View itemView) {
            super(itemView);
            mTitleText = itemView.findViewById(R.id.viewSalesLeadName);
            mTypeText = itemView.findViewById(R.id.viewSalesLeadType);
            mStatusText = itemView.findViewById(R.id.viewSalesLeadStatus);
            mPriorityText = itemView.findViewById(R.id.viewSalesLeadPriority);
            mRating = itemView.findViewById(R.id.viewSalesLeadRating);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            modifyButton = itemView.findViewById(R.id.modifyButton);
        }

        @SuppressLint("SetTextI18n")
        void bindTo(SalesLead currentItem){
            mTitleText.setText(currentItem.getName());
            mRating.setRating(currentItem.getRating());
            mTypeText.setText("Típus: " + currentItem.getType());
            mStatusText.setText("Státusz: " + currentItem.getStatus().toString());
            mPriorityText.setText("Prioritás: " + currentItem.getPriority());
        }
    }
}
