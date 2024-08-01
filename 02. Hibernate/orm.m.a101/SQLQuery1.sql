SELECT * FROM MovieTheater.CINEMA_ROOM_DETAIL

select * from MovieTheater.SEAT

select * from MovieTheater.CINEMA_ROOM cr join MovieTheater.SEAT s 
	ON cr.CINEMA_ROOM_ID = s.CINEMA_ROOM_ID join MovieTheater.CINEMA_ROOM_DETAIL crd 
	on cr.CINEMA_ROOM_ID = crd.CINEMA_ROOM_ID