-- Passwords are in the format: Password<UserLetter>123. Unless specified otherwise.
-- Passwords encrypted using: https://www.javainuse.com/onlineBcrypt
INSERT INTO local_user (email, first_name, last_name, password, username, email_verified)
    VALUES ('UserA@junit.com', 'UserA-FirstName', 'UserA-LastName', '$2a$10$..RDaqutnYMxitHO8ZK2L.Su8/69dNso4lgRAhfno4lxhq5W6HdUm', 'UserA', true)
    , ('UserB@junit.com', 'UserB-FirstName', 'UserB-LastName', '$2a$10$btEcZcMvo2xn2u/sJpQLw.cwRHcn24EiO7paLvgcEB0OUOUFlPCXO', 'UserB', false);