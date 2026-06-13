ALTER TABLE members
    ADD CONSTRAINT members_email_unique UNIQUE (email);