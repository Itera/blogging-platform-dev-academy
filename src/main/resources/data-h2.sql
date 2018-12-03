INSERT INTO CATEGORY (ID, NAME) VALUES (1, 'sport');
INSERT INTO CATEGORY (ID, NAME) VALUES (2, 'movies');
INSERT INTO CATEGORY (ID, NAME) VALUES (3, 'fashion');
INSERT INTO CATEGORY (ID, NAME) VALUES (4, 'food');
INSERT INTO CATEGORY (ID, NAME) VALUES (5, 'culture');
INSERT INTO CATEGORY (ID, NAME) VALUES (6, 'paleonthology');
INSERT INTO CATEGORY (ID, NAME) VALUES (7, 'television');
INSERT INTO CATEGORY (ID, NAME) VALUES (8, 'music');

INSERT INTO AUTHOR (ID, USERNAME, FIRSTNAME, LASTNAME, EMAIL, PASSWORD, PHONE)
VALUES (1, 'Joey', 'Joseph', 'Tribbiani', 'joey@friends.com', 'howudoing?', 123456789);
INSERT INTO AUTHOR (ID, USERNAME, FIRSTNAME, LASTNAME, EMAIL, PASSWORD, PHONE)
VALUES (2, 'Rachel', 'Rachel', 'Green', 'rachel@friends.com', 'transponsterisaword', 123456789);
INSERT INTO AUTHOR (ID, USERNAME, FIRSTNAME, LASTNAME, EMAIL, PASSWORD, PHONE)
VALUES (3, 'Monica', 'Monica', 'Geller', 'monica@friends.com', 'iknow!', 123456789);
INSERT INTO AUTHOR (ID, USERNAME, FIRSTNAME, LASTNAME, EMAIL, PASSWORD, PHONE)
VALUES (4, 'Ross', 'Ross', 'Geller', 'ross@friends.com', 'wewereonabreak!', 123456789);
INSERT INTO AUTHOR (ID, USERNAME, FIRSTNAME, LASTNAME, EMAIL, PASSWORD, PHONE)
VALUES (5, 'Chandler', 'Chandler', 'Bing', 'chandy@friends.com', 'muriel', 123456789);
INSERT INTO AUTHOR (ID, USERNAME, FIRSTNAME, LASTNAME, EMAIL, PASSWORD, PHONE)
VALUES (6, 'Phoebs', 'Phoebe', 'Buffay', 'phoebs@friends.com', 'reginaphalange', 123456789);

INSERT INTO POST (ID, TITLE, CREATED, PEREX, CONTENT, AUTHOR_ID)
VALUES (
  1,
  'Get to Know a Dino: Velociraptor',
  parsedatetime('17-09-2018 18:47:52', 'dd-MM-yyyy hh:mm:ss'),
  'Today’s birds are descended from a group of dinosaurs known as theropods. This group included many species of ' ||
  'dinosaurs, from the towering Tyrannosaurus rex to the more diminutive Velociraptor.',
  'Surprised that Velociraptor is so small? In Jurassic Park, the look and behavior of the now-famous predators ' ||
  'was based on Deinonychus, a much larger and more terrifying creature. But the name Velociraptor was more ' ||
  'dramatic and easier to say, and a star was born. Thanks to its star turn, Velociraptor is one of the most famous non-bird dinosaurs in the world. This Hollywood star also has many features that mark it as a close relative of modern birds. The bird-like traits of Velociraptor included hinged ankles, swivel-jointed wrists, and a furcula, or wishbone. The animal’s feet are bird-like, too, with three forward-facing toes—though a horny sheath would have covered the toe and finger bones you see, doubling their size. Living birds and reptiles—as well as non-bird dinosaurs—also have a bony ring in each eye socket. Velociraptor was covered in thin filaments, and the feathers on its tail and forearms would look right at home on a living bird, though this animal was flightless. Velociraptor was a fierce predator, chasing prey on two legs. But even this behavior is bird-like; most birds spend more time walking than flying, and only a few catch prey on the wing.',
  4
);

INSERT INTO COMMENT (ID, CREATED, USERNAME, CONTENT, POST_ID)
VALUES (1, parsedatetime('17-09-2018 18:53:02', 'dd-MM-yyyy hh:mm:ss'), 'Emily', '😢', 1);

INSERT INTO POST_CATEGORY (POST_ID, CATEGORY_ID) VALUES (1, 6);