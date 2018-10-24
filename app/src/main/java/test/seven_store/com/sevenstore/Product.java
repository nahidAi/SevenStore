package test.seven_store.com.sevenstore;


import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;

import com.google.gson.annotations.SerializedName;

public class Product {

    public static final int STATUS_EXSTS = 1;
    public static final int STATUS_NOT_EXSTS = 0;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String title;
    @SerializedName("image")
    private String imageUrl;
    @SerializedName("price_previous")
    private String previousPrice;
    @SerializedName("price_current")
    private String currentPrice;
    @SerializedName("status")
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public SpannableString getPreviusPrice() {
        if (status == STATUS_EXSTS) {
            SpannableString spannableString = new SpannableString(NumberConverter.convertToFarsiNumbers(previousPrice));
            spannableString.setSpan(new StrikethroughSpan(),0,previousPrice.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
        } else {
            return new SpannableString("ناموجود");
        }

    }

    public void setPreviusPrice(String previusPrice) {
        this.previousPrice = previusPrice;
    }

    public String getCurrentPrice() {

        return NumberConverter.convertToFarsiNumbers(currentPrice) ;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
