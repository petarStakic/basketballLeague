UPDATE competition_formats t
SET t.type = ?, t.description = ?, t.matches_per_fixture = ?, t.number_of_groups = ?, t.tiebraker = ?
WHERE t.id = ?