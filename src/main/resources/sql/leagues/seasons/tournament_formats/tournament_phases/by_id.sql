SELECT tp.id, tp.phase_num, tp.phase_name, tp.start_date, tp.end_date, tp.competition_format_id, tp.season_id,
r.ranking, t.id as teams_table_id, t.name, t.country, t.city, t.national
FROM tournament_phases tp JOIN xref_team_season r ON tp.id = r.phase_id JOIN teams t ON r.team_id = t.id
WHERE tp.id = ?