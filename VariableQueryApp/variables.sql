-- Variables Database Export
-- Generated on: Fri Aug 08 15:19:52 SAST 2025

DROP TABLE IF EXISTS variables;

CREATE TABLE variables (
    variable VARCHAR(10) DEFAULT '0',
    value INTEGER DEFAULT 0,
    query INTEGER DEFAULT 0
);

-- Insert data
INSERT INTO variables (variable, value, query) VALUES ('a', 1, 2);
INSERT INTO variables (variable, value, query) VALUES ('b', 2, 1);
INSERT INTO variables (variable, value, query) VALUES ('c', 0, -1);

-- End of export
