package net.entrofi.samples.jee.spring.batch.business.model;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {


    private static final long serialVersionUID = 1L;


    @EmbeddedId
    private ProductId id;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String productName;

    @ManyToMany
    private Set<ProductTag> tags = new HashSet<>();

    public Product() {

    }

    public Product(final ProductId productId) {
        this.id = productId;
    }

    public Product(final String externalProductId, final Merchant merchant) {
        this.id = new ProductId(merchant, externalProductId);
    }

    public ProductId getId() {
        return id;
    }

    public void setId(final ProductId id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public Set<ProductTag> getTags() {
        return tags;
    }

    public void setTags(final Set<ProductTag> tags) {
        this.tags = tags;
    }

    @Transient
    public Merchant getMerchant() {
        return this.id != null ? id.merchant : null;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product product = (Product) o;

        return new EqualsBuilder()
                .append(getId(), product.getId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Product{"
                + "id=" + id
                + ", productName='" + productName + '\''
                + ", tags=" + tags
                + '}';
    }

    @Embeddable
    public static class ProductId implements Serializable {

        private static final long serialVersionUID = 1L;

        @NotNull
        @Column(name = "PRODUCT_ID", nullable = false)
        private String productId;


        @NotNull
        @ManyToOne
        @JoinColumn(name = "MERCHANT_ID", referencedColumnName = "ID")
        private Merchant merchant;

        public ProductId() {

        }

        public ProductId(final String productId, final Merchant merchant) {
            this.productId = productId;
            this.merchant = merchant;
        }

        public ProductId(final Merchant merchant, final String productId) {
            this.merchant = merchant;
            this.productId = productId;
        }

        public long getSerialVersionUID() {
            return serialVersionUID;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(final String productId) {
            this.productId = productId;
        }

        public Merchant getMerchant() {
            return merchant;
        }

        public void setMerchant(final Merchant merchant) {
            this.merchant = merchant;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            ProductId other = (ProductId) o;

            return new EqualsBuilder()
                    .append(productId, other.productId)
                    .append(merchant, other.merchant)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(productId)
                    .append(merchant)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return "ProductId{"
                    + "productId='" + productId + '\''
                    + ", merchant=" + merchant
                    + '}';
        }
    }


}
