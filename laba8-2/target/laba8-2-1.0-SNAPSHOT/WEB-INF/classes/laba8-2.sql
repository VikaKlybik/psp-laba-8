CREATE TABLE `COMMENTS` (
  `id` int NOT NULL,
  `text` varchar(400) NOT NULL,
  `post_id` int NOT NULL,
  `author_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `likes` (
  `id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `comment_id` int DEFAULT NULL,
  `post_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `POSTS` (
  `id` int NOT NULL,
  `title` varchar(255) NOT NULL,
  `post_body` varchar(255) DEFAULT '',
  `created_at` timestamp NULL DEFAULT NULL,
  `edited_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `author_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `POST_TAGS` (
  `id` int NOT NULL,
  `tag_name_id` int NOT NULL,
  `post_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `TAG_NAMES` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `USERS` (
  `id` int NOT NULL,
  `firstName` varchar(40) NOT NULL,
  `lastName` varchar(40) DEFAULT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(60) NOT NULL,
  `country` varchar(60) DEFAULT NULL,
  `gender` varchar(60) DEFAULT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `registrationDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `editedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `website_role` varchar(20) DEFAULT 'user'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `COMMENTS`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `author_id` (`author_id`,`post_id`),
  ADD KEY `post_id` (`post_id`);

ALTER TABLE `likes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_id` (`user_id`,`comment_id`,`post_id`),
  ADD KEY `comment_id` (`comment_id`),
  ADD KEY `post_id` (`post_id`);

ALTER TABLE `POSTS`
  ADD PRIMARY KEY (`id`),
  ADD KEY `author_id` (`author_id`);

ALTER TABLE `POST_TAGS`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `tag_name_id` (`tag_name_id`,`post_id`),
  ADD KEY `post_id` (`post_id`);

--
-- Indexes for table `TAG_NAMES`
--
ALTER TABLE `TAG_NAMES`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `USERS`
--
ALTER TABLE `USERS`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`,`password`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `COMMENTS`
--
ALTER TABLE `COMMENTS`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `likes`
--
ALTER TABLE `likes`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `POSTS`
--
ALTER TABLE `POSTS`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `POST_TAGS`
--
ALTER TABLE `POST_TAGS`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `TAG_NAMES`
--
ALTER TABLE `TAG_NAMES`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `USERS`
--
ALTER TABLE `USERS`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `COMMENTS`
--
ALTER TABLE `COMMENTS`
  ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `POSTS` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`author_id`) REFERENCES `USERS` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `likes`
--
ALTER TABLE `likes`
  ADD CONSTRAINT `likes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `likes_ibfk_2` FOREIGN KEY (`comment_id`) REFERENCES `COMMENTS` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `likes_ibfk_3` FOREIGN KEY (`post_id`) REFERENCES `POSTS` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `POSTS`
--
ALTER TABLE `POSTS`
  ADD CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `USERS` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `POST_TAGS`
--
ALTER TABLE `POST_TAGS`
  ADD CONSTRAINT `post_tags_ibfk_1` FOREIGN KEY (`tag_name_id`) REFERENCES `TAG_NAMES` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `post_tags_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `POSTS` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;