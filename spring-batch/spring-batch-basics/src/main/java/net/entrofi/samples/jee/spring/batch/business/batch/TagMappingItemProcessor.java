package net.entrofi.samples.jee.spring.batch.business.batch;


import net.entrofi.samples.jee.spring.batch.business.model.Merchant;
import net.entrofi.samples.jee.spring.batch.business.model.Product;
import net.entrofi.samples.jee.spring.batch.business.model.ProductTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


@Component
public class TagMappingItemProcessor implements ItemProcessor<TagMappingItem, ProductTag> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TagMappingItemProcessor.class);

    private EntityManager entityManager;

    public TagMappingItemProcessor(@Autowired EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public ProductTag process(TagMappingItem item) throws Exception {
        Merchant merchant = this.entityManager.find(Merchant.class, 1L);
        ProductTag tag = new ProductTag(item.getTag(), merchant);
        Product product = new Product(item.getProductId(), merchant);
        product.setProductName(item.getProductName());
        tag.getProducts().add(product);
        LOGGER.info("Finished mapping tag mapping item {}. Entities tag {} and product {} are created.",
                item, tag, product);
        return tag;
    }
}
