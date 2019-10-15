-- https://en.wikipedia.org/wiki/Information_schema
-- https://dev.mysql.com/doc/refman/5.5/en/performance-schema.html
-- Performance and information schema are not scanned because the client doesn't need that data
select C.TABLE_SCHEMA AS DATABASESCHEMA, T.TABLE_NAME AS TABLENAME,C.COLUMN_NAME AS COLUMNNAME ,
DATA_TYPE AS COLUMNTYPE from INFORMATION_SCHEMA.columns C inner join
information_schema.tables T on C.TABLE_NAME=T.TABLE_NAME and C.TABLE_SCHEMA=T.TABLE_SCHEMA
WHERE T.TABLE_SCHEMA NOT IN ( 'information_schema','MYSQL','performance_schema','mysql')
order by C.TABLE_SCHEMA, T.TABLE_NAME, C.COLUMN_NAME