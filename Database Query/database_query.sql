SELECT user_id, username, training_id, training_date, count FROM
User u INNER JOIN Training_details t ON u.user_id = t.user_id;