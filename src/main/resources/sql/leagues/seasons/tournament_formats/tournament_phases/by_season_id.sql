SELECT tp.id, tp.phase_num, tp.phase_name, tp.start_date, tp.end_date,
f.id as format_id, f.type, f.description, f.matches_per_fixture, f.number_of_groups, f.tiebraker
FROM tournament_phases tp JOIN competition_formats f ON tp.competition_format_id = f.id