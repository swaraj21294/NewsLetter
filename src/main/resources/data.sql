-- Insert sample topics
INSERT INTO topics (id, name, description) VALUES 
(1, 'Technology', 'Latest tech news and updates'),
(2, 'Science', 'Scientific discoveries and research'),
(3, 'Business', 'Business and finance news'),
(4, 'Health', 'Health and wellness tips');

-- Insert sample subscribers
INSERT INTO subscribers (id, email, name, topic_id) VALUES 
(1, '5395sk@gmail.com', 'Swaraj Kumar', 1);

-- Insert sample content
INSERT INTO contents (id, title, body, scheduled_time, sent, topic_id) VALUES 
(1, 'AI Breakthrough 2024', 'New AI model achieves remarkable results in natural language processing...', '2025-11-14 18:39:29', false, 1),
(2, 'Mars Mission Update', 'The latest discoveries from the Perseverance rover on Mars...', DATEADD('MINUTE', 10, '	2025-11-14 18:39:29'), false, 2),
(3, 'Market Trends Q1 2024', 'Analysis of stock market performance and future predictions...', DATEADD('HOUR', 1, '	2025-11-14 18:39:29'), false, 3),
(4, 'Healthy Eating Tips', 'Top 10 nutrition tips for better health and wellness...', DATEADD('DAY', 1, '	2025-11-14 18:39:29'), false, 4);