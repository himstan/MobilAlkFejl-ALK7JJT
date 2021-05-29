package hu.szte.mobilalkfejl.dao;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.QuerySnapshot;

import hu.szte.mobilalkfejl.model.SalesLead;

public interface SalesLeadDAO {

    void saveSalesLead(SalesLead salesLead);
    SalesLead getSalesLead(String id);
    void removeSalesLead(String id);
    void editSalesLead(String id, SalesLead salesLead);
    void getSalesLeads(OnCompleteListener<QuerySnapshot> onCompleteListener);

}
