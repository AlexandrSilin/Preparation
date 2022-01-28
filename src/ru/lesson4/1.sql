select m.title as "Название фильма", s.date as "Время начала", m.duration as "Длительность"
from movies as m
         inner join timetables as tt on tt.movie = m.id
         inner join sessions as s on s.id = tt.session
order by s.date;