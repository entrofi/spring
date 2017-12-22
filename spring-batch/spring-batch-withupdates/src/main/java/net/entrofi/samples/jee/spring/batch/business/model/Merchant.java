package net.entrofi.samples.jee.spring.batch.business.model;
// Generated Oct 5, 2017 4:06:22 PM by Hibernate Tools 3.2.4.GA


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Merchant implements java.io.Serializable {


    @Id
    @Column(name = "ID", unique = true, nullable = false, precision = 38, scale = 0)
    private Long id;

    @Column(name = "NAME")
    private String name;


    @OneToMany(mappedBy = "id.merchant")
    private Set<Product> products = new HashSet<>(0);

    @OneToMany(mappedBy = "id.merchant")
    private Set<ProductTag> productTags = new HashSet<>();

    public Merchant() {
    }


    public Merchant(final Long id) {
        this.id = id;
    }



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<ProductTag> getProductTags() {
        return productTags;
    }

    public void setProductTags(Set<ProductTag> productTags) {
        this.productTags = productTags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Merchant merchant = (Merchant) o;
        
        return new EqualsBuilder()
                .append(id, merchant.id)
                .append(name, merchant.name)
                .isEquals();
    }
    

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }
}


