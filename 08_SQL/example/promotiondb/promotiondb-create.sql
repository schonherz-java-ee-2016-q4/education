-- Creating a new schema for the Promotion Tracker app.
CREATE SCHEMA IF NOT EXISTS promotion_tracker;

-- Set the active schema so subsequent statements won't have to specify it.
SET SCHEMA 'promotion_tracker';

/*
 * Checking if 'prizetype' type already exists, creating it if not.
 *
 * Prize type can be 'digital' in case of, for example music downloads or pictures,
 * 'physical' for small gifts like selfie sticks or a soccer ball
 * and 'couponcode' for a a short coupon code the participant
 * can use to obtain a discount somewhere.
 */
DO
$$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'prizetype') THEN
        CREATE TYPE prizetype AS ENUM ('digital', 'physical', 'couponcode');
    END IF;
END$$;

/*
 * Table representing a promotion.
 * A promotion is a timeframed online event where participants can win prizes
 * by submitting codes found on various products.
 */
CREATE TABLE IF NOT EXISTS promotion (
    id SERIAL PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    description VARCHAR,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    url VARCHAR(255)
);

/*
 * Table representing a person taking part in a promotion.
 */
CREATE TABLE IF NOT EXISTS participant(
    id SERIAL PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    email VARCHAR(255) NOT NULL
);

/*
 * Table representing a prize.
 * Prizes can be won by a participant when (s)he submits a promotion code
 * and the system's algorithm decides that the given code won.
 *
 * Prizes can run out of stock when their amount depletes.
 */
CREATE TABLE IF NOT EXISTS prize(
    id SERIAL PRIMARY KEY,
    promo_id INTEGER NOT NULL,
    name VARCHAR(60) NOT NULL,
    type prizetype NOT NULL,
    amount INTEGER DEFAULT 10,
    CONSTRAINT prize_promotion_fk
        FOREIGN KEY (promo_id)
        REFERENCES promotion(id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

/*
 * Table representing a participant's submission for an
 * ongoing promotion.
 *
 * A participant should not be able to submit a code
 * when the target promotion is not running.
 *
 * If a record in this table has an actual prize_id instead of NULL,
 * it means that this submission was a winning submission and the
 * participant won the prize identified by prize_id.
 */
CREATE TABLE IF NOT EXISTS submission(
    id SERIAL PRIMARY KEY,
    promo_id INTEGER NOT NULL,
    participant_id INTEGER NOT NULL,
    prize_id INTEGER,
    date_submitted DATE NOT NULL DEFAULT CURRENT_DATE,
    code VARCHAR(10) NOT NULL,
    CONSTRAINT submission_promo_fk
        FOREIGN KEY (promo_id)
        REFERENCES promotion(id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT submission_participant_fk
        FOREIGN KEY (participant_id)
        REFERENCES participant(id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT submission_prize_fk
        FOREIGN KEY (prize_id)
        REFERENCES prize(id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
