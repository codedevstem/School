--1. Single table queries (25%)

--a) Done 
	SELECT COUNT(Codon_id)
	FROM Codons

--b) Done
	SELECT *
	FROM Amino_acid_properties
	WHERE Charge = 'positive' AND Molecular_mass > 150

--c) Done
	SELECT *
	FROM Nucleotides
	WHERE Type = 'Purine'
	ORDER BY Symbol

--d) Done
	SELECT *
	FROM Codons
	WHERE Position2 = Position3

--e) Done
	SELECT Codon_sequence, Amino_acid_id
	FROM Codons
	GROUP BY Amino_acid_id
	HAVING COUNT(Codon_sequence) = 1

--2. Creating tables and modifying tables (25%)

--a) 
CREATE TABLE Amino_acid_nomenclature (
    Amino_id VARCHAR(3) NOT NULL,
    Symbol CHAR(1),
    Name VARCHAR(20),
    Code VARCHAR(5) NOT NULL,
    CONSTRAINT Amino_id_pk PRIMARY KEY (Amino_id),
    CONSTRAINT Name_fk FOREIGN KEY (Name)
        REFERENCES Amino_acid_properties (Name)
);

--b) 
insert into Amino_acid_nomenclature values ('a1', 'A', 'Alanine', 'Ala');
insert into Amino_acid_nomenclature values ('a2', 'C', 'Cysteine', 'Cys');
insert into Amino_acid_nomenclature values ('a3', 'D', 'Aspartic acid', 'Asp');
insert into Amino_acid_nomenclature values ('a4', 'E', 'Glutamic acid', 'Glu');
insert into Amino_acid_nomenclature values ('a5', 'F', 'Phenylalanine', 'Phe');
insert into Amino_acid_nomenclature values ('a6', 'G', 'Glycine', 'Gly');
insert into Amino_acid_nomenclature values ('a7', 'H', 'Histidine', 'His');
insert into Amino_acid_nomenclature values ('a8', 'I', 'Isoleucine', 'Ile');
insert into Amino_acid_nomenclature values ('a9', 'K', 'Lysine', 'Lys');
insert into Amino_acid_nomenclature values ('a10', 'L', 'Leucine', 'Leu');
insert into Amino_acid_nomenclature values ('a11', 'M', 'Methionine', 'Met');
insert into Amino_acid_nomenclature values ('a12', 'N', 'Asparagine', 'Asn');
insert into Amino_acid_nomenclature values ('a13', 'P', 'Proline', 'Pro');
insert into Amino_acid_nomenclature values ('a14', 'Q', 'Glutamine', 'Gln');
insert into Amino_acid_nomenclature values ('a15', 'R', 'Arginine', 'Arg');
insert into Amino_acid_nomenclature values ('a16', 'S', 'Serine', 'Ser');
insert into Amino_acid_nomenclature values ('a17', 'T', 'Threonine', 'Thr');
insert into Amino_acid_nomenclature values ('a18', 'V', 'Valine', 'Val');
insert into Amino_acid_nomenclature values ('a19', 'W', 'Tryptophan', 'Trp');
insert into Amino_acid_nomenclature values ('a20', 'Y', 'Tyrosine', 'Tyr');
insert into Amino_acid_nomenclature values ('a21', null, null, 'Stop');
insert into Amino_acid_nomenclature values ('a22', null, null, 'Stop');
insert into Amino_acid_nomenclature values ('a23', null, null, 'Stop');


--c)
--i) 
	ALTER TABLE Amino_acid_properties
	ADD CONSTRAINT ck_molecular_mass
	CHECK (Molecular_mass > 70 AND Molecular_mass < 210)
--ii) 
ALTER TABLE Amino_acid_properties
	ADD CONSTRAINT ck_charge_rule
	CHECK (Charge IN ('uncharged', 'positive', 'negative'))

--d) 
ALTER TABLE Codons
	ADD CONSTRAINT amino_acid_id_fk
		FOREIGN KEY (Amino_acid_id)
		REFERENCES Amino_acid_nomenclature (Amino_id)

--3. Multiple table queries (25%)

--a) 
SELECT 
    *
FROM
    Codons AS C,
    Amino_acid_nomenclature AS A
WHERE
    C.Amino_acid_id = A.Amino_id
        AND A.Code = 'stop'

--b) 
	SELECT Codon_sequence
	FROM Codons AS C
	WHERE C.Codon_sequence LIKE 'G%'

--c)
SELECT 
    C.Codon_sequence
FROM
    Codons AS C,
    Amino_acid_properties AS AP,
    Amino_acid_nomenclature AS AN
WHERE
    AP.name = AN.name
        AND C.Amino_acid_id = AN.Amino_id
ORDER BY AP.Molecular_mass ASC

--d) 
SELECT 
    COUNT(*)
FROM
    Amino_acid_properties AS AP,
    Codons AS C,
    Amino_acid_nomenclature AS AN
WHERE
    AN.Name = AP.Name
        AND AN.Amino_id = C.Amino_acid_id
        AND C.Position3 = 'A'
        AND AP.Charge = 'uncharged'

--e) 
SELECT 
    C.Codon_sequence, AP.name
FROM
    Amino_acid_properties AS AP,
    Codons AS C,
    Amino_acid_nomenclature AS AN
WHERE
    AN.name = AP.name
        AND AN.amino_id = C.amino_acid_id
        AND AP.molecular_mass > 130
        AND AP.molecular_mass < 150
        AND AP.Charge = 'uncharged'
	
--4. Advanced queries (25%)

--a) 
SELECT 
    SUM(type = 'purine') AS CaseOfPurines,
    SUM(type = 'pyrimidine') AS CaseOfPyrimidines
FROM
    Nucleotides

--b) 
SELECT 
    AN.Symbol
FROM
    Amino_acid_nomenclature AS AN
        INNER JOIN
    Codons AS C ON AN.Amino_id = C.Amino_acid_id
WHERE
    C.Position1 = C.Position2
        AND C.Position2 = C.Position3
ORDER BY AN.Name ASC

--c) 
SELECT 
    C.Codon_sequence
FROM
    Amino_acid_nomenclature AS AN
        INNER JOIN
    Codons AS C ON AN.Amino_id = C.Amino_acid_id
WHERE
    C.Position1 = 'A'
        OR C.Position1 = 'G'
        AND AN.Name LIKE '%ine'

--d) 
Select polarity, count(*)

from ((Codons as C inner Join Amino_acid_nomenclature as AN on

C.Amino_acid_id = AN.Amino_id)

inner join Amino_acid_properties as AP on AN.Name = AP.Name)

group by Polarity
--e) 
Select Charge, Polarity, Count(*)

from ((Codons as C inner Join Amino_acid_nomenclature as AN on

C.Amino_acid_id = AN.Amino_id)

inner join Amino_acid_properties as AP on AN.Name = AP.Name)

group by Polarity, Charge