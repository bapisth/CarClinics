package com.urja.carclinics.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.urja.carclinics.R;
import com.urja.carclinics.database.ServiceRequest;
import com.urja.carclinics.fragment.TransactionListActivityFragment.OnListFragmentInteractionListener;
import com.urja.carclinics.fragment.dummy.DummyContent.DummyItem;
import com.urja.carclinics.model.Transaction;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class TransactionDetailRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = TransactionDetailRecyclerViewAdapter.class.getSimpleName();
    private final List<Transaction> mValues;
    private static final int VIEW_TYPE_EMPTY_LIST_PLACEHOLDER = 0;
    private static final int VIEW_TYPE_OBJECT_VIEW = 1;
    private static final String MSG_TOTAL_AMOUNT = "Total Amount: Rs. ";
    private Context mContext;
    private String mTransactionId;

    public TransactionDetailRecyclerViewAdapter(List<Transaction> items, Context context) {
        this.mValues = items;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TransactionViewHolder transactionViewHolder = null;

        RecyclerView.ViewHolder viewHolder = null;
        View view = null;
        switch (viewType){
            case VIEW_TYPE_EMPTY_LIST_PLACEHOLDER:
                view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
                viewHolder = new EmptyTransactionViewHolder(view);
                break;
            case VIEW_TYPE_OBJECT_VIEW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_orderforservicestransaction, parent, false);// fragment_item
                viewHolder = new TransactionViewHolder(view);
                break;
        }
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case VIEW_TYPE_OBJECT_VIEW:
                TransactionViewHolder transactionViewHolder = (TransactionViewHolder) holder;
                Transaction transaction = mValues.get(position);
                transactionViewHolder.mItem = transaction;

                List<ServiceRequest> serviceRequestList = transaction.getServiceRequestList();
                List<ServiceRequest> requests = serviceRequestList;
                int totalAmount = 0;
                if (requests != null){
                    for (ServiceRequest request : requests){
                        int val = Integer.valueOf(request.getVehiclegroup());
                        totalAmount +=val;
                    }
                }

                //holder.mIdView.setText(mValues.get(position).id);
                //holder.mContentView.setText(mValues.get(position).content);
                if (serviceRequestList!= null){
                    String carNumber = serviceRequestList.get(0).getCarnumber();
                    transactionViewHolder.mCarNumber.setText(carNumber);
                }else {
                    transactionViewHolder.mCarNumber.setText("N/A");
                }

                mTransactionId = transaction.getTransactionId();
                transactionViewHolder.mTransactionID.setText(mTransactionId);

                transactionViewHolder.mTransactionDate.setText(transaction.getServiceRequestDate());
                String requestStatus = transaction.getRequestStatus();
                if (requestStatus != "" && requestStatus != null){
                    transactionViewHolder.mTransactionStatus.setText(requestStatus.toUpperCase());
                }else {
                    transactionViewHolder.mTransactionStatus.setText("N/A");
                }
                transactionViewHolder.mTotalAmount.setText(MSG_TOTAL_AMOUNT+totalAmount);
                break;
            case VIEW_TYPE_EMPTY_LIST_PLACEHOLDER:
                EmptyTransactionViewHolder emptyTransactionViewHolder = (EmptyTransactionViewHolder) holder;
                emptyTransactionViewHolder.mEmptyText.setText(mContext.getString(R.string.empty_data));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mValues.isEmpty()) {
            return VIEW_TYPE_EMPTY_LIST_PLACEHOLDER;
        } else {
            return VIEW_TYPE_OBJECT_VIEW;
        }
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mCarNumber;
        public final TextView mTransactionID;
        public final TextView mTransactionDate;
        public final TextView mTransactionStatus;
        public final TextView mTotalAmount;
        public Transaction mItem;
        public final CardView mTransactionIdContainer;

        public TransactionViewHolder(View view) {
            super(view);
            mView = view;
            mCarNumber = (TextView) view.findViewById(R.id.carNumber);
            mTransactionID = (TextView) view.findViewById(R.id.transactionID);
            mTransactionDate = (TextView) view.findViewById(R.id.transactionDate);
            mTransactionStatus = (TextView) view.findViewById(R.id.transactionStatus);
            mTotalAmount = (TextView) view.findViewById(R.id.totalAmount);
            mTransactionIdContainer = (CardView) view.findViewById(R.id.transactionContainer);
        }
    }

    public class EmptyTransactionViewHolder extends RecyclerView.ViewHolder {

        private final TextView mEmptyText;
        public EmptyTransactionViewHolder(View itemView) {
            super(itemView);
            mEmptyText = (TextView) itemView.findViewById(R.id.no_data);
        }
    }
}
