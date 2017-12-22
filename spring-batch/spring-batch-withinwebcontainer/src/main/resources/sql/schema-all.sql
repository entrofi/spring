DROP TABLE IF EXISTS PRODUCT;
DROP TABLE IF EXISTS PRODUCT_TAG;
DROP TABLE IF EXISTS PRODUCT_PRODUCT_TAG;
DROP TABLE IF EXISTS MERCHANT;

CREATE TABLE MERCHANT
(
  ID BIGINT PRIMARY KEY AUTO_INCREMENT ,
  NAME VARCHAR(255) NOT NULL
);

CREATE TABLE PRODUCT_TAG
(
  TAG         VARCHAR(255) NOT NULL,
  MERCHANT_ID BIGINT,
  CONSTRAINT PK_PRODUCT_TAG PRIMARY KEY (TAG, MERCHANT_ID)
);

CREATE TABLE PRODUCT
(
  PRODUCT_ID VARCHAR(255) NOT NULL,
  MERCHANT_ID BIGINT,
  PRODUCT_NAME VARCHAR(255) NOT NULL,
  CONSTRAINT PK_PRODUCT PRIMARY KEY (PRODUCT_ID, MERCHANT_ID)
);

CREATE TABLE PRODUCT_PRODUCT_TAG
(
  TAGS_TAG VARCHAR(255) NOT NULL,
  TAGS_MERCHANT_ID BIGINT NOT NULL,
  PRODUCT_PRODUCT_ID VARCHAR(255) NOT NULL,
  PRODUCT_MERCHANT_ID BIGINT NOT NULL,
  CONSTRAINT FK_PRD_PRD_TAG_PRD_TAG FOREIGN KEY (TAGS_TAG, TAGS_MERCHANT_ID) REFERENCES PRODUCT_TAG (TAG, MERCHANT_ID),
  CONSTRAINT FK_PRD_PRD_TAG_PRD FOREIGN KEY (PRODUCT_PRODUCT_ID, PRODUCT_MERCHANT_ID) REFERENCES PRODUCT (PRODUCT_ID, MERCHANT_ID)
);

ALTER TABLE PRODUCT
  ADD CONSTRAINT FK_PRODUCT_MERCHANT
FOREIGN KEY (MERCHANT_ID) REFERENCES MERCHANT (ID);

ALTER TABLE PRODUCT_TAG
  ADD CONSTRAINT FK_PRODUCT_TAG_MERCHANT
FOREIGN KEY (MERCHANT_ID) REFERENCES MERCHANT (ID);