CREATE TABLE IF NOT EXISTS files (
    fileid  UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    contenttype TEXT,
    data bytea
);