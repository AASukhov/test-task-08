CREATE SCHEMA IF NOT EXISTS "public";

CREATE TABLE IF NOT EXISTS users (
    id INT,
    username VARCHAR (40),
    email VARCHAR (40),
    password VARCHAR (70),
    creation_date DATE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS quotes (
    id INT,
    username VARCHAR (40),
    votes LONG,
    text VARCHAR (255),
    PRIMARY KEY (id)
);

INSERT INTO quotes VALUES (2829,'PETR', 0,'DASDADASDASD'),
                          (232,'PETR', 0,'QQQQQQQQSSSSS'),
                          (2221,'PETR', 0,'PPPPPGFGKFGKF'),
                          (28321,'PETR', 0,'VCXMVMCXMVXC'),
                          (11102,'PETR', 0,'CVVSDFIAKSDF'),
                          (7,'PETR', 0,'DFASDFIASDIFOA'),
                          (2321,'PETR', 0,'ERQWJDSKMCA'),
                          (19222,'alex', 0,'AMJKCMAADPDPD'),
                          (251,'alex', 0,'DFADMVKMCAE'),
                          (785,'alex', 0,'SDAFASDF'),
                          (1921,'flex', 0,'WEOQWOEQWODLSDA');

