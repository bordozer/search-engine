INSERT INTO VGN_NECK_TUNE (NECK_TUNE_ID, NECK_TUNE_NAME, DEFAULT_NECK_TUNE) VALUES
  (1001, 'EBGDAE', true),
  (1002, 'DADGAD', false),
  (1004, 'Open D', false),
  (1005, 'Open G', false),
  (1006, 'Open C', false),
  (1007, 'Drop D', false),
  (1008, 'Double Drop-D', false),
  (1009, 'Drop C', false)

;

/* NECK TUNES */

-- EHGDAE tune
INSERT INTO VGN_STRING_TUNE (STRING_TUNE_ID, STRING_NUMBER, MUSIC_NOTE, OCTAVE, NECK_TUNE_ID) VALUES
  (501, 1, 'NOTE_E',  'FIRST', 1001),
  (502, 2, 'NOTE_H',  'SMALL', 1001),
  (503, 3, 'NOTE_G',  'SMALL', 1001),
  (504, 4, 'NOTE_D',  'SMALL', 1001),
  (505, 5, 'NOTE_A',  'GREAT', 1001),
  (506, 6, 'NOTE_E',  'GREAT', 1001),
  (507, 7, 'NOTE_H',  'CONTRA', 1001),
  (508, 8, 'NOTE_FD', 'CONTRA', 1001);

-- DADGAD tune
INSERT INTO VGN_STRING_TUNE (STRING_TUNE_ID, STRING_NUMBER, MUSIC_NOTE, OCTAVE, NECK_TUNE_ID) VALUES
  (511, 1, 'NOTE_D',  'FIRST', 1002),
  (512, 2, 'NOTE_A',  'SMALL', 1002),
  (513, 3, 'NOTE_G',  'SMALL', 1002),
  (514, 4, 'NOTE_D',  'SMALL', 1002),
  (515, 5, 'NOTE_A',  'GREAT', 1002),
  (516, 6, 'NOTE_D',  'GREAT', 1002),
  (517, 7, 'NOTE_CD', 'CONTRA', 1002),
  (518, 8, 'NOTE_GD', 'CONTRA', 1002);

-- Open D tune
INSERT INTO VGN_STRING_TUNE (STRING_TUNE_ID, STRING_NUMBER, MUSIC_NOTE, OCTAVE, NECK_TUNE_ID) VALUES
  (541, 1, 'NOTE_D',  'FIRST', 1004),
  (542, 2, 'NOTE_A',  'SMALL', 1004),
  (543, 3, 'NOTE_FD', 'SMALL', 1004),
  (544, 4, 'NOTE_D',  'SMALL', 1004),
  (545, 5, 'NOTE_A',  'GREAT', 1004),
  (546, 6, 'NOTE_D',  'GREAT', 1004),
  (547, 7, 'NOTE_GD', 'CONTRA', 1004), -- TODO: ???
  (548, 8, 'NOTE_GD', 'CONTRA', 1004); -- TODO: ???

-- Open G tune
INSERT INTO VGN_STRING_TUNE (STRING_TUNE_ID, STRING_NUMBER, MUSIC_NOTE, OCTAVE, NECK_TUNE_ID) VALUES
  (531, 1, 'NOTE_D',  'FIRST', 1005),
  (532, 2, 'NOTE_H',  'SMALL', 1005),
  (533, 3, 'NOTE_G',  'SMALL', 1005),
  (534, 4, 'NOTE_D',  'SMALL', 1005),
  (535, 5, 'NOTE_G',  'GREAT', 1005),
  (536, 6, 'NOTE_D',  'GREAT', 1005),
  (537, 7, 'NOTE_GD', 'CONTRA', 1005), -- TODO: ???
  (538, 8, 'NOTE_GD', 'CONTRA', 1005); -- TODO: ???

-- Open C tune
INSERT INTO VGN_STRING_TUNE (STRING_TUNE_ID, STRING_NUMBER, MUSIC_NOTE, OCTAVE, NECK_TUNE_ID) VALUES
  (521, 1, 'NOTE_E',  'FIRST', 1006),
  (522, 2, 'NOTE_C',  'FIRST', 1006),
  (523, 3, 'NOTE_G',  'SMALL', 1006),
  (524, 4, 'NOTE_C',  'SMALL', 1006),
  (525, 5, 'NOTE_G',  'GREAT', 1006),
  (526, 6, 'NOTE_C',  'GREAT', 1006),
  (527, 7, 'NOTE_GD', 'CONTRA', 1006), -- TODO: ???
  (528, 8, 'NOTE_GD', 'CONTRA', 1006); -- TODO: ???

-- Drop D tune
INSERT INTO VGN_STRING_TUNE (STRING_TUNE_ID, STRING_NUMBER, MUSIC_NOTE, OCTAVE, NECK_TUNE_ID) VALUES
  (551, 1, 'NOTE_E',  'FIRST', 1007),
  (552, 2, 'NOTE_H',  'SMALL', 1007),
  (553, 3, 'NOTE_G',  'SMALL', 1007),
  (554, 4, 'NOTE_D',  'SMALL', 1007),
  (555, 5, 'NOTE_A',  'GREAT', 1007),
  (556, 6, 'NOTE_D',  'GREAT', 1007),
  (557, 7, 'NOTE_GD', 'CONTRA', 1007), -- TODO: ???
  (558, 8, 'NOTE_GD', 'CONTRA', 1007); -- TODO: ???

-- Double Drop D tune
INSERT INTO VGN_STRING_TUNE (STRING_TUNE_ID, STRING_NUMBER, MUSIC_NOTE, OCTAVE, NECK_TUNE_ID) VALUES
  (571, 1, 'NOTE_D',  'FIRST', 1008),
  (572, 2, 'NOTE_H',  'SMALL', 1008),
  (573, 3, 'NOTE_G',  'SMALL', 1008),
  (574, 4, 'NOTE_D',  'SMALL', 1008),
  (575, 5, 'NOTE_A',  'GREAT', 1008),
  (576, 6, 'NOTE_D',  'GREAT', 1008),
  (577, 7, 'NOTE_GD', 'CONTRA', 1008), -- TODO: ???
  (578, 8, 'NOTE_GD', 'CONTRA', 1008); -- TODO: ???

-- Drop C tune
INSERT INTO VGN_STRING_TUNE (STRING_TUNE_ID, STRING_NUMBER, MUSIC_NOTE, OCTAVE, NECK_TUNE_ID) VALUES
  (561, 1, 'NOTE_D',  'FIRST', 1009),
  (562, 2, 'NOTE_A',  'SMALL', 1009),
  (563, 3, 'NOTE_F',  'SMALL', 1009),
  (564, 4, 'NOTE_C',  'SMALL', 1009),
  (565, 5, 'NOTE_G',  'GREAT', 1009),
  (566, 6, 'NOTE_C',  'GREAT', 1009),
  (567, 7, 'NOTE_GD', 'CONTRA', 1009), -- TODO: ???
  (568, 8, 'NOTE_GD', 'CONTRA', 1009); -- TODO: ???

/* COLOR PROFILES */

INSERT INTO VGN_COLOR_PROFILE (COLOR_PROFILE_ID, COLOR) VALUES
  (1, '#aed6f1'),
  (2, '#76d7c4'),
  (3, '#5499c7'),
  (4, '#d2b4de'),
  (5, '#fae5d3'),
  (6, '#fcf3cf'),
  (7, '#f8c471'),
  (8, '#688286'),
  (9, '#f4cccc'),
  (11, '#40bee5'),
  (12, '#c677d1'),
  (13, '#d69d68'),
  (14, '#a5894c'),
  (15, '#bc9772'),
  (16, '#a2c4c9'),
  (17, '#ff9900'),
  (18, '#d9d2e9'),
  (19, '#ead1dc'),
  (20, '#be88d5'),
  (21, '#b56ad5'),
  (22, '#dc969f')
  ;

INSERT INTO VGN_SEQUENCE (SEQUENCE_ID, SEQUENCE_NAME, SEQUENCE_TYPE, COLOR_PROFILE_ID, SEQUENCE_ORDER, USER_ID) VALUES
  (8,  'Minor Pentatonic Nerd', 'MINOR', 16, 10, 0),
  (3,  'Blues minor Nerd', 'MINOR', 2, 20, 0),
  (1,  'Aeolian mode Nerd', 'MINOR', 1, 30, 0),
  (21, 'Dorian mode Nerd', 'MINOR', 11, 40, 0),
  (22, 'Phrygian mode Nerd', 'MINOR', 12, 50, 0),
  (5,  'Harmonic minor Nerd', 'MINOR', 3, 60, 0),
  (26, 'Double harmonic minor Nerd', 'MINOR', 18, 70, 0),
  (6,  'Melodic minor Nerd', 'MINOR', 8, 80, 0),
  (7,  'Hiroyoshi Nerd', 'MINOR', 4, 90, 0),
  (28,  'Hon Kumoi Nerd', 'MINOR', 20, 100, 0),
  (29,  'Ivato Nerd', 'MINOR', 21, 110, 0),

  (10, 'Major Pentatonic Nerd', 'MAJOR', 17, 1100, 0),
  (12, 'Blues Major Nerd', 'MAJOR', 5, 1110, 0),
  (11, 'Ionian mode Nerd', 'MAJOR', 6, 1120, 0),
  (23, 'Lydian mode Nerd', 'MAJOR', 13, 1130, 0),
  (24, 'Mixolydian mode Nerd', 'MAJOR', 14, 1140, 0),
  (25, 'Phrygian mode Nerd', 'MAJOR', 15, 1150, 0),
  (13, 'Harmonic major Nerd', 'MAJOR', 7, 1160, 0),
  (27, 'Double harmonic major Nerd', 'MAJOR', 19, 1170, 0),
  (14, 'Melodic major Nerd', 'MAJOR', 9, 1180, 0),
  (30, 'Kumoi Nerd', 'MAJOR', 22, 1190, 0)
  ;

-- http://guitarpages.narod.ru/Definitions_Modes.html

-- Дорийско-фригийский минор
-- Альтерированная минорная пентатоника с пониженной 4-ой ступенью
-- Альтерированная минорная пентатоника с пониженной 1-ой ступенью

-- Лидийско-миксолидийский мажор
-- Альтерированная мажорная пентатоника с пониженной 2-ой ступенью
-- Альтерированная мажорная пентатоника с пониженной 6-ой ступенью

-- Хроматические звукоряды???

-- natural-minor
INSERT INTO VGN_COLOR_PROFILE (COLOR_PROFILE_ID, COLOR) VALUES
  (10, '#D6EAF8');

INSERT INTO VGN_SEQUENCE_INTERVAL(SEQUENCE_INTERVAL_ID, INTERVAL, COLOR_PROFILE_ID, SEQUENCE_ID) VALUES
  (1, 0, null, 1),
  (2, 2, null, 1),
  (3, 3, null, 1),
  (4, 5, 10, 1),
  (5, 7, null, 1),
  (6, 8, null, 1),
  (7, 10, null, 1);

INSERT INTO VGN_NOTE_PROPERTY(NOTE_PROPERTY_ID, NOTE_TYPE, NOTE_TITLE, SEQUENCE_INTERVAL_ID) VALUES
  (1, 'TONIC_NOTE', 'A tonic note', 1),
  (2, 'BAND_NOTE', 'A tonic bend note', 1),
  (3, 'HALF_BAND_NOTE', 'A half band note', 2),
  (4, 'BAND_NOTE', 'A band note', 3),
  (5, 'BAND_NOTE', 'A band note', 4),
  (6, 'HALF_BAND_NOTE', 'A band note', 5),
  (7, 'BAND_NOTE', 'A band note', 6),
  (8, 'BAND_NOTE', 'A band note', 7);

-- minor-blues
INSERT INTO VGN_SEQUENCE_INTERVAL(SEQUENCE_INTERVAL_ID, INTERVAL, COLOR_PROFILE_ID, SEQUENCE_ID) VALUES
  (11, 0, null, 3),
  (12, 3, null, 3),
  (13, 5, null, 3),
  (14, 6, null, 3),
  (15, 7, null, 3),
  (16, 10, null, 3);

INSERT INTO VGN_NOTE_PROPERTY(NOTE_PROPERTY_ID, NOTE_TYPE, NOTE_TITLE, SEQUENCE_INTERVAL_ID) VALUES
  (10, 'TONIC_NOTE', 'A tonic note', 11),
  (12, 'BAND_NOTE', 'A band note', 12),
  (13, 'BAND_NOTE', 'A band note', 13),
  (14, 'BLUES_NOTE', 'A blues note', 14),
  (15, 'HALF_BAND_NOTE', 'A half band note', 15),
  (16, 'BAND_NOTE', 'A band note', 16);

-- harmonic-major
INSERT INTO VGN_SEQUENCE_INTERVAL(SEQUENCE_INTERVAL_ID, INTERVAL, COLOR_PROFILE_ID, SEQUENCE_ID) VALUES
  (21, 0, null, 5),
  (22, 2, null, 5),
  (23, 3, null, 5),
  (24, 5, null, 5),
  (25, 7, null, 5),
  (26, 8, null, 5),
  (27, 11, null, 5);

-- 2x-harmonic-major (Gypsy minor)
INSERT INTO VGN_SEQUENCE_INTERVAL(SEQUENCE_INTERVAL_ID, INTERVAL, COLOR_PROFILE_ID, SEQUENCE_ID) VALUES
  (211, 0, null, 26),
  (212, 2, null, 26),
  (213, 3, null, 26),
  (214, 6, null, 26),
  (215, 7, null, 26),
  (216, 8, null, 26),
  (217, 11, null, 26);

-- melodic-minor
INSERT INTO VGN_SEQUENCE_INTERVAL(SEQUENCE_INTERVAL_ID, INTERVAL, COLOR_PROFILE_ID, SEQUENCE_ID) VALUES
  (41, 0, null, 6),
  (42, 2, null, 6),
  (43, 3, null, 6),
  (44, 5, null, 6),
  (45, 7, null, 6),
  (46, 9, null, 6),
  (47, 11, null, 6);

-- japanese-neutral-hirajoshi
INSERT INTO VGN_SEQUENCE_INTERVAL(SEQUENCE_INTERVAL_ID, INTERVAL, COLOR_PROFILE_ID, SEQUENCE_ID) VALUES
  (31, 0, null, 7),
  (32, 2, null, 7),
  (33, 3, null, 7),
  (34, 7, null, 7),
  (35, 8, null, 7);

-- japanese Hon Kumoi Shiouzhi
INSERT INTO VGN_SEQUENCE_INTERVAL(SEQUENCE_INTERVAL_ID, INTERVAL, COLOR_PROFILE_ID, SEQUENCE_ID) VALUES
  (231, 0, null, 28),
  (232, 1, null, 28),
  (233, 5, null, 28),
  (234, 7, null, 28),
  (235, 8, null, 28);

-- japanese Iwato
INSERT INTO VGN_SEQUENCE_INTERVAL(SEQUENCE_INTERVAL_ID, INTERVAL, COLOR_PROFILE_ID, SEQUENCE_ID) VALUES
  (241, 0, null, 29),
  (242, 1, null, 29),
  (243, 5, null, 29),
  (244, 6, null, 29),
  (245, 10, null, 29);

-- japanese Real Kumoi major
INSERT INTO VGN_SEQUENCE_INTERVAL(SEQUENCE_INTERVAL_ID, INTERVAL, COLOR_PROFILE_ID, SEQUENCE_ID) VALUES
  (251, 0, null, 30),
  (252, 4, null, 30),
  (253, 5, null, 30),
  (254, 9, null, 30),
  (255, 11, null, 30);

-- minor pentatinic
INSERT INTO VGN_SEQUENCE_INTERVAL(SEQUENCE_INTERVAL_ID, INTERVAL, COLOR_PROFILE_ID, SEQUENCE_ID) VALUES
  (191, 0, null, 8),
  (192, 3, null, 8),
  (193, 5, null, 8),
  (194, 7, null, 8),
  (195, 10, null, 8);


-- major pentatinic
INSERT INTO VGN_SEQUENCE_INTERVAL(SEQUENCE_INTERVAL_ID, INTERVAL, COLOR_PROFILE_ID, SEQUENCE_ID) VALUES
  (201, 0, null, 10),
  (202, 2, null, 10),
  (203, 4, null, 10),
  (204, 7, null, 10),
  (205, 9, null, 10);

-- natural-major
INSERT INTO VGN_SEQUENCE_INTERVAL(SEQUENCE_INTERVAL_ID, INTERVAL, COLOR_PROFILE_ID, SEQUENCE_ID) VALUES
  (101, 0, null, 11),
  (102, 2, null, 11),
  (103, 4, null, 11),
  (104, 5, null, 11),
  (105, 7, null, 11),
  (106, 9, null, 11),
  (107, 11, null, 11);

-- blues-major
INSERT INTO VGN_SEQUENCE_INTERVAL(SEQUENCE_INTERVAL_ID, INTERVAL, COLOR_PROFILE_ID, SEQUENCE_ID) VALUES
  (111, 0, null, 12),
  (112, 2, null, 12),
  (113, 3, null, 12),
  (114, 4, null, 12),
  (115, 7, null, 12),
  (116, 9, null, 12);

-- harmonic-major
INSERT INTO VGN_SEQUENCE_INTERVAL(SEQUENCE_INTERVAL_ID, INTERVAL, COLOR_PROFILE_ID, SEQUENCE_ID) VALUES
  (121, 0, null, 13),
  (122, 2, null, 13),
  (123, 4, null, 13),
  (124, 5, null, 13),
  (125, 7, null, 13),
  (126, 8, null, 13),
  (127, 11, null, 13);

-- 2x-harmonic-major (Gypsy major)
INSERT INTO VGN_SEQUENCE_INTERVAL(SEQUENCE_INTERVAL_ID, INTERVAL, COLOR_PROFILE_ID, SEQUENCE_ID) VALUES
  (221, 0, null, 27),
  (222, 1, null, 27),
  (223, 4, null, 27),
  (224, 5, null, 27),
  (225, 7, null, 27),
  (226, 8, null, 27),
  (227, 11, null, 27);

-- melodic-major
INSERT INTO VGN_SEQUENCE_INTERVAL(SEQUENCE_INTERVAL_ID, INTERVAL, COLOR_PROFILE_ID, SEQUENCE_ID) VALUES
  (171, 0, null, 14),
  (172, 2, null, 14),
  (173, 4, null, 14),
  (174, 5, null, 14),
  (175, 7, null, 14),
  (176, 8, null, 14),
  (177, 10, null, 14);

-- Dorian minor
INSERT INTO VGN_SEQUENCE_INTERVAL(SEQUENCE_INTERVAL_ID, INTERVAL, COLOR_PROFILE_ID, SEQUENCE_ID) VALUES
  (131, 0, null, 21),
  (132, 2, null, 21),
  (133, 3, null, 21),
  (134, 5, null, 21),
  (135, 7, null, 21),
  (136, 9, null, 21),
  (137, 10, null, 21);

-- Phrygian minor
INSERT INTO VGN_SEQUENCE_INTERVAL(SEQUENCE_INTERVAL_ID, INTERVAL, COLOR_PROFILE_ID, SEQUENCE_ID) VALUES
  (141, 0, null, 22),
  (142, 1, null, 22),
  (143, 3, null, 22),
  (144, 5, null, 22),
  (145, 7, null, 22),
  (146, 8, null, 22),
  (147, 10, null, 22);

-- Lydian mode
INSERT INTO VGN_SEQUENCE_INTERVAL(SEQUENCE_INTERVAL_ID, INTERVAL, COLOR_PROFILE_ID, SEQUENCE_ID) VALUES
  (151, 0, null, 23),
  (152, 2, null, 23),
  (153, 4, null, 23),
  (154, 6, null, 23),
  (155, 7, null, 23),
  (156, 9, null, 23),
  (157, 11, null, 23);

-- Lydian mode
INSERT INTO VGN_SEQUENCE_INTERVAL(SEQUENCE_INTERVAL_ID, INTERVAL, COLOR_PROFILE_ID, SEQUENCE_ID) VALUES
  (161, 0, null, 24),
  (162, 2, null, 24),
  (163, 4, null, 24),
  (164, 5, null, 24),
  (165, 7, null, 24),
  (166, 9, null, 24),
  (167, 10, null, 24);

-- Phrygian major
INSERT INTO VGN_SEQUENCE_INTERVAL(SEQUENCE_INTERVAL_ID, INTERVAL, COLOR_PROFILE_ID, SEQUENCE_ID) VALUES
  (181, 0, null, 25),
  (182, 1, null, 25),
  (183, 4, null, 25),
  (184, 5, null, 25),
  (185, 7, null, 25),
  (186, 8, null, 25),
  (187, 10, null, 25);
