package hu.szte.mobilalkfejl;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.stream.Collectors;

import hu.szte.mobilalkfejl.dao.SalesLeadDAO;
import hu.szte.mobilalkfejl.dao.SalesLeadDAOImpl;
import hu.szte.mobilalkfejl.model.SalesLead;
import hu.szte.mobilalkfejl.model.SalesLeadPriorityType;
import hu.szte.mobilalkfejl.model.SalesLeadStateType;

public class CreateActivity extends AppCompatActivity {

    SalesLeadDAO salesLeadDAO;

    NotificationHelper notificationHelper;

    EditText leadNameText;
    EditText leadTypeText;
    RatingBar leadRatingBar;
    Spinner leadStatusSpinner;
    Spinner leadPrioritySpinner;

    TextView errorText;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        notificationHelper = new NotificationHelper(this);
        salesLeadDAO = new SalesLeadDAOImpl(this);

        leadNameText = findViewById(R.id.salesLeadName);
        leadTypeText = findViewById(R.id.salesLeadType);
        leadRatingBar = findViewById(R.id.salesLeadRating);
        leadStatusSpinner = findViewById(R.id.salesLeadStatus);
        leadPrioritySpinner = findViewById(R.id.salesLeadPriority);

        setupSpinners();

        errorText = findViewById(R.id.errorText);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("ResourceType")
    private void setupSpinners() {
        String[] statusValues = Arrays.stream(SalesLeadStateType.values()).map(Enum::toString).toArray(String[]::new);
        String[] priorityValues = Arrays.stream(SalesLeadPriorityType.values()).map(Enum::toString).toArray(String[]::new);
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, statusValues);
        ArrayAdapter<String> priorityAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, priorityValues);
        leadStatusSpinner.setAdapter(statusAdapter);
        leadPrioritySpinner.setAdapter(priorityAdapter);
    }

    public void addSalesLead(View view) {
        String leadName = getLeadName();
        String leadType = getLeadType();
        SalesLeadStateType leadStatus = getLeadStatus();
        SalesLeadPriorityType leadPriority = getLeadPriority();
        float leadRating = getLeadRating();

        if (validateSalesLead()) {
            SalesLead salesLead = new SalesLead(leadName, leadPriority, leadRating, leadStatus, leadType);
            salesLeadDAO.saveSalesLead(salesLead);
            notificationHelper.send(leadName + " has been added!");
            finish();
        }
    }

    private void setErrorText(String error) {
        this.errorText.setText(error);
    }

    private boolean validateSalesLead() {
        if (getLeadName().isEmpty()) {
            setErrorText("Kérled add meg a Sales Lead nevét");
            return false;
        }
        if (getLeadType().isEmpty()) {
            setErrorText("Kérled add meg a Sales Lead típusát");
            return false;
        }
        if (getLeadRating() == 0) {
            setErrorText("Kérled add meg a Sales Lead értékelést");
            return false;
        }
        setErrorText("");
        return true;
    }

    private String getLeadName() {
        return leadNameText.getText().toString();
    }

    private String getLeadType() {
        return leadTypeText.getText().toString();
    }

    private float getLeadRating() {
        return leadRatingBar.getRating();
    }

    private SalesLeadStateType getLeadStatus() {
        SalesLeadStateType status;
        try {
            String value = leadStatusSpinner.getSelectedItem().toString();
            status = SalesLeadStateType.valueOf(value);
        } catch (IllegalArgumentException | NullPointerException exception) {
            return SalesLeadStateType.pending;
        }
        return status;
    }

    private SalesLeadPriorityType getLeadPriority() {
        SalesLeadPriorityType priority;
        try {
            String value = leadPrioritySpinner.getSelectedItem().toString();
            priority = SalesLeadPriorityType.valueOf(value);
        } catch (IllegalArgumentException | NullPointerException exception) {
            return SalesLeadPriorityType.low;
        }
        return priority;
    }

}