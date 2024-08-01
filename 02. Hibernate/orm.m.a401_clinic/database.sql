CREATE PROCEDURE PagingPatient
    @pageNumber INT,
    @pageSize INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @offset INT;
    SET @offset = (@pageNumber - 1) * @pageSize;

    SELECT *
    FROM Patient
    ORDER BY pat_id
    OFFSET @Offset ROWS
    FETCH NEXT @pageSize ROWS ONLY;
END



EXEC PagingPatient 1,2

SELECT * FROM Patient