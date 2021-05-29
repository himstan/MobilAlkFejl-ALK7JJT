package hu.szte.mobilalkfejl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import hu.szte.mobilalkfejl.dao.SalesLeadDAO;
import hu.szte.mobilalkfejl.dao.SalesLeadDAOImpl;
import hu.szte.mobilalkfejl.model.SalesLead;

public class MainActivity extends AppCompatActivity {

    private int gridNumber = 1;
    private RecyclerView mRecyclerView;
    private List<SalesLead> mItemsData;

    private SalesLeadAdapter mAdapter;
    private SalesLeadDAO salesLeadDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        salesLeadDAO = new SalesLeadDAOImpl(this);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridNumber));
        queryData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdapter != null) {
            queryData();
        }
    }

    private void queryData() {
        mItemsData = new ArrayList<>();
        salesLeadDAO.getSalesLeads(task -> {
            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                SalesLead salesLead = document.toObject(SalesLead.class);
                mItemsData.add(salesLead);
            }
            mAdapter = new SalesLeadAdapter(this, mItemsData);
            mRecyclerView.setAdapter(mAdapter);
        });
    }

    public void addSalesLead(View view) {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }
}