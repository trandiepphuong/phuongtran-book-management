drop table comment;
drop table book;
drop table "user";
drop table role;
create table role
(
    id   serial       not null primary key,
    name varchar(255) not null
);
create table "user"
(
    id         serial       not null primary key,
    email      varchar(255) not null UNIQUE,
    password   varchar(255) not null,
    first_name varchar(255),
    last_name  varchar(255),
    enabled    boolean      not null,
    avatar     varchar(255),
    role_id    int,
    FOREIGN KEY (role_id) REFERENCES role (id)
);


create table book
(
    id          serial        not null primary key,
    title       varchar(1000) not null,
    author      varchar(1000) not null,
    description varchar(2000),
    created_at  TIMESTAMP     not null,
    updated_at  TIMESTAMP     not null,
    image       varchar(1000),
    enabled     boolean       not null,
    user_id     int           not null,
    FOREIGN KEY (user_id) REFERENCES "user" (id)
);

insert into role (name)
values ('ROLE_ADMIN'),
       ('ROLE_USER'),
       ('ROLE_SUPERADMIN');

insert into "user" (email, password, enabled, role_id)
values ('phuong1@gmail.com', '123', true, 1),
       ('phuong2@gmail.com', '123', true, 2),
       ('phuong3@gmail.com', '123', true, 3);

insert into book (title, author, description, created_at, updated_at, image, enabled, user_id)
values ('The Hunger Games', 'Suzanne Collins',
        'In the ruins of a place once known as North America lies the nation of Panem, a shining Capitol surrounded by twelve outlying districts. The Capitol is harsh and cruel and keeps the districts in line by forcing them all to send one boy and one girl between the ages of twelve and eighteen to participate in the annual Hunger Games, a fight to the death on live TV. Sixteen-year-old Katniss Everdeen, who lives alone with her mother and younger sister, regards it as a death sentence when she steps forward to take her sisters place in the Games. But Katniss has been close to dead before—and survival, for her, is second nature. Without really meaning to, she becomes a contender. But if she is to win, she will have to start making choices that weight survival against humanity and life against love.',
        '2021-07-29 08:44', '2021-07-29 08:44',
        'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1586722975l/2767052.jpg', true, 1),
       ('Harry Potter and the Order of the Phoenix', 'J.K. Rowling, Mary GrandPré (Illustrator)',
        'There is a door at the end of a silent corridor. And it’s haunting Harry Pottter’s dreams. Why else would he be waking in the middle of the night, screaming in terror? Harry has a lot on his mind for this, his fifth year at Hogwarts: a Defense Against the Dark Arts teacher with a personality like poisoned honey; a big surprise on the Gryffindor Quidditch team; and the looming terror of the Ordinary Wizarding Level exams. But all these things pale next to the growing threat of He-Who-Must-Not-Be-Named - a threat that neither the magical government nor the authorities at Hogwarts can stop. As the grasp of darkness tightens, Harry must discover the true depth and strength of his friends, the importance of boundless loyalty, and the shocking price of unbearable sacrifice. His fate depends on them all.',
        '2021-07-29 08:44', '2021-07-29 08:44',
        'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1546910265l/2.jpg', true, 1),
       ('To Kill a Mockingbird', 'Harper Lee',
        'The unforgettable novel of a childhood in a sleepy Southern town and the crisis of conscience that rocked it. "To Kill A Mockingbird" became both an instant bestseller and a critical success when it was first published in 1960. It went on to win the Pulitzer Prize in 1961 and was later made into an Academy Award-winning film, also a classic. Compassionate, dramatic, and deeply moving, "To Kill A Mockingbird" takes readers to the roots of human behavior - to innocence and experience, kindness and cruelty, love and hatred, humor and pathos. Now with over 18 million copies in print and translated into forty languages, this regional story by a young Alabama woman claims universal appeal. Harper Lee always considered her book to be a simple love story. Today it is regarded as a masterpiece of American literature. ',
        '2021-07-29 08:44', '2021-07-29 08:44',
        'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1553383690l/2657.jpg', true, 1),
       ('Pride and Prejudice', 'Jane Austen, Anna Quindlen (Introduction)',
        'Since its immediate success in 1813, Pride and Prejudice has remained one of the most popular novels in the English language. Jane Austen called this brilliant work "her own darling child" and its vivacious heroine, Elizabeth Bennet, "as delightful a creature as ever appeared in print." The romantic clash between the opinionated Elizabeth and her proud beau, Mr. Darcy, is a splendid performance of civilized sparring. And Jane Austens radiant wit sparkles as her characters dance a delicate quadrille of flirtation and intrigue, making this book the most superb comedy of manners of Regency England.',
        '2021-07-29 08:53', '2021-07-29 08:53',
        'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1320399351l/1885.jpg', true, 1),
       ('Twilight', 'Stephenie Meyer',
        'About three things I was absolutely positive. First, Edward was a vampire. Second, there was a part of him—and I didnt know how dominant that part might be—that thirsted for my blood. And third, I was unconditionally and irrevocably in love with him. Deeply seductive and extraordinarily suspenseful, Twilight is a love story with bite. ',
        '2021-07-29 08:55', '2021-07-29 08:55',
        'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1361039443l/41865.jpg', true, 2),
       ('The Book Thief', 'Markus Zusak',
        'It is 1939. Nazi Germany. The country is holding its breath. Death has never been busier, and will be busier still. By her brothers graveside, Liesels life is changed when she picks up a single object, partially hidden in the snow. It is The Gravediggers Handbook, left behind there by accident, and it is her first act of book thievery. So begins a love affair with books and words, as Liesel, with the help of her accordian-playing foster father, learns to read. Soon she is stealing books from Nazi book-burnings, the mayors wifes library, wherever there are books to be found. But these are dangerous times. When Liesels foster family hides a Jew in their basement, Liesels world is both opened up, and closed down. In superbly crafted writing that burns with intensity, award-winning author Markus Zusak has given us one of the most enduring stories of our time.',
        '2021-07-29 08:58', '2021-07-29 08:58',
        'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1522157426l/19063._SY475_.jpg', true, 2),
       ('Animal Farm', 'George Orwell, Russell Baker (Preface), C.M. Woodhouse (Introduction)',
        'A farm is taken over by its overworked, mistreated animals. With flaming idealism and stirring slogans, they set out to create a paradise of progress, justice, and equality. Thus the stage is set for one of the most telling satiric fables ever penned –a razor-edged fairy tale for grown-ups that records the evolution from revolution against tyranny to a totalitarianism just as terrible. When Animal Farm was first published, Stalinist Russia was seen as its target. Today it is devastatingly clear that wherever and whenever freedom is attacked, under whatever banner, the cutting clarity and savage comedy of George Orwell’s masterpiece have a meaning and message still ferociously fresh.',
        '2021-07-29 09:00', '2021-07-29 09:00',
        'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1325861570l/170448.jpg', true, 2),
       ('The Chronicles of Narnia', 'C.S. Lewis, Pauline Baynes (Illustrator)',
        'Journeys to the end of the world, fantastic creatures, and epic battles between good and evil—what more could any reader ask for in one book? The book that has it all is The Lion, the Witch and the Wardrobe, written in 1949 by Clive Staples Lewis. But Lewis did not stop there. Six more books followed, and together they became known as The Chronicles of Narnia. For the past fifty years, The Chronicles of Narnia have transcended the fantasy genre to become part of the canon of classic literature. Each of the seven books is a masterpiece, drawing the reader into a land where magic meets reality, and the result is a fictional world whose scope has fascinated generations. This edition presents all seven books—unabridged—in one impressive volume. The books are presented here in chronlogical order, each chapter graced with an illustration by the original artist, Pauline Baynes. Deceptively simple and direct, The Chronicles of Narnia continue to captivate fans with adventures, characters, and truths that speak to readers of all ages, even fifty years after they were first published.',
        '2021-07-29 09:02', '2021-07-29 09:02',
        'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1449868701l/11127._SY475_.jpg', true, 2),
       ('J.R.R. Tolkien 4-Book Boxed Set: The Hobbit and The Lord of the Rings', 'J.R.R. Tolkien',
        'This four-volume, boxed set contains J.R.R. Tolkiens epic masterworks The Hobbit and the three volumes of The Lord of the Rings (The Fellowship of the Ring, The Two Towers, and The Return of the King). In The Hobbit, Bilbo Baggins is whisked away from his comfortable, unambitious life in Hobbiton by the wizard Gandalf and a company of dwarves. He finds himself caught up in a plot to raid the treasure hoard of Smaug the Magnificent, a large and very dangerous dragon. The Lord of the Rings tells of the great quest undertaken by Frodo Baggins and the Fellowship of the Ring: Gandalf the wizard; the hobbits Merry, Pippin, and Sam; Gimli the dwarf; Legolas the elf; Boromir of Gondor; and a tall, mysterious stranger called Strider. J.R.R. Tolkiens three volume masterpiece is at once a classic myth and a modern fairy tale—a story of high and heroic adventure set in the unforgettable landscape of Middle-earth',
        '2021-07-29 09:04', '2021-07-29 09:04',
        'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1346072396l/30.jpg', true, 3),
       ('The Fault in Our Stars', 'John Green',
        'Despite the tumor-shrinking medical miracle that has bought her a few years, Hazel has never been anything but terminal, her final chapter inscribed upon diagnosis. But when a gorgeous plot twist named Augustus Waters suddenly appears at Cancer Kid Support Group, Hazels story is about to be completely rewritten. Insightful, bold, irreverent, and raw, The Fault in Our Stars is award-winning author John Greens most ambitious and heartbreaking work yet, brilliantly exploring the funny, thrilling, and tragic business of being alive and in love. ',
        '2021-07-29 09:05', '2021-07-29 09:05',
        'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1360206420l/11870085.jpg', true, 3),
       ('Gone with the Wind', 'Margaret Mitchell',
        'Scarlett OHara, the beautiful, spoiled daughter of a well-to-do Georgia plantation owner, must use every means at her disposal to claw her way out of the poverty she finds herself in after Shermans March to the Sea.',
        '2021-07-29 09:06', '2021-07-29 09:06',
        'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1551144577l/18405._SY475_.jpg', true, 3),
       ('The Hitchhikers Guide to the Galaxy', 'Douglas Adams',
        'A beautifully illustrated edition of the New York Times bestselling classic, timed to celebrate the pivotal 42nd anniversary of the original publication--with never-before-seen illustrations by award winner Chris Riddell. Seconds before Earth is demolished to make way for a galactic freeway, Arthur Dent is plucked off the planet by his friend Ford Prefect, a researcher for the revised edition of The Hitchhikers Guide to the Galaxy who, for the last fifteen years, has been posing as an out-of-work actor. Together, this dynamic pair begin a journey through space aided by a galaxyful of fellow travelers: Zaphod Beeblebrox--the two-headed, three-armed ex-hippie and totally out-to-lunch president of the galaxy; Trillian (formerly Tricia McMillan), Zaphods girlfriend, whom Arthur tried to pick up at a cocktail party once upon a time zone; Marvin, a paranoid, brilliant, and chronically depressed robot; and Veet Voojagig, a former graduate student obsessed with the disappearance of all the ballpoint pens hes bought over the years. Where are these pens? Why are we born? Why do we die? For all the answers, stick your thumb to the stars!',
        '2021-07-29 09:08', '2021-07-29 09:08',
        'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1559986152l/386162.jpg', true, 3);

create table comment
(
    id         serial        not null,
    message    varchar(2000) not null,
    user_id    int           not null,
    book_id    int           not null,
    created_at TIMESTAMP     not null,
    updated_at TIMESTAMP     not null,
    foreign key (user_id) references "user" (id),
    foreign key (book_id) references book (id)
);
insert into comment (message, book_id, user_id, created_at, updated_at)
values ('I recently watched the movie (I know... why would you ever watch the movie without reading the book first!?!). I was just curious - how different is the book from the movie? I want to read this book, but I have a long list of other titles that I am considering reading as well. Just wanted to know if I will be blown away by it.',
        1, 2, '2021-07-29 09:13', '2021-07-29 09:13'),
       ('I watched the movies and they were really good.Are the books better than the book? And if so should I read this trilogy?',
        1, 3, '2021-07-29 09:14', '2021-07-29 09:14'),
       ('okay so can anyone tell me how old a child should be to read this book?', 2, 2, '2021-07-29 09:15',
        '2021-07-29 09:15'),
       ('Can I vanish my life in this muggle world to join Dumbledores Army and fight against the Dark Lord and his Death Eaters now? Oh, how I wish I could, after reading this fascinating book!',
        2, 3, '2021-07-29 09:15', '2021-07-29 09:15'),
       ('Is there any way I could find out if this was turned into a movie.. High school demands it!!', 3, 2,
        '2021-07-29 09:17', '2021-07-29 09:17'),
       ('Is this classic on the reading list at your local high school?', 3, 3, '2021-07-29 09:15', '2021-07-29 09:15'),
       ('How do I understand the language in Pride and Prejudice? Im 13 and we havent studied it in school but I would like to read it for fun but I cant understand even the first page! Can someone please help me out!',
        4, 2, '2021-07-29 09:15', '2021-07-29 09:15'),
       ('Can anyone please recommend other satires or comedies of manner? I enjoy Austens sense of humor immensely. I equally enjoyed Oscar Wildes in "The Importance of Being Earnest"',
        4, 3, '2021-07-29 09:15', '2021-07-29 09:15'),
       ('Why do people hate this book so much? They shouldt even be allowed to', 5, 1, '2021-07-29 09:19',
        '2021-07-29 09:19'),
       ('I think we can all agree that Twilight is a master piece compared to Fifty shades of Grey?', 5, 3,
        '2021-07-29 09:15', '2021-07-29 09:15'),
       ('I would like to ask if anyone cried while reading the book?', 6, 1, '2021-07-29 09:21', '2021-07-29 09:21'),
       ('"The sun-- looks like a pat of softened butter-- melting-- into a warm, creamy mashed-potatoes cloud-- in the middle of a bottomless powder-blue bowl." This is another way I describe the sky in my own words at times...what about you? How would you explain the sky’s appearance to someone, like how Max asked Liesel to creatively express it in her own words?',
        6, 3, '2021-07-29 09:15', '2021-07-29 09:15'),
       ('So is this really about animals or is it representing people?', 7, 1, '2021-07-29 09:23', '2021-07-29 09:23'),
       ('is animal farm a goodread for kids?', 7, 3, '2021-07-29 09:15', '2021-07-29 09:15'),
       ('Two questions 1: Will this be counted as 1 or 7 books in my challenge? 2: What order should the books be read in? This book puts them in this order: The Magicians Nephew, The Lion, the Witch and the Wardrobe, The Horse and his Boy, Prince Caspian, The Voyage of the Dawn Treader, The Silver Chair, and The Last Battle. Should I read them in that order?',
        8, 1, '2021-07-29 09:24', '2021-07-29 09:24'),
       ('Should I try to read it even though I ve watched the movies? Wouldnt it spoil the whole story?', 8, 3,
        '2021-07-29 09:15', '2021-07-29 09:15'),
       ('Should I read the hobbit first I read The Lord of The Rings?Help me!', 9, 1, '2021-07-29 09:25',
        '2021-07-29 09:25'),
       ('Reading the "Hobbit" first looks like the right decision, right?', 9, 2, '2021-07-29 09:15',
        '2021-07-29 09:15'),
       ('My daughter and I loved this book. I am always struggling to find the next book she might like: any recommendations based on the fact that she really liked this one (The fault in our stars) and the Curious incident? Thanks',
        10, 1, '2021-07-29 09:26', '2021-07-29 09:26'),
       ('I know that the character of Hazel is based on someone real--but how did John Green come to meet her? Was she already a fan of his books?',
        10, 2, '2021-07-29 09:15', '2021-07-29 09:15'),
       ('Is this book ok for teenagers to read? Is it fiction?', 11, 1, '2021-07-29 09:15', '2021-07-29 09:15'),
       ('It has been SO long since I ve read this book and it certainly would be a joy to read it again. Probably worthwhile to check the history behind the writing of it. Wasnt that a banned book at one time?',
        11, 2, '2021-07-29 09:27', '2021-07-29 09:27'),
       ('If you read this book do you have to read the sequels to know what is going to happen or do they simply narrate another story just with the same characters?',
        12, 1, '2021-07-29 09:28', '2021-07-29 09:28'),
       ('What happen next', 12, 2, '2021-07-29 09:28', '2021-07-29 09:28');



