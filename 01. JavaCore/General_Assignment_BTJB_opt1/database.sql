CREATE TABLE Candidate(
	candidateId INT PRIMARY KEY IDENTITY(1,1),
	fullName VARCHAR(200) NOT NULL,
	birthDay DATE NOT NULL,
	phone VARCHAR(30) NOT NULL,
	email VARCHAR(50) NOT NULL,
	candidateType TINYINT NOT NULL CHECK(candidateType BETWEEN 0 AND 2)
);

CREATE TABLE Experience(
	candidateId INT NOT NULL,
	expInYear INT NOT NULL CHECK(expInYear > 0),
	proSkill VARCHAR(200) NOT NULL,
	FOREIGN KEY (candidateId) REFERENCES Candidate(candidateId)
);

CREATE TABLE Fresher(
	candidateId INT NOT NULL,
	graduationDate DATE NOT NULL,
	graduationRank INT NOT NULL CHECK(graduationRank > 0),
	education VARCHAR(200) NOT NULL
	FOREIGN KEY (candidateId) REFERENCES Candidate(candidateId)
);

CREATE TABLE Intern(
	candidateId INT NOT NULL,
	major VARCHAR(200) NOT NULL,
	semester INT NOT NULL,
	universityName VARCHAR(200) NOT NULL,
	FOREIGN KEY (candidateId) REFERENCES Candidate(candidateId)
);

CREATE TABLE Certificate(
	certificateId INT NOT NULL,
	candidateId INT NOT NULL, 
	certificateName VARCHAR(200) NOT NULL,
	certificateRank INT NOT NULL CHECK (certificateRank > 0),
	certificateDate DATE NOT NULL,
	PRIMARY KEY (certificateId, candidateId),
	FOREIGN KEY (candidateId) REFERENCES Candidate(candidateId)
);