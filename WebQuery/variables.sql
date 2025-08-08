-- Variables Database Export
-- Generated on: Fri Aug 08 15:38:49 SAST 2025

DROP TABLE IF EXISTS variables;

CREATE TABLE variables (
    variable VARCHAR(10) DEFAULT '0',
    value INTEGER DEFAULT 0,
    query INTEGER DEFAULT 0
);

-- Insert data
INSERT INTO variables (variable, value, query) VALUES ('a', 0, 0);
INSERT INTO variables (variable, value, query) VALUES ('b', 0, 0);
INSERT INTO variables (variable, value, query) VALUES ('c', 0, 0);

-- End of export
