package hu.szte.mobilalkfejl.model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class SalesLead {

    public SalesLead() {
    }

    public SalesLead(String name, SalesLeadPriorityType priority, float rating, SalesLeadStateType status, String type) {
        this(null, name, priority, rating, status, type);
    }

    public SalesLead(String id, String name, SalesLeadPriorityType priority, float rating, SalesLeadStateType status, String type) {
        if (id == null) {
            this.id = UUID.randomUUID().toString();
        } else {
            this.id = id;
        }
        this.name = name;
        this.priority = priority;
        this.rating = rating;
        this.status = status;
        this.type = type;
        creationDate = new Date();
        referredDate = new Date();
        statusChangeDate = new Date();
        validFor = new Date();
    }

    private CategoryRef category;
    private ChannelRef channel;
    private Date creationDate;
    private String description;
    private Money estimatedRevenue;
    private String href;
    private String id;
    private MarketSegmentRef marketSegment;
    private MarketingCampaignRef marketingCampaign;
    private String name;
    private Note[] note;
    private SalesLeadPriorityType priority;
    private ProductRef product;
    private ProductOfferingRef productOffering;
    private ProductSpecificationRef productSpecification;
    private ContactMedium[] prospectContact;
    private float rating;
    private Date referredDate;
    private RelatedParty[] relatedParty;
    private SalesOpportunityRef salesOpportunity;
    private SalesLeadStateType status;
    private Date statusChangeDate;
    private String statusChangeReason;
    private String type;
    private Date validFor;

    public CategoryRef getCategory() {
        return category;
    }

    public void setCategory(CategoryRef category) {
        this.category = category;
    }

    public ChannelRef getChannel() {
        return channel;
    }

    public void setChannel(ChannelRef channel) {
        this.channel = channel;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Money getEstimatedRevenue() {
        return estimatedRevenue;
    }

    public void setEstimatedRevenue(Money estimatedRevenue) {
        this.estimatedRevenue = estimatedRevenue;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MarketSegmentRef getMarketSegment() {
        return marketSegment;
    }

    public void setMarketSegment(MarketSegmentRef marketSegment) {
        this.marketSegment = marketSegment;
    }

    public MarketingCampaignRef getMarketingCampaign() {
        return marketingCampaign;
    }

    public void setMarketingCampaign(MarketingCampaignRef marketingCampaign) {
        this.marketingCampaign = marketingCampaign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Note[] getNote() {
        return note;
    }

    public void setNote(Note[] note) {
        this.note = note;
    }

    public SalesLeadPriorityType getPriority() {
        return priority;
    }

    public void setPriority(SalesLeadPriorityType priority) {
        this.priority = priority;
    }

    public ProductRef getProduct() {
        return product;
    }

    public void setProduct(ProductRef product) {
        this.product = product;
    }

    public ProductOfferingRef getProductOffering() {
        return productOffering;
    }

    public void setProductOffering(ProductOfferingRef productOffering) {
        this.productOffering = productOffering;
    }

    public ProductSpecificationRef getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(ProductSpecificationRef productSpecification) {
        this.productSpecification = productSpecification;
    }

    public ContactMedium[] getProspectContact() {
        return prospectContact;
    }

    public void setProspectContact(ContactMedium[] prospectContact) {
        this.prospectContact = prospectContact;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Date getReferredDate() {
        return referredDate;
    }

    public void setReferredDate(Date referredDate) {
        this.referredDate = referredDate;
    }

    public RelatedParty[] getRelatedParty() {
        return relatedParty;
    }

    public void setRelatedParty(RelatedParty[] relatedParty) {
        this.relatedParty = relatedParty;
    }

    public SalesOpportunityRef getSalesOpportunity() {
        return salesOpportunity;
    }

    public void setSalesOpportunity(SalesOpportunityRef salesOpportunity) {
        this.salesOpportunity = salesOpportunity;
    }

    public SalesLeadStateType getStatus() {
        return status;
    }

    public void setStatus(SalesLeadStateType status) {
        this.status = status;
    }

    public Date getStatusChangeDate() {
        return statusChangeDate;
    }

    public void setStatusChangeDate(Date statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }

    public String getStatusChangeReason() {
        return statusChangeReason;
    }

    public void setStatusChangeReason(String statusChangeReason) {
        this.statusChangeReason = statusChangeReason;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getValidFor() {
        return validFor;
    }

    public void setValidFor(Date validFor) {
        this.validFor = validFor;
    }
}
