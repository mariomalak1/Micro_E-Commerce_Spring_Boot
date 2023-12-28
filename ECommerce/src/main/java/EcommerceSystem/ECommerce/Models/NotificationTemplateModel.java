package EcommerceSystem.ECommerce.Models;

public abstract class NotificationTemplateModel {
    protected String subject;
    protected String content;
    protected String[] langauges;
    protected String[] placeHolders;
    protected Customer customerAccount;
    protected Product[] product;

    public NotificationTemplateModel(Customer customerAccount, Product[] product) {
        this.customerAccount = customerAccount;
        this.product = product;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getLangauges() {
        return langauges;
    }

    public void setLangauges(String[] langauges) {
        this.langauges = langauges;
    }

    public String[] getPlaceHolders() {
        return placeHolders;
    }

    public void setPlaceHolders(String[] placeHolders) {
        this.placeHolders = placeHolders;
    }
}
