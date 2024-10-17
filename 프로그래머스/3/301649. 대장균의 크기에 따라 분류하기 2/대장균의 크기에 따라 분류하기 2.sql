SELECT ID, 
    CASE
        WHEN percentile_rank = 1 THEN 'CRITICAL'
        WHEN percentile_rank = 2 THEN 'HIGH'
        WHEN percentile_rank = 3 THEN 'MEDIUM'
        ELSE 'LOW'
    END AS COLONY_NAME
FROM (
    SELECT 
        ID,
        SIZE_OF_COLONY,
        NTILE(4) OVER (ORDER BY SIZE_OF_COLONY DESC) AS percentile_rank
    FROM 
        ECOLI_DATA
) AS RANK_DATA
ORDER BY ID ASC;
        