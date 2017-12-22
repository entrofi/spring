package net.entrofi.samples.jee.spring.batch.business.batch;

public class TagMappingItem {

    private String tag;

    private String productId;

    private String productName;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "TagMappingItem{" +
                "tag='" + tag + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }
}
