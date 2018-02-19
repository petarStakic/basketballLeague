SELECT id, country, city, time_zone_id, name, capacity, active
FROM arenas
WHERE LOWER(name) = LOWER(?) AND LOWER(city) = LOWER(?) AND LOWER(country) = LOWER(?)