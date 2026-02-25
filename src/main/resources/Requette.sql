-- question 1
SELECT COUNT(id) AS total_votes
FROM vote;
-- q2
SELECT vote_type, COUNT(id) AS count
FROM vote
GROUP BY vote_type;
 -- q3
SELECT c.name AS candidate_name,
       COUNT(v.id) FILTER (WHERE v.vote_type = 'VALID') AS valid_vote
FROM candidate c
         LEFT JOIN vote v ON c.id = v.candidate_id
GROUP BY c.id, c.name;
--q4
SELECT
    COUNT(id) FILTER (WHERE vote_type = 'VALID') AS valid_count,
    COUNT(id) FILTER (WHERE vote_type = 'BLANK') AS blank_count,
    COUNT(id) FILTER (WHERE vote_type = 'NULL') AS null_count
FROM vote;
 --q5
SELECT
    (COUNT(v.id)::FLOAT / COUNT(vt.id)::FLOAT) * 100 AS participation_rate
FROM voter vt
         LEFT JOIN vote v ON vt.id = v.voter_id;

-- q6
SELECT c.name AS candidate_name,
       COUNT(v.id) AS valid_vote_count
FROM candidate c
         JOIN vote v ON c.id = v.candidate_id
WHERE v.vote_type = 'VALID'
GROUP BY c.id, c.name
ORDER BY valid_vote_count DESC
    LIMIT 1;
