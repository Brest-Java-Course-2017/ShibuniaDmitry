DROP TABLE IF EXISTS team;
CREATE TABLE team(
  team_id INT NOT NULL AUTO_INCREMENT,
  team_name VARCHAR(50) NOT NULL UNIQUE,
  players_quantity INT NOT NULL,
  PRIMARY KEY (team_id)
);

DROP TABLE IF EXISTS player;
CREATE TABLE player(
  player_id INT NOT NULL AUTO_INCREMENT,
  team_id INT NOT NULL,
  player_name VARCHAR(50) NOT NULL,
  player_surname VARCHAR(50) NOT NULL,
  acceptance_date DATE,
  PRIMARY KEY (player_id),
  FOREIGN KEY (team_id) REFERENCES team(team_id)
);
