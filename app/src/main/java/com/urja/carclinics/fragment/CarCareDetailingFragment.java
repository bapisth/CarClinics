package com.urja.carclinics.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.urja.carclinics.R;
import com.urja.carclinics.adapters.CarServiceRecyclerViewAdapter;
import com.urja.carclinics.database.CarServicePrice;
import com.urja.carclinics.database.DbHelper;
import com.urja.carclinics.database.dao.CarServicePriceDao;
import com.urja.carclinics.utils.DatabaseConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class CarCareDetailingFragment extends Fragment {

    static final boolean GRID_LAYOUT = false;
    private static final String TAG = CarCareDetailingFragment.class.getSimpleName();
    private static final int ITEM_COUNT = 100;
    private static String CAR_TYPE = "";
    private static String CAR_TYPE_NAME = "";
    public RecyclerView.Adapter mAdapter;
    CarServicePriceDao carServicePriceDao = null;
    private RecyclerView mRecyclerView;
    private List<CarServicePrice> mContentItems = new ArrayList<>();
    private DatabaseReference mDatabaseRootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference mCarServiceWashTypesRef = mDatabaseRootRef.child(DatabaseConstants.TABLE_CAR_SERVICE + "/" + DatabaseConstants.CAR_CARE_DETAILING);
    private FirebaseAuth firebaseAuth;

    public static CarCareDetailingFragment newInstance(String carType, String mCarTypeName) {
        CarCareDetailingFragment.CAR_TYPE = carType;
        CarCareDetailingFragment.CAR_TYPE_NAME = mCarTypeName;
        return new CarCareDetailingFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carServicePriceDao = DbHelper.getInstance(getActivity()).getCarServicePriceDao();
        mContentItems = carServicePriceDao.loadAll();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager;

        if (GRID_LAYOUT) {
            layoutManager = new GridLayoutManager(getActivity(), 2);
        } else {
            layoutManager = new LinearLayoutManager(getActivity());
        }
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        //Use this now
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mAdapter = new CarServiceRecyclerViewAdapter(mContentItems, getActivity(), CAR_TYPE, CAR_TYPE_NAME);

        //mAdapter = new RecyclerViewMaterialAdapter();
        mRecyclerView.setAdapter(mAdapter);


        //fetchServiceData(this);


    }

    private void fetchServiceData(CarCareDetailingFragment carCareDetailingFragment) {
        mContentItems = carServicePriceDao.loadAll();
        mAdapter.notifyDataSetChanged();
    }
}
