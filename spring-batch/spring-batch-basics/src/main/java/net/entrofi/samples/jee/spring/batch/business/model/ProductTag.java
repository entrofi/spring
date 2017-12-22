package net.entrofi.samples.jee.spring.batch.business.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "PRODUCT_TAG")
public class ProductTag implements Serializable {


    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ProductTagId id;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, targetEntity = Product.class)
    @JoinTable(name = "PRODUCT_PRODUCT_TAG",
            joinColumns = {
                    @JoinColumn(name = "TAGS_TAG", referencedColumnName = "TAG"),
                    @JoinColumn(name = "TAGS_MERCHANT_ID", referencedColumnName = "MERCHANT_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "PRODUCT_MERCHANT_ID", referencedColumnName = "MERCHANT_ID"),
                    @JoinColumn(name = "PRODUCT_PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
            }
    )
    private Set<Product> products = new HashSet<>();

    public ProductTag() {

    }

    public ProductTag(ProductTagId id) {
        this.id = id;
    }
    public ProductTag(final String tag, final Merchant merchant) {
        this.setId(new ProductTagId(tag, merchant));
    }

    public ProductTagId getId() {
        return id;
    }

    public void setId(final ProductTagId id) {
        this.id = id;
    }

    @Transient
    public String getTag() {
        return id != null ? id.getTag() : null;
    }

    @Transient
    public Merchant getMerchant() {
        return id != null ? id.getMerchant() : null;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(final Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductTag that = (ProductTag) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
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
        return "ProductTag{"
                + "id=" + id
                + '}';
    }

    public static class ProductTagId implements Serializable {
        private static final long serialVersionUID = 1L;

        @NotNull
        @Column(name = "TAG", nullable = false)
        private String tag;

        @NotNull
        @ManyToOne
        @JoinColumn(name = "MERCHANT_ID", referencedColumnName = "ID")
        private Merchant merchant;

        public ProductTagId() {
        }

        public ProductTagId(final String tag, final Merchant merchant) {
            this.tag = tag;
            this.merchant = merchant;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(final String tag) {
            this.tag = tag;
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

            ProductTagId that = (ProductTagId) o;

            return new EqualsBuilder()
                    .append(getTag(), that.getTag())
                    .append(getMerchant(), that.getMerchant())
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(getTag())
                    .append(getMerchant())
                    .toHashCode();
        }

        @Override
        public String toString() {
            return "ProductTagId{" +
                    "tag='" + tag + '\'' +
                    ", merchant=" + merchant +
                    '}';
        }
    }
}
