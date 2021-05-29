package hu.szte.mobilalkfejl.dao;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import hu.szte.mobilalkfejl.MainActivity;
import hu.szte.mobilalkfejl.model.SalesLead;

public class SalesLeadDAOImpl implements SalesLeadDAO{

    private static final String TAG = MainActivity.class.getName();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final String COLLECTION = "salesLeads";
    private Context context;

    public SalesLeadDAOImpl(Context context) {
        this.context = context;
    }

    @Override
    public void saveSalesLead(SalesLead salesLead) {
       db.collection(COLLECTION).document(salesLead.getId()).set(salesLead).addOnCompleteListener(task -> {
           if (task.isSuccessful()) {
               Log.w(TAG, "Successful add!", task.getException());
           } else {
               Log.w(TAG, "Error getting documents.", task.getException());
           }
       });
    }

    @Override
    public SalesLead getSalesLead(String id) {
        return Objects.requireNonNull(db.collection(COLLECTION).document(id).get().getResult()).toObject(SalesLead.class);
    }

    @Override
    public void removeSalesLead(String id) {
        db.collection(COLLECTION).document(id).delete();
    }

    @Override
    public void editSalesLead(String id, SalesLead salesLead) {
        removeSalesLead(id);
        saveSalesLead(salesLead);
    }

    @Override
    public void getSalesLeads(OnCompleteListener<QuerySnapshot> onCompleteListener) {
        db.collection(COLLECTION).get().addOnCompleteListener(onCompleteListener);
    }
}
