SELECT i.ANIMAL_ID, i.NAME
FROM ANIMAL_INS AS i
LEFT JOIN ANIMAL_OUTS AS o ON i.ANIMAL_ID = o.ANIMAL_ID
WHERE o.DATETIME < i.DATETIME
ORDER BY i.DATETIME ASC;