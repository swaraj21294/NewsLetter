NEWS LETTER COLLECTION API ENDPOINTS AND USAGE GUIDE
========================================================================

---
SERVICE ACCESS AND SETUP
---

**Base URL:**
To access the application, set the 'baseURL' variable in your Postman environment to:
https://news-letter-service-691d712f6ef2.herokuapp.com

**Postman Collection:**
Download the Postman collection to use the service: News Letter Collection.postman_collection.json

---
USAGE INSTRUCTIONS
---
Initial data was loaded using import.sql contents
### Retrieving Data (GET Endpoints)
* Use the **GET** endpoints (/api/contents, /api/topics, /api/subscribers) to view the existing data in the database for newsletter contents, topics, and subscribers.

### Creating Data (POST Endpoints)
* Use the **POST** calls (/api/contents, /api/topics, /api/subscribers) to add new content, topics, and subscribers to the service.

### Updating Data (PUT Endpoints)
* Use the **PUT** calls (/api/contents/{id}, /api/topics/{id}, /api/subscribers/{id}) to update existing content, topics, and subscribers.
* **Timezone for Content Update:** To update the scheduledTime for content, you must use the **timezone** returned by the /api/time endpoint.

---
TOPICS ENDPOINTS
---

1. Get All Topics
   Method: GET
   Path: /api/topics
   Request Body: None

2. Get Topic by ID
   Method: GET
   Path: /api/topics/1
   Request Body: None

3. Create a New Topic
   Method: POST
   Path: /api/topics
   Request Body:
   {
       "name": "Technology",
       "description": "Latest tech news and updates"
   }

4. Update a Topic
   Method: PUT
   Path: /api/topics/1
   Request Body:
   {
       "name": "Updated Technology",
       "description": "Updated tech news description"
   }

5. Delete a Topic
   Method: DELETE
   Path: /api/topics/1
   Request Body: None


---
SUBSCRIBERS ENDPOINTS
---

6. Get All Subscribers
   Method: GET
   Path: /api/subscribers
   Request Body: None

7. Subscribe a New User
   Method: POST
   Path: /api/subscribers
   Request Body:
   {
       "email": "john.doe@example.com",
       "name": "John Doe",
       "topicId": 1
   }

8. Update a Subscriber
   Method: PUT
   Path: /api/subscribers/1
   Request Body:
   {
       "email": "updated.john@example.com",
       "name": "John Updated",
       "topicId": 7
   }

9. Unsubscribe/Delete a Subscriber
   Method: DELETE
   Path: /api/subscribers/1
   Request Body: None


---
CONTENT ENDPOINTS
---

10. Get All Newsletter Contents
    Method: GET
    Path: /api/contents
    Request Body: None

11. Create New Newsletter Content
    Method: POST
    Path: /api/contents
    Request Body:
    {
        "title": "Breaking News: AI Revolution",
        "body": "Artificial intelligence is transforming industries worldwide...",
        "scheduledTime": "2024-01-15T14:30:00",
        "topicId": 1
    }

12. Update Content
    Method: PUT
    Path: /api/contents/1
    Request Body:
    {
        "title": "Updated: AI Revolution 2025",
        "body": "Updated content about AI transformation...",
        "scheduledTime": "2024-01-16T10:00:00",
        "topicId": 7
    }


---
OTHER ENDPOINT
---

13. Get Server Time and Timezone
    Method: GET
    Path: /api/time
    Request Body: None


---
PITFALLS
---

1.  Email Delivery: There is no retry mechanism implemented if an email fails to send to all subscribers.
2.  Scheduler Time Matching: The scheduler runs every minute. It fetches content when the scheduled time matches the current time, ignoring the seconds part. For example, a scheduled time of 2025-11-17:10:50:10 will match a current time of 2025-11-17:10:50.


---
IMPROVEMENTS: Implementing Configurable Retries
---

To improve reliability and implement 'n' number of configurable retries for email sending, the better approach is Asynchronous Messaging.

1. Asynchronous Messaging (Most Scalable Solution):

Decouple: In the NewsletterService, instead of calling EmailService directly, publish an 'Email Details' message (containing toEmail, subject, body) to a message broker (Kafka).
Dedicated Listener: A separate consumer service (Message Listener) listens to the message queue. This listener handles the actual call to the EmailService.
Retry Handling: Configure the message broker itself (or the Spring integration) to manage retries. If the listener throws an exception (due to a transient failure), the broker will automatically retry delivering the message according to a configured policy (e.g., 5 retries with exponential backoff).
Dead Letter Queue (DLQ): After all retries are exhausted, the broker moves the failed message to a DLQ for manual inspection or processing, ensuring no email task is permanently lost.
