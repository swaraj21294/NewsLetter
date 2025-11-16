INSERT INTO topics (name, description) VALUES ('Travel', 'Guides, tips, and news for global travel destinations.');
INSERT INTO topics (name, description) VALUES ('Cooking', 'Recipes, techniques, and culinary trends from around the world.');
INSERT INTO topics (name, description) VALUES ('Gaming', 'Reviews, previews, and esports updates for video games.');
INSERT INTO topics (name, description) VALUES ('Self-Improvement', 'Articles on mindfulness, productivity, and personal growth.');


INSERT INTO contents (title, body, scheduled_time, sent, topic_id) VALUES ('Best Hidden Beaches in Portugal', 'A guide to the secret coves and pristine sands of the Algarve coast.', '2025-11-20 09:00:00', false, 1); 
INSERT INTO contents (title, body, scheduled_time, sent, topic_id) VALUES ('The 5-Minute Morning Routine', 'Simple habits you can adopt right now to boost your productivity for the rest of the day.', '2025-11-20 07:00:00', false, 4);
INSERT INTO contents (title, body, scheduled_time, sent, topic_id) VALUES ('Review: New RPG Launch', 'Detailed analysis of the graphics, story, and mechanics of the year''s biggest role-playing game.', '2025-11-20 14:30:00', false, 3);

INSERT INTO subscribers (email, name, topic_id) VALUES ('alice.jones@example.com', 'Alice Jones', 1);
INSERT INTO subscribers (email, name, topic_id) VALUES ('bob.smith@example.com', 'Bob Smith', 2);
INSERT INTO subscribers (email, name, topic_id) VALUES ('carol.lee@example.com', 'Carol Lee', 3);