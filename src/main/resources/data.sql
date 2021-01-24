INSERT INTO match (home_team, away_team, status, start_time, end_time, created_date)
VALUES  ('R.Madrid', 'Betis', 'NOT_STARTED', CURRENT_TIMESTAMP() , CURRENT_TIMESTAMP() + 1, CURRENT_TIMESTAMP() - 5),
        ('Atletico', 'Toledo', 'NOT_STARTED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + 1, CURRENT_TIMESTAMP() - 1),
        ('Barcelona', 'Granada', 'NOT_STARTED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + 1, CURRENT_TIMESTAMP() - 3),
        ('Deportivo', 'Celta', 'NOT_STARTED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + 1, CURRENT_TIMESTAMP() - 10);