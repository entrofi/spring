# Optimistic Locking in A Rest API Using Http ETag & Spring ETag Support

In this example, I have tried to give an idea about how http header ``ETag`` can be used to provide optimistic locking support in Rest API level. 

It's pretty common to for clients of a rest API to interact with the same resource concurrently. In such cases, the copies of the resource owned by each client might differ from each other in time and if the intention is to send an update to the server, the data might get inconsistent. Optimistic locking is a well-known approach to such kind of situations and unless implemented intentionally, this feature is built-in to most of ORM frameworks. We can also implement optimistic locking support using http 1.1's  ``Etag`` (Entity Tag) support. 

### Guides
