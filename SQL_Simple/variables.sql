-- Variables Database Export
-- Generated on: Fri Aug 08 14:21:41 SAST 2025

DROP TABLE IF EXISTS variables;

CREATE TABLE variables (
    variable VARCHAR(10) DEFAULT '0',
    value INTEGER DEFAULT 0,
    query INTEGER DEFAULT 0
);

-- Insert data
INSERT INTO variables (variable, value, query) VALUES ('a', 5, 5);
INSERT INTO variables (variable, value, query) VALUES ('b', 3, 3);
INSERT INTO variables (variable, value, query) VALUES ('c', 2, 2);

-- End of export
