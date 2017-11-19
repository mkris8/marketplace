
package com.sedex.marketplace.model.json;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Product {

    @SerializedName("Product code")
    @Expose
    private String ProductCode;
    @SerializedName("Name")
    @Expose
    private String Name;
    @SerializedName("Price")
    @Expose
    private String Price;

    public Product(String code, String productName, String string) {

    	this.ProductCode = String.valueOf(code);
    	this.Name = productName;
    	this.Price = String.valueOf(string);
    }

	/**
     * 
     * @return
     *     The ProductCode
     */
    public String getProductCode() {
        return ProductCode;
    }

    /**
     * 
     * @param ProductCode
     *     The Product code
     */
    public void setProductCode(String ProductCode) {
        this.ProductCode = ProductCode;
    }

    /**
     * 
     * @return
     *     The Name
     */
    public String getName() {
        return Name;
    }

    /**
     * 
     * @param Name
     *     The Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * 
     * @return
     *     The Price
     */
    public String getPrice() {
        return Price;
    }

    /**
     * 
     * @param Price
     *     The Price
     */
    public void setPrice(String Price) {
        this.Price = Price;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(ProductCode).append(Name).append(Price).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Product) == false) {
            return false;
        }
        Product rhs = ((Product) other);
        return new EqualsBuilder().append(ProductCode, rhs.ProductCode).append(Name, rhs.Name).append(Price, rhs.Price).isEquals();
    }

}
